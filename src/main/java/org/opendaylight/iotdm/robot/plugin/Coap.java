package org.opendaylight.iotdm.robot.plugin;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.*;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.core.server.resources.Resource;
import org.opendaylight.iotdm.constant.onem2m.OneM2M;
import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.robot.api.Plugin;
import org.opendaylight.iotdm.robot.util.Prepare;

/**
 * Created by wenxshi on 4/2/15.
 */
public class Coap implements Plugin {
    public static final String SCHEMA = "coap";

    private CoapServer server = new CoapServer() {
        @Override
        public org.eclipse.californium.core.server.resources.Resource createRoot() {
            return new RootResource();
        }
    };

    private class RootResource extends CoapResource {
        public RootResource() {
            super("");
        }

        @Override
        public Resource getChild(String name) {
            return this;
        }

        @Override
        public void handlePOST(final CoapExchange exchange) {

            StringBuilder sb = new StringBuilder();
            sb.append(exchange.advanced().getRequest().getURI() + "\n");
            for (Option option : exchange.getRequestOptions().asSortedList()) {
                sb.append(option.getNumber() + ":" + option.getStringValue() + "\n");
            }
            sb.append(exchange.getRequestText());
            exchange.respond(sb.toString());
        }
    }


    public Coap() {

    }

    public void start() {
        server.start();
    }

    public void close() {
        server.stop();
    }

    public String sendRequestAndGetResponse(RequestPrimitive requestPrimitive, String host, String port, String timeout) {
        Request request;
        switch (OneM2M.Operation.getEnum(
                requestPrimitive.getOperation())) {
            case CREATE:
                request = new Request(CoAP.Code.POST);
                break;
            case RETRIEVE:
                request = new Request(CoAP.Code.GET);
                break;
            case UPDATE:
                request = new Request(CoAP.Code.PUT);
                break;
            case DELETE:
                request = new Request(CoAP.Code.DELETE);
                break;
            case NOTIFY:
                request = new Request(CoAP.Code.POST);
                break;
            default:
                return null;
        }

        String payload = Prepare.payload(requestPrimitive);
        String uri = Prepare.uri(requestPrimitive, host, port, SCHEMA).toString();

        request.setURI(uri);
        request.setPayload(payload);
        prepareOptions(requestPrimitive, request.getOptions());

        Response response = request.send().getResponse();
        return response.getPayloadString();
    }

    private void prepareOptions(RequestPrimitive requestPrimitive, OptionSet os) {

        //option build
        if (requestPrimitive.getFrom() != null)
            os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_FR.value(), requestPrimitive.getFrom()));

        if (requestPrimitive.getRequestIdentifier() != null)
            os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_RQI.value(), requestPrimitive.getRequestIdentifier()));

        if (requestPrimitive.getName() != null)
            os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_NM.value(), requestPrimitive.getName()));

        if (requestPrimitive.getOriginatingTimestamp() != null)
            os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_OT.value(), requestPrimitive.getOriginatingTimestamp()));

        if (requestPrimitive.getRequestExpirationTimestamp() != null)
            os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_RQET.value(), requestPrimitive.getRequestExpirationTimestamp()));

        if (requestPrimitive.getResultExpirationTimestamp() != null)
            os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_RSET.value(), requestPrimitive.getResultExpirationTimestamp()));

        if (requestPrimitive.getOperationExecutionTime() != null)
            os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_OET.value(), requestPrimitive.getOperationExecutionTime()));

        if (requestPrimitive.getEventCategory() != null)
            os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_EC.value(), requestPrimitive.getEventCategory()));

        if (requestPrimitive.getGroupRequestIdentifier() != null)
            os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_GID.value(), requestPrimitive.getGroupRequestIdentifier()));
        //

        //TODO
        //NOTIFICATION URI OF response type is not support. XSD is not in accord with TS-0008
    }
}
