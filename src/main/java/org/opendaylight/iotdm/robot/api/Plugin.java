package org.opendaylight.iotdm.robot.api;

import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.primitive.ResponsePrimitive;
import org.opendaylight.iotdm.robot.iotdm.IotdmExchange;

/**
 * Created by wenxshi on 3/30/15.
 */
public interface Plugin {
    void start();
    void close();

    void send(IotdmExchange iotdmExchange);
}
