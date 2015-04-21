package org.opendaylight.iotdm.robot.util;

import org.apache.http.client.utils.URIBuilder;
import org.opendaylight.iotdm.constant.onem2m.OneM2M;
import org.opendaylight.iotdm.primitive.Attribute;
import org.opendaylight.iotdm.primitive.FilterCriteria;
import org.opendaylight.iotdm.primitive.RequestPrimitive;

import java.net.URI;
import java.net.URISyntaxException;

public class Prepare {

    public static URI uri(RequestPrimitive requestPrimitive, String host, String port, String schema) {


        URIBuilder ub;
        try {
            ub = new URIBuilder(requestPrimitive.getTo());
        } catch (Exception e) {
            ub = new URIBuilder();
            ub.setPath("");
        }
        if (!ub.getPath().startsWith("/"))
            ub.setPath("/" + ub.getPath());

        if (ub.getHost() == null)
            ub.setHost(host);

        if (ub.getPort() < 0)
            ub.setPort(Integer.valueOf(port));

        ub.setScheme(schema);

        if (requestPrimitive.getResponseType() != null && requestPrimitive.getResponseType().getResponseType() != null)
            ub.addParameter(OneM2M.Name.Primitive.RESPONSE_TYPE.toString(), requestPrimitive.getResponseType().getResponseType().toString());

        if (requestPrimitive.getResourceType() != null)
            ub.addParameter(OneM2M.Name.Primitive.RESOURCE_TYPE.toString(), requestPrimitive.getResourceType().toString());

        if (requestPrimitive.getResultPersistence() != null)
            ub.addParameter(OneM2M.Name.Primitive.RESULT_PERSISTENCE.toString(), requestPrimitive.getResultPersistence().toString());

        if (requestPrimitive.getDiscoveryResultType() != null)
            ub.addParameter(OneM2M.Name.Primitive.DELIVERY_AGGREGATION.toString(), requestPrimitive.getDiscoveryResultType().toString());

        if (requestPrimitive.getResultContent() != null)
            ub.addParameter(OneM2M.Name.Primitive.RESULT_CONTENT.toString(), requestPrimitive.getResultContent().toString());

        if (requestPrimitive.getFilterCriteria() != null) {
            FilterCriteria fc = requestPrimitive.getFilterCriteria();
            if (fc.getCreatedBefore() != null)
                ub.addParameter(OneM2M.Name.Primitive.CREATED_BEFORE.toString(), fc.getCreatedBefore());

            if (fc.getCreatedAfter() != null)
                ub.addParameter(OneM2M.Name.Primitive.CREATED_AFTER.toString(), fc.getCreatedAfter());

            if (fc.getModifiedSince() != null)
                ub.addParameter(OneM2M.Name.Primitive.MODIFIED_SINCE.toString(), fc.getModifiedSince());

            if (fc.getUnmodifiedSince() != null)
                ub.addParameter(OneM2M.Name.Primitive.UNMODIFIED_SINCE.toString(), fc.getUnmodifiedSince());

            if (fc.getStateTagSmaller() != null)
                ub.addParameter(OneM2M.Name.Primitive.STATE_TAG_SMALLER.toString(), fc.getStateTagSmaller().toString());

            if (fc.getStateTagBigger() != null)
                ub.addParameter(OneM2M.Name.Primitive.STATE_TAG_BIGGER.toString(), fc.getStateTagBigger().toString());


            if (fc.getExpireBefore() != null)
                ub.addParameter(OneM2M.Name.Primitive.EXPIRE_BEFORE.toString(), fc.getExpireBefore());

            if (fc.getExpireAfter() != null)
                ub.addParameter(OneM2M.Name.Primitive.EXPIRE_AFTER.toString(), fc.getExpireAfter());

            if (fc.getLabels() != null && !fc.getLabels().isEmpty()) {
                for (String str : fc.getLabels()) {
                    ub.addParameter(OneM2M.Name.Primitive.LABELS.toString(), str);
                }
            }

            if (fc.getResourceType() != null)
                ub.addParameter(OneM2M.Name.Primitive.FILTER_CRITERIA_RESOURCE_TYPE.toString(), fc.getResourceType().toString());

            if (fc.getSizeAbove() != null)
                ub.addParameter(OneM2M.Name.Primitive.SIZE_ABOVE.toString(), fc.getSizeAbove().toString());

            if (fc.getSizeBelow() != null)
                ub.addParameter(OneM2M.Name.Primitive.SIZE_BELOW.toString(), fc.getSizeBelow().toString());

            if (fc.getContentType() != null && !fc.getContentType().isEmpty()) {
                for (String str : fc.getContentType()) {
                    ub.addParameter(OneM2M.Name.Primitive.CONTENT_TYPE.toString(), str);
                }
            }

            if (fc.getAttribute() != null && !fc.getAttribute().isEmpty()) {
                for (Attribute attribute : fc.getAttribute()) {
                    String str = attribute.getName() + ":" + attribute.getValue();
                    ub.addParameter(OneM2M.Name.Primitive.ATTRIBUTE.toString(), str);
                }
            }

            if (fc.getFilterUsage() != null)
                ub.addParameter(OneM2M.Name.Primitive.FILTER_USAGE.toString(), fc.getFilterUsage().toString());

            if (fc.getLimit() != null)
                ub.addParameter(OneM2M.Name.Primitive.LIMIT.toString(), fc.getLimit().toString());
        }
        if (requestPrimitive.getDiscoveryResultType() != null)
            ub.addParameter(OneM2M.Name.Primitive.DISCOVERY_RESULT_TYPE.toString(), requestPrimitive.getDiscoveryResultType().toString());

        try {
            return ub.build();
        } catch (URISyntaxException e) {
            return null;
        }
    }


    public static String payload(RequestPrimitive requestPrimitive) {
        if (requestPrimitive == null)
            return null;
        String str = GsonUtil.toPrettyJson(requestPrimitive.getContent());
        return GsonUtil.jsonToShortJson(str);
    }

}
