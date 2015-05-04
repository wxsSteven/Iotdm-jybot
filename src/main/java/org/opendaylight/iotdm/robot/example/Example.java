package org.opendaylight.iotdm.robot.example;

import com.sun.jmx.remote.internal.Unmarshal;
import org.opendaylight.iotdm.primitive.AE;
import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.robot.iotdm.Iotdm;
import org.opendaylight.iotdm.robot.util.GsonUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.Timestamp;

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
//        Iotdm iotdm = new Iotdm();
//        RequestPrimitive requestPrimitive = iotdm.getInitilazedCreateRequestPrimitive();
//        AE ae=new AE();
//        ae.setAEID("jsjfa");
//        ae.setAppID("1234");
//        requestPrimitive.getContent().getAny().set(0,ae);

//        requestPrimitive.setResultContent(null);
//        requestPrimitive.setTo("/InCSE1");
//        iotdm.sendRequestAndGetResponse(requestPrimitive);

        String str="{\n" +
                "  \"any\": [\n" +
                "    {\n" +
                "      \"labels\": [\n" +
                "        \"iphone\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        System.out.println(GsonUtil.jsonToShortNameJson(str));

    }

    public static void xml(){
        Iotdm iotdm = new Iotdm();
        RequestPrimitive requestPrimitive = iotdm.getInitilazedCreateRequestPrimitive();

        try {
            JAXBContext context = JAXBContext.newInstance(RequestPrimitive.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw=new StringWriter();
            m.marshal(requestPrimitive, sw);
            String str=sw.toString();

            Unmarshaller um=context.createUnmarshaller();
            Object o=um.unmarshal(new StringReader(str));
            System.out.println(o.getClass().getName());

        }catch (Exception e){

        }
    }
}


