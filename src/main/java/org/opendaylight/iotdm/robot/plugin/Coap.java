package org.opendaylight.iotdm.robot.plugin;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Option;
import org.eclipse.californium.core.coap.OptionSet;
import org.eclipse.californium.core.coap.Request;
import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.robot.api.OneM2MOperation;
import org.opendaylight.iotdm.robot.api.Plugin;

/**
 * Created by wenxshi on 4/2/15.
 */
public class Coap implements Plugin {
    private CoapServer server=new CoapServer();
    private CoapClient client=new CoapClient();

    public void start() {

    }

    public void close() {

    }

    public String sendRequestAndGetResponse(RequestPrimitive requestPrimitive) {
        Request request;
        switch (requestPrimitive.getOperation().intValue()){
            case OneM2MOperation.CREATE:
                request=new Request(CoAP.Code.POST);
                break;
            case OneM2MOperation.RETRIEVE:
                request=new Request(CoAP.Code.GET);
                break;
            case OneM2MOperation.UPDATE:
                request=new Request(CoAP.Code.PUT);
                break;
            case OneM2MOperation.DELETE:
                request=new Request(CoAP.Code.DELETE);
                break;
            case OneM2MOperation.NOTIFY:
                request=new Request(CoAP.Code.POST);
                break;
            default:
                return null;
        }
        OptionSet os=new OptionSet();
        Option op= new Option();
        return null;
    }
}
