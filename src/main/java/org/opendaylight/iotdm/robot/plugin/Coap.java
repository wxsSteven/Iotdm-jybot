package org.opendaylight.iotdm.robot.plugin;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.*;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.core.server.resources.Resource;
import org.opendaylight.iotdm.constant.onem2m.OneM2M;
import org.opendaylight.iotdm.primitive.*;
import org.opendaylight.iotdm.robot.api.Plugin;
import org.opendaylight.iotdm.robot.util.GsonUtil;
import org.opendaylight.iotdm.robot.util.Prepare;

import java.math.BigInteger;

/**
 * Created by wenxshi on 4/2/15.
 */
public class Coap implements Plugin {
    public static final String SCHEMA = "coap";
    public static final int PORT=65432;

    private CoapServer server = new CoapServer(PORT) {
        @Override
        public org.eclipse.californium.core.server.resources.Resource createRoot() {
            return new RootResource();
        }
    };


    public Coap() {
    }

    public void start() {
        server.start();
    }

    public void close() {
        server.stop();
    }

    public ResponsePrimitive sendRequestAndGetResponse(RequestPrimitive requestPrimitive, String host, String port, String timeout) {
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
        prepareRequest(request,requestPrimitive,host,port);

        Response response = null;
        try {
            request.send();
            response = request.waitForResponse(Long.valueOf(timeout));
            if (response == null)
                return null;
        } catch (Exception e) {
        }
        ResponsePrimitive responsePrimitive=new ResponsePrimitive();
        prepareResponsePrimitive(response,responsePrimitive);
        return responsePrimitive;
    }

    private void prepareRequest(Request request, RequestPrimitive requestPrimitive,String host,String port){
        String uri =Prepare.uri(requestPrimitive, host, port, SCHEMA).toString();
        String payload = GsonUtil.jsonToPrettyJson(Prepare.payload(requestPrimitive));

        request.setURI(uri);
        request.setPayload(payload);
        request.setTimedOut(true);
        prepareOptions(requestPrimitive, request.getOptions());

        System.out.println("CoAP Request:");
        System.out.println("Uri:");
        System.out.println(uri + "\n");
        System.out.println("Option:");
        System.out.println(request.getOptions().toString() + "\n");
        System.out.println("Payload:");
        System.out.println(payload);
    }


    private void prepareResponsePrimitive(Response response,ResponsePrimitive responsePrimitive) {

        for (Option option : response.getOptions().asSortedList()) {
            switch (OneM2M.CoAP.Option.getEnum(option.getIntegerValue())) {
                case ONEM2M_RSC:
                    responsePrimitive.setResponseStatusCode(new BigInteger(option.getStringValue()));
                    break;
                case ONEM2M_RQI:
                    responsePrimitive.setRequestIdentifier(option.getStringValue());
                    break;
                case ONEM2M_FR:
                    responsePrimitive.setFrom(option.getStringValue());
                    break;
                case ONEM2M_OT:
                    responsePrimitive.setOriginatingTimestamp(option.getStringValue());
                    break;
                case ONEM2M_RSET:
                    responsePrimitive.setResultExpirationTimestamp(option.getStringValue());
                    break;
                case ONEM2M_EC:
                    responsePrimitive.setEventCategory(option.getStringValue());
                    break;
            }
        }

        String payload = response.getPayloadString();

        try {
            JsonArray array = new JsonParser().parse(payload).getAsJsonObject().get("any").getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                responsePrimitive.getContent().getAny().add(array.get(i).toString());
            }
        } catch (Exception e) {

        }
    }

    private void prepareOptions(RequestPrimitive requestPrimitive, OptionSet os) {

        //option build
        os.setContentFormat(MediaTypeRegistry.APPLICATION_JSON);

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

        //TODO
        //NOTIFICATION URI OF response type is not support. XSD is not in accord with TS-0008
    }

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
}
