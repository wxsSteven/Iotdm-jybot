package org.opendaylight.iotdm.robot.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.opendaylight.iotdm.onem2m.core.constant.OneM2MName;
import org.opendaylight.iotdm.primitive.Attribute;
import org.opendaylight.iotdm.primitive.FilterCriteria;
import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.primitive.enumeration.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wenxshi on 3/30/15.
 */
public class Prepare {
    private static Map<String, EnumApi[]> map;

    static {
        map = new HashMap<String, EnumApi[]>();
        map.put(OneM2MName.RESOURCE_TYPE, ResourceType.values());
        map.put(OneM2MName.NOTIFICATION_CONTENT_TYPE, NotificationContentType.values());
        map.put(OneM2MName.RESPONSE_TYPE, ResponseType.values());
        map.put(OneM2MName.DISCOVERY_RESULT_TYPE, DiscResType.values());
    }

    public static String uri(RequestPrimitive requestPrimitive) {

        StringBuilder sb = new StringBuilder();
        sb.append(requestPrimitive.getTo());
        sb.append("?");
        if (requestPrimitive.getFrom() != null)
            sb.append(OneM2MName.FROM + "=" + requestPrimitive.getFrom() + "&");

        if (requestPrimitive.getRequestIdentifier() != null)
            sb.append(OneM2MName.REQUEST_IDENTIFIER + "=" + requestPrimitive.getRequestIdentifier() + "&");

        if (requestPrimitive.getResourceType() != null)
            sb.append(OneM2MName.RESOURCE_TYPE + "=" + requestPrimitive.getResourceType() + "&");

        if (requestPrimitive.getName() != null)
            sb.append(OneM2MName.NAME + "=" + requestPrimitive.getName() + "&");

        if (requestPrimitive.getOriginatingTimestamp() != null)
            sb.append(OneM2MName.ORIGINATING_TIMESTAMP + "=" + requestPrimitive.getOriginatingTimestamp() + "&");

        if (requestPrimitive.getRequestExpirationTimestamp() != null)
            sb.append(OneM2MName.REQUEST_EXPIRATION_TIMESTAMP + "=" + requestPrimitive.getRequestExpirationTimestamp() + "&");

        if (requestPrimitive.getResultExpirationTimestamp() != null)
            sb.append(OneM2MName.RESULT_EXPIRATION_TIMESTAMP + "=" + requestPrimitive.getRequestExpirationTimestamp() + "&");

        if (requestPrimitive.getOperationExecutionTime() != null)
            sb.append(OneM2MName.OPERATION_EXECUTION_TIME + "=" + requestPrimitive.getOperationExecutionTime() + "&");

        if (requestPrimitive.getResponseType() != null)
            sb.append(OneM2MName.RESOURCE_TYPE + "=" + requestPrimitive.getResponseType() + "&");

        if (requestPrimitive.getResultPersistence() != null)
            sb.append(OneM2MName.RESULT_PERSISTENCE + "=" + requestPrimitive.getResultPersistence() + "&");

        if (requestPrimitive.getResultContent() != null)
            sb.append(OneM2MName.RESULT_CONTENT + "=" + requestPrimitive.getResultContent() + "&");

        if (requestPrimitive.getEventCategory() != null)
            sb.append(OneM2MName.EVENT_CATEGORY + "=" + requestPrimitive.getEventCategory() + "&");

        if (requestPrimitive.getDiscoveryResultType() != null)
            sb.append(OneM2MName.DISCOVERY_RESULT_TYPE + "=" + requestPrimitive.getDiscoveryResultType() + "&");

        if (requestPrimitive.getFilterCriteria() != null)
            appendFilterCriteria(sb, requestPrimitive.getFilterCriteria());

        if (requestPrimitive.getDiscoveryResultType() != null)
            sb.append(OneM2MName.DISCOVERY_RESULT_TYPE + "=" + requestPrimitive.getDiscoveryResultType());

        String result = sb.toString();
        if (result.endsWith("?"))
            result = result.substring(0, result.length());

        return result;
    }

