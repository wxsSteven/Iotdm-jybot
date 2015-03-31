package org.opendaylight.iotdm.robot.util.json.adaptor;

import com.google.gson.*;
import org.opendaylight.iotdm.primitive.Attribute;
import org.opendaylight.iotdm.primitive.PrimitiveContent;

import java.lang.reflect.Type;

/**
 * Created by wenxshi on 2/24/15.
 */
public class PrimitiveContentAdaptor implements JsonSerializer<PrimitiveContent> {

    public JsonElement serialize(PrimitiveContent primitiveContent, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject root = new JsonObject();
        if (primitiveContent != null && primitiveContent.getAny().size() > 0) {
            for (Object object : primitiveContent.getAny()) {
                Attribute attr = (Attribute) object;
                root.addProperty(attr.getName(), attr.getValue().toString());
            }
            return root;
        }
        return null;
    }
}
