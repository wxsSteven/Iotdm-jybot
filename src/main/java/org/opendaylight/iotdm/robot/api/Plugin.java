package org.opendaylight.iotdm.robot.api;

import org.opendaylight.iotdm.primitive.RequestPrimitive;

/**
 * Created by wenxshi on 3/30/15.
 */
public interface Plugin {
    void start();
    void close();
    String sendRequestAndGetResponse(RequestPrimitive requestPrimitive);
}
