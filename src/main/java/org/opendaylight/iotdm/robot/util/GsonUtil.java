package org.opendaylight.iotdm.robot.util;

import com.google.gson.*;
import org.opendaylight.iotdm.constant.onem2m.OneM2M;

import java.util.Map;

/**
 * Created by wenxshi on 3/30/15.
 */
public class GsonUtil {
    public static String toJson(Object o) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(o);
    }

    public static String toPrettyJson(Object o) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(o);
    }

    public static String jsonToPrettyJson(String str) {
        try {
            JsonParser parser = new JsonParser();
            JsonElement object = parser.parse(str);
            return (new GsonBuilder().setPrettyPrinting().create().toJson(object));
        } catch (Exception e) {
            return str;
        }
    }

    public static Object fromJson(String str){
        try {
            JsonParser parser = new JsonParser();
            JsonElement object = parser.parse(str);
            return object;
        } catch (Exception e) {
            return str;
        }
    }



    public static String jsonToShortNameJson(String str) {
        try {
            JsonParser parser = new JsonParser();
            JsonElement object = parser.parse(str);
            JsonElement object1 = parser.parse(str);
            jsonToShortNameJsonHelper(object, object1);
            return object1.toString();
        } catch (Exception e) {
            return str;
        }
    }

    private static void jsonToShortNameJsonHelper(JsonElement element, JsonElement element1) {
        if (element.isJsonObject()) {
            JsonObject object = element.getAsJsonObject();
            JsonObject object1=element1.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                String shortName = OneM2M.Name.ResourceAttribute.toShortName(entry.getKey());
                if(shortName.equals(entry.getKey())){
                    shortName=OneM2M.Name.ComplexType.toShortName(entry.getKey());
                }
                if (!shortName.equals(entry.getKey())) {
                    JsonElement e = entry.getValue();
                    object1.remove(entry.getKey());
                    object1.add(shortName, e);
                }
                jsonToShortNameJsonHelper(entry.getValue(), object1.get(entry.getKey()));
            }
        } else if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            JsonArray array1=element1.getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                jsonToShortNameJsonHelper(array.get(i), array1.get(i));
            }
        }
    }


    public static String jsonToFullNameJson(String str) {
        try {
            JsonParser parser = new JsonParser();
            JsonElement object = parser.parse(str);
            JsonElement object1 = parser.parse(str);
            jsonToFullNameJsonHelper(object, object1);
            return object1.toString();
        } catch (Exception e) {
            return str;
        }
    }


    private static void jsonToFullNameJsonHelper(JsonElement element, JsonElement element1) {
        if (element.isJsonObject()) {
            JsonObject object = element.getAsJsonObject();
            JsonObject object1=element1.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                String fullName = OneM2M.Name.ResourceAttribute.toFullName(entry.getKey());
                if(fullName.equals(entry.getKey())){
                    fullName=OneM2M.Name.ComplexType.toFullName(entry.getKey());
                }
                if (!fullName.equals(entry.getKey())) {
                    JsonElement e = entry.getValue();
                    object1.remove(entry.getKey());
                    object1.add(fullName, e);
                }
                jsonToFullNameJsonHelper(entry.getValue(), object1.get(entry.getKey()));
            }
        } else if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            JsonArray array1=element1.getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                jsonToFullNameJsonHelper(array.get(i), array1.get(i));
            }
        }
    }

}