    private static void appendFilterCriteria(StringBuilder sb, FilterCriteria fc) {
        if (fc.getCreatedBefore() != null)
            sb.append(OneM2MName.CREATED_BEFORE + "=" + fc.getCreatedBefore() + "&");

        if (fc.getCreatedAfter() != null)
            sb.append(OneM2MName.CREATED_AFTER + "=" + fc.getCreatedAfter() + "&");

        if (fc.getModifiedSince() != null)
            sb.append(OneM2MName.MODIFIED_SINCE + "=" + fc.getModifiedSince() + "&");

        if (fc.getUnmodifiedSince() != null)
            sb.append(OneM2MName.UNMODIFIED_SINCE + "=" + fc.getUnmodifiedSince() + "&");

        if (fc.getStateTagSmaller() != null)
            sb.append(OneM2MName.STATE_TAG_SMALLER + "=" + fc.getStateTagSmaller() + "&");

        if (fc.getStateTagBigger() != null)
            sb.append(OneM2MName.STATE_TAG_BIGGER + "=" + fc.getStateTagBigger() + "&");


        if (fc.getExpireBefore() != null)
            sb.append(OneM2MName.EXPIRE_BEFORE + "=" + fc.getExpireBefore() + "&");

        if (fc.getExpireAfter() != null)
            sb.append(OneM2MName.EXPIRE_AFTER + "=" + fc.getExpireAfter() + "&");

        if (fc.getResourceType() != null)
            sb.append(OneM2MName.RESOURCE_TYPE + "=" + fc.getResourceType() + "&");

        if (fc.getSizeAbove() != null)
            sb.append(OneM2MName.SIZE_ABOVE + "=" + fc.getSizeAbove() + "&");

        if (fc.getSizeBelow() != null)
            sb.append(OneM2MName.SIZE_BELOW + "=" + fc.getSizeBelow() + "&");

        if (fc.getLimit() != null)
            sb.append(OneM2MName.LIMIT + "=" + fc.getLimit());

        //TODO:In future, using "+" to combine the collection
        //----------------------------------------------------------------------------------------------------
//        if (fc.getLabels() != null && fc.getLabels().size() > 0) {
//            String labels = "";
//            for (String l : fc.getLabels()) {
//                labels += l + "+";
//            }
//            sb.append(OneM2MName.LABELS + "=" + labels.substring(0, labels.length()-1) + "&");
//        }
//
//        if (fc.getContentType() != null && fc.getContentType().size() > 0) {
//            String ct = "";
//            for (String c : fc.getContentType()) {
//                ct += c + "+";
//            }
//            sb.append(OneM2MName.CREATED_BEFORE + "=" + ct.substring(0, ct.length()-1) + "&");
//        }
//
//        if (fc.getAttribute() != null && fc.getAttribute().size() > 0) {
//            String attributes = "";
//            for (Attribute attr : fc.getAttribute()) {
//                if (attr.getName() != null && attr.getValue() != null)
//                    attributes += attr.getName() + ":" + attr.getValue() + "+";
//            }
//            sb.append(OneM2MName.ATTRIBUTE + "=" + attributes.substring(0, attributes.length()-1) + "&");
//        }
        //--------------------------------------------------------------------------------------------------

        //Todo:following will be deprecated
        //----------------------------------------------------------------------------------------------------
        if (fc.getLabels() != null && fc.getLabels().size() > 0) {
            for (String l : fc.getLabels()) {
                sb.append(OneM2MName.LABELS + "=" + l + "&");
            }
        }

        if (fc.getContentType() != null && fc.getContentType().size() > 0) {
            for (String c : fc.getContentType()) {
                sb.append(OneM2MName.CREATED_BEFORE + "=" + c + "&");
            }
        }

        if (fc.getAttribute() != null && fc.getAttribute().size() > 0) {
            for (Attribute attr : fc.getAttribute()) {
                if (attr.getName() != null && attr.getValue() != null)
                    sb.append(OneM2MName.ATTRIBUTE + "=" + attr.getName() + ":" + attr.getValue() + "&");
            }
        }
        //-----------------------------------------------------------------------------------------------------


        if (fc.getFilterUsage() != null)
            sb.append(OneM2MName.FILTER_USAGE + "=" + fc.getFilterUsage() + "&");

    }


    public static String payload(RequestPrimitive requestPrimitive) {
        if (requestPrimitive != null) {
            return GsonUtil.toJson(requestPrimitive);
        }
        return null;
    }

    public static String payloadAdapter(String str) {
        JsonParser parser = new JsonParser();
        JsonElement root = parser.parse(str).getAsJsonObject();
        changeElementByKey(root);
        return GsonUtil.toPrettyJson(root);
    }

    private static void changeElementByKey(JsonElement element) {
        for (String key : map.keySet()) {
            changeElementByKeyHelper(element, key);
        }
    }

    private static void changeElementByKeyHelper(JsonElement element, String key) {
        if (element.isJsonObject()) {
            JsonObject object = element.getAsJsonObject();
            if (object.has(key)) {
                BigInteger number = object.get(key).getAsBigInteger();
                object.remove(key);
                object.addProperty(key, searchName(key, number));
            }
            for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                changeElementByKeyHelper(entry.getValue(), key);
            }
        } else if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                changeElementByKeyHelper(array.get(i), key);
            }
        }
    }


    private static String searchName(String key, BigInteger number) {
        for (EnumApi api : map.get(key)) {
            if (number.equals(api.getValue()))
                return api.getInterpretation();
        }
        return "";
    }

    public static String uriAdapter(String uriStr) {
        Uri ub = new Uri(uriStr);
        for (String name : map.keySet()) {
            List<String> values=ub.getQueryValue(name);
            if(values==null)
                continue;
            ub.removeQuery(name);
            for (String value : values) {
                ub.addQuery(name, searchName(name, new BigInteger(value)));
            }
        }
        return ub.toString();
    }
}
