package org.opendaylight.iotdm.robot.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.opendaylight.iotdm.primitive.PrimitiveContent;
import org.opendaylight.iotdm.robot.util.json.adaptor.PrimitiveContentAdaptor;

/**
 * Created by wenxshi on 3/30/15.
 */
public class GsonUtil {
    public static String toJson(Object o){
        Gson gson=new GsonBuilder().setPrettyPrinting().registerTypeAdapter(PrimitiveContent.class, new PrimitiveContentAdaptor()).create();
        return gson.toJson(o);
    }

}
