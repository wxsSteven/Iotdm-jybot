package org.opendaylight.iotdm.robot.util;

import com.google.gson.JsonObject;
import org.opendaylight.iotdm.onem2m.core.constant.OneM2MName;

/**
 * Created by wenxshi on 3/4/15.
 */
public class PayloadBuilder {

    JsonObject object = new JsonObject();
    JsonObject content = new JsonObject();

    /**
     * CommonAttributePairs aims to hold info related the resource defined by resourceType in commonAttribute.
     *
     * @param name
     * @param value
     * @return
     */
    public PayloadBuilder addContentAttributePair(String name, String value) {
        content.addProperty(name, value);
        return this;
    }

    /**
     * CommonAttributePairs aim to hold  info related to oneM2M request.
     * Some of them are mandatory.(e.g requestIdentifier, resourceType, from)
     *
     * @param name
     * @param value
     * @return
     */
    public PayloadBuilder addCommonAttributePair(String name, String value) {
        object.addProperty(name, value);
        return this;
    }

    /**
     * Create subscription for specified resource.
     *
     * @param notificationUri         URI of subscriber
     * @param notificationContentType it should be one of "modifiedAttribute" "wholeResource" or "referenceOnly"
     * @return
     */

    public PayloadBuilder addSubscriber(String notificationUri, String notificationContentType) {
        addCommonAttributePair(OneM2MName.RESOURCE_TYPE, OneM2MName.SUBSCRIPTION);
        addContentAttributePair(OneM2MName.NOTIFICATION_URI, notificationUri);
        addContentAttributePair(OneM2MName.NOTIFICATION_CONTENT_TYPE, notificationContentType);
        return this;
    }

    public String build() {
        object.add(OneM2MName.CONTENT, content);
        return object.toString();
    }
}
