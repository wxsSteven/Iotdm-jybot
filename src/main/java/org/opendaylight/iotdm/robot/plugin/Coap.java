package org.opendaylight.iotdm.robot.plugin;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.*;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.core.server.resources.Resource;
import org.opendaylight.iotdm.constant.onem2m.OneM2M;
import org.opendaylight.iotdm.primitive.Attribute;
import org.opendaylight.iotdm.primitive.FilterCriteria;
import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.robot.api.Plugin;
import org.opendaylight.iotdm.robot.util.GsonUtil;

import java.util.List;

/**
 * Created by wenxshi on 4/2/15.
 */
public class Coap implements Plugin {
    private CoapClient client = new CoapClient();
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

            StringBuilder sb=new StringBuilder();
            sb.append(exchange.advanced().getRequest().getURI() + "\n");
            for (Option option : exchange.getRequestOptions().asSortedList()) {
                sb.append(option.getNumber() + ":" + option.getStringValue()+"\n");
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

    public String sendRequestAndGetResponse(RequestPrimitive requestPrimitive) {
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
        client.setURI(requestPrimitive.getTo());
        OptionSet os = prepareOptions(requestPrimitive);
        String payload = preparePayload(requestPrimitive);

        request.setOptions(os);
        request.setPayload(payload);

        CoapResponse response=client.advanced(request);

        return response.getResponseText();
    }

    private OptionSet prepareOptions(RequestPrimitive requestPrimitive) {
        OptionSet os = new OptionSet();

        //uri build
        os.addUriQuery(OneM2M.Name.Primitive.RESOURCE_TYPE + "=" + requestPrimitive.getResourceType());
        os.addUriQuery(OneM2M.Name.Primitive.RESPONSE_TYPE + "=" + requestPrimitive.getResourceType());
        os.addUriQuery((OneM2M.Name.Primitive.RESULT_PERSISTENCE + "=" + requestPrimitive.getResultPersistence()));
        os.addUriQuery(OneM2M.Name.Primitive.DELIVERY_AGGREGATION + "=" + requestPrimitive.getDiscoveryResultType());
        os.addUriQuery(OneM2M.Name.Primitive.RESULT_CONTENT + "=" + requestPrimitive.getResultContent());

        if (requestPrimitive.getFilterCriteria() != null) {
            FilterCriteria fc = requestPrimitive.getFilterCriteria();
            os.addUriQuery(OneM2M.Name.Primitive.CREATED_BEFORE + "=" + fc.getCreatedBefore());
            os.addUriQuery(OneM2M.Name.Primitive.CREATED_AFTER + "=" + fc.getCreatedAfter());
            os.addUriQuery(OneM2M.Name.Primitive.MODIFIED_SINCE + "=" + fc.getModifiedSince());
            os.addUriQuery(OneM2M.Name.Primitive.UNMODIFIED_SINCE + "=" + fc.getUnmodifiedSince());
            os.addUriQuery(OneM2M.Name.Primitive.STATE_TAG_SMALLER + "=" + fc.getStateTagSmaller());
            os.addUriQuery(OneM2M.Name.Primitive.STATE_TAG_BIGGER + "=" + fc.getStateTagBigger());
            os.addUriQuery(OneM2M.Name.Primitive.EXPIRE_BEFORE + "=" + fc.getExpireBefore());
            os.addUriQuery(OneM2M.Name.Primitive.EXPIRE_AFTER + "=" + fc.getExpireAfter());
            os.addUriQuery(OneM2M.Name.Primitive.LABELS + "=" + prepareListString(fc.getLabels()));
            os.addUriQuery(OneM2M.Name.Primitive.FILTER_CRITERIA_RESOURCE_TYPE + "=" + fc.getResourceType());
            os.addUriQuery(OneM2M.Name.Primitive.SIZE_ABOVE + "=" + fc.getSizeAbove());
            os.addUriQuery(OneM2M.Name.Primitive.SIZE_BELOW + "=" + fc.getSizeBelow());
            os.addUriQuery(OneM2M.Name.Primitive.CONTENT_TYPE + "=" + prepareListString(fc.getContentType()));
            os.addUriQuery(OneM2M.Name.Primitive.ATTRIBUTE + "=" + prepareListAttribute(fc.getAttribute()));
            os.addUriQuery(OneM2M.Name.Primitive.FILTER_USAGE + "=" + fc.getFilterUsage());
            os.addUriQuery(OneM2M.Name.Primitive.LIMIT + "=" + fc.getLimit());
        } else {
            os.addUriQuery(OneM2M.Name.Primitive.FILTER_CRITERIA + "=" + null);
        }
        os.addUriQuery(OneM2M.Name.Primitive.DISCOVERY_RESULT_TYPE + "=" + requestPrimitive.getDiscoveryResultType());
        //uri build end

        //option build
        os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_FR.value(), requestPrimitive.getFrom()));
        os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_RQI.value(), requestPrimitive.getRequestIdentifier()));
        os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_NM.value(), requestPrimitive.getName()));
        os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_OT.value(), requestPrimitive.getOriginatingTimestamp()));
        os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_RQET.value(), requestPrimitive.getRequestExpirationTimestamp()));
        os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_RSET.value(), requestPrimitive.getResultExpirationTimestamp()));
        os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_OET.value(), requestPrimitive.getOperationExecutionTime()));
        os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_EC.value(), requestPrimitive.getEventCategory()));
        os.addOption(new Option(OneM2M.CoAP.Option.ONEM2M_GID.value(), requestPrimitive.getGroupRequestIdentifier()));
        //

        //TODO
        //NOTIFICATION URI OF response type is not support. XSD is not in accord with TS-0008

        return os;

    }

    private String prepareListAttribute(List<Attribute> list) {
        if (list == null)
            return null;
        if (list.isEmpty())
            return list.toString();

        StringBuilder sb = new StringBuilder();
        for (Attribute attr : list) {
            sb.append("+");
            sb.append(attr.getName());
            sb.append(":");
            sb.append(attr.getValue());
        }
        return sb.substring("+".length());
    }

    private String prepareListString(List<String> list) {
        if (list == null)
            return null;
        if (list.isEmpty())
            return list.toString();

        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append("+" + str);
        }
        return sb.substring("+".length());
    }


    private String preparePayload(RequestPrimitive requestPrimitive) {
        if (requestPrimitive == null)
            return null;
        String str = GsonUtil.toPrettyJson(requestPrimitive.getContent());
        return GsonUtil.jsonToShortJson(str);
    }
}
