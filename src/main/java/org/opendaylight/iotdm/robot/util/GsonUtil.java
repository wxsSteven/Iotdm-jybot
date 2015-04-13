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

    public static String jsonToShortJson(String str) {
        try {
            JsonParser parser = new JsonParser();
            JsonElement object = parser.parse(str);
            jsonToShortJsonHelper(object);
            return object.toString();
        } catch (Exception e) {
            return str;
        }
    }

    private static void jsonToShortJsonHelper(JsonElement element) {
        if (element.isJsonObject()) {
            JsonObject object = element.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                String shortName = OneM2M.Name.ResourceAttribute.shortName(entry.getKey());
                if (!shortName.equals(entry.getKey())) {
                    JsonElement e = entry.getValue();
                    object.remove(entry.getKey());
                    object.add(shortName, e);
                }
                jsonToShortJsonHelper(entry.getValue());
            }
        } else if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                jsonToShortJsonHelper(array.get(i));
            }
        }
    }
}
