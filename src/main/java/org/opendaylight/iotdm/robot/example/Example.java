package org.opendaylight.iotdm.robot.example;


import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.primitive.enumeration.ResponseType;
import org.opendaylight.iotdm.robot.iotdm.Iotdm;
import org.opendaylight.iotdm.robot.util.RequestPrimitiveFactory;

/**
 * Created by wenxshi on 3/4/15.
 * This is example describer how a client create a contanier resource in the server and create subscription to that container.
 * When a new contentInstance is added in this container, a notifcation will be received by client.
 * <p/>
 * Our http server default port is 8282
 * Our client uses the 8989 port for listening the notification.
 */
public class Example {
    public static void main(String[] args) {
        RequestPrimitive requestPrimitive = RequestPrimitiveFactory.makeDefaultRequestPrimitive();
        Iotdm iotdm = new Iotdm();
        iotdm.sendRequestAndGetResponse(requestPrimitive);
    }
}


