package org.opendaylight.iotdm.robot.util;

import org.apache.http.client.utils.URIBuilder;
import org.opendaylight.iotdm.constant.onem2m.OneM2M;
import org.opendaylight.iotdm.primitive.Attribute;
import org.opendaylight.iotdm.primitive.FilterCriteria;
import org.opendaylight.iotdm.primitive.RequestPrimitive;

<<<<<<< HEAD
import java.math.BigInteger;
import java.net.URI;
import java.util.HashMap;
=======
import java.net.URI;
import java.net.URISyntaxException;
>>>>>>> coap
import java.util.List;

/**
 * Created by wenxshi on 3/30/15.
 */
public class Prepare {

    public static URI uri(RequestPrimitive requestPrimitive,String host,String port,String schema) {

<<<<<<< HEAD
        StringBuilder sb = new StringBuilder();
        String path="";
        try{
           path=new URI(requestPrimitive.getTo()).getPath();
            if(!path.startsWith("/"))
                path="/"+path;
        }catch(Exception e){
            System.out.println("Not valid uri");
        }

        sb.append(path);
        sb.append("?");
        if (requestPrimitive.getFrom() != null)
            sb.append(OneM2MName.FROM + "=" + requestPrimitive.getFrom() + "&");
=======
        URIBuilder ub= null;
        try {
            ub = new URIBuilder(requestPrimitive.getTo());
        } catch (Exception e) {
            ub=new URIBuilder();
            ub.setPath("");
        }
        if(!ub.getPath().startsWith("/"))
            ub.setPath("/" + ub.getPath());
>>>>>>> coap

        if(ub.getHost()==null)
            ub.setHost(host);

        if(ub.getPort()<0)
           ub.setPort(Integer.valueOf(port));

        ub.setScheme(schema);

        if(requestPrimitive.getResponseType()!=null&&requestPrimitive.getResponseType().getResponseType()!=null)
            ub.addParameter(OneM2M.Name.Primitive.RESPONSE_TYPE.toString(),requestPrimitive.getResponseType().getResponseType().toString());

        if (requestPrimitive.getResourceType() != null)
            ub.addParameter(OneM2M.Name.Primitive.RESOURCE_TYPE.toString(),requestPrimitive.getResourceType().toString());

        if (requestPrimitive.getResultPersistence() != null)
            ub.addParameter(OneM2M.Name.Primitive.RESULT_PERSISTENCE.toString(),requestPrimitive.getResultPersistence().toString());

        if (requestPrimitive.getDiscoveryResultType() != null)
            ub.addParameter(OneM2M.Name.Primitive.DELIVERY_AGGREGATION.toString(),requestPrimitive.getDiscoveryResultType().toString());

        if (requestPrimitive.getResultContent() != null)
            ub.addParameter(OneM2M.Name.Primitive.RESULT_CONTENT.toString(),requestPrimitive.getResultContent().toString());

        if (requestPrimitive.getFilterCriteria() != null) {
            FilterCriteria fc = requestPrimitive.getFilterCriteria();
            if (fc.getCreatedBefore() != null)
                ub.addParameter(OneM2M.Name.Primitive.CREATED_BEFORE.toString(),fc.getCreatedBefore());

            if (fc.getCreatedAfter() != null)
                ub.addParameter(OneM2M.Name.Primitive.CREATED_AFTER.toString(),fc.getCreatedAfter());

            if (fc.getModifiedSince() != null)
                ub.addParameter(OneM2M.Name.Primitive.MODIFIED_SINCE.toString(),fc.getModifiedSince());

            if (fc.getUnmodifiedSince() != null)
                ub.addParameter(OneM2M.Name.Primitive.UNMODIFIED_SINCE.toString(),fc.getUnmodifiedSince());

            if (fc.getStateTagSmaller() != null)
                ub.addParameter(OneM2M.Name.Primitive.STATE_TAG_SMALLER.toString(),fc.getStateTagSmaller().toString());

            if (fc.getStateTagBigger() != null)
                ub.addParameter(OneM2M.Name.Primitive.STATE_TAG_BIGGER.toString(),fc.getStateTagBigger().toString());

<<<<<<< HEAD
        String result = sb.toString();
        if (result.endsWith("?"))
            result = result.substring(0, result.length());
        return result;
    }
=======
            if (fc.getExpireBefore() != null)
                ub.addParameter(OneM2M.Name.Primitive.EXPIRE_BEFORE.toString(),fc.getExpireBefore());

            if (fc.getExpireAfter() != null)
                ub.addParameter(OneM2M.Name.Primitive.EXPIRE_AFTER.toString(),fc.getExpireAfter());
>>>>>>> coap

            if (fc.getLabels() != null)
                ub.addParameter(OneM2M.Name.Primitive.LABELS.toString(),prepareListString(fc.getLabels()));

            if (fc.getResourceType() != null)
                ub.addParameter(OneM2M.Name.Primitive.FILTER_CRITERIA_RESOURCE_TYPE.toString(),fc.getResourceType().toString());

            if (fc.getSizeAbove() != null)
                ub.addParameter(OneM2M.Name.Primitive.SIZE_ABOVE.toString(), fc.getSizeAbove().toString());

            if (fc.getSizeBelow() != null)
                ub.addParameter(OneM2M.Name.Primitive.SIZE_BELOW.toString(),fc.getSizeBelow().toString());

            if (fc.getContentType() != null&&!fc.getContentType().isEmpty()){
                  ub.addParameter(OneM2M.Name.Primitive.CONTENT_TYPE.toString(),prepareListString(fc.getContentType()));
            }

            if (fc.getAttribute() != null&&!fc.getAttribute().isEmpty())
                ub.addParameter(OneM2M.Name.Primitive.ATTRIBUTE.toString(),prepareListAttribute(fc.getAttribute()));

            if (fc.getFilterUsage() != null)
                ub.addParameter(OneM2M.Name.Primitive.FILTER_USAGE.toString(),fc.getFilterUsage().toString());

            if (fc.getLimit() != null)
                ub.addParameter(OneM2M.Name.Primitive.LIMIT.toString(),fc.getLimit().toString());
        }
        if (requestPrimitive.getDiscoveryResultType() != null)
            ub.addParameter(OneM2M.Name.Primitive.DISCOVERY_RESULT_TYPE.toString(), requestPrimitive.getDiscoveryResultType().toString());

        try {
            return ub.build();
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private static String prepareListAttribute(List<Attribute> list) {
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

    private static String prepareListString(List<String> list) {
        if (list.isEmpty())
            return list.toString();

        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append("+" + str);
        }
        return sb.substring("+".length());
    }


    public static String payload(RequestPrimitive requestPrimitive) {
        if (requestPrimitive == null)
            return null;
        String str = GsonUtil.toPrettyJson(requestPrimitive.getContent());
        return GsonUtil.jsonToShortJson(str);
    }

}
