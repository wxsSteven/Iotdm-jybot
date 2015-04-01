package org.opendaylight.iotdm.robot.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.opendaylight.iotdm.primitive.PrimitiveContent;
import org.opendaylight.iotdm.robot.util.json.adaptor.PrimitiveContentAdaptor;

/**
 * Created by wenxshi on 3/30/15.
 */
public class GsonUtil {
    public static String toJson(Object o) {
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(PrimitiveContent.class, new PrimitiveContentAdaptor()).create();
        return gson.toJson(o);
    }

    public static String toPrettyJson(Object o) {
        Gson gson = new GsonBuilder().setPrettyPrinting().setPrettyPrinting().registerTypeAdapter(PrimitiveContent.class, new PrimitiveContentAdaptor()).create();
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
}
