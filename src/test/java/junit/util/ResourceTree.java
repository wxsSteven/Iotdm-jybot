package junit.util;

import org.opendaylight.iotdm.constant.onem2m.OneM2M;
import org.opendaylight.iotdm.primitive.Container;
import org.opendaylight.iotdm.primitive.ContentInstance;
import org.opendaylight.iotdm.primitive.PrimitiveContent;
import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.robot.iotdm.Iotdm;
import org.opendaylight.iotdm.robot.util.Onem2mDateTime;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wenxshi on 5/5/15.
 */
public class ResourceTree {

    public static List<String> timeStamp = new LinkedList<>();
    public static final String PATH = "/InCSE1/container";

    public static void buildResourceTree() throws InterruptedException {

        Iotdm iotdm = new Iotdm();
        RequestPrimitive request = iotdm.getInitilazedCreateRequestPrimitive();
        PrimitiveContent content = new PrimitiveContent();
        Container container = new Container();
        ContentInstance contentInstance = new ContentInstance();

        container.getLabels().add("apple");
        contentInstance.getLabels().add("apple");
        content.getAny().add(container);
        container.setCreator("apple");
        contentInstance.setContent("mac");

        request.setName("container");
        request.setTo("/InCSE1");
        request.setResourceType(OneM2M.ResourceType.CONTAINER.value());
        request.setContent(content);
        iotdm.sendRequestAndGetResponseWithoutPrint(request);
        timeStamp.add(Onem2mDateTime.getCurrDateTime());
        Thread.sleep(1000);

        request.setName("containerChild1");
        request.setTo("/InCSE1/container");
        request.setResourceType(OneM2M.ResourceType.CONTAINER.value());
        container.getLabels().set(0, "ipad");
        content.getAny().set(0, container);
        iotdm.sendRequestAndGetResponseWithoutPrint(request);
        timeStamp.add(Onem2mDateTime.getCurrDateTime());
        Thread.sleep(1000);

        request.setName("containerChild2");
        request.setTo("/InCSE1/container");
        request.setResourceType(OneM2M.ResourceType.CONTAINER.value());
        container.getLabels().set(0, "iphone");
        content.getAny().set(0, container);
        iotdm.sendRequestAndGetResponseWithoutPrint(request);
        timeStamp.add(Onem2mDateTime.getCurrDateTime());
        Thread.sleep(1000);

        request.setName("contentInstanceChild1");
        request.setTo("/InCSE1/container");
        request.setResourceType(OneM2M.ResourceType.CONTENT_INSTANCE.value());
        contentInstance.getLabels().set(0, "ipad");
        content.getAny().set(0, contentInstance);
        iotdm.sendRequestAndGetResponseWithoutPrint(request);
        timeStamp.add(Onem2mDateTime.getCurrDateTime());
        Thread.sleep(1000);

        request.setName("contentInstanceChild2");
        request.setTo("/InCSE1/container");
        request.setResourceType(OneM2M.ResourceType.CONTENT_INSTANCE.value());
        contentInstance.getLabels().set(0, "iphone");
        content.getAny().set(0, contentInstance);
        iotdm.sendRequestAndGetResponseWithoutPrint(request);
        timeStamp.add(Onem2mDateTime.getCurrDateTime());
        Thread.sleep(1000);
    }

    public static void cleanResourceTree() {
        Iotdm iotdm = new Iotdm();
        RequestPrimitive request = iotdm.getInitilazedDeleteRequestPrimitive();
        request.setTo("/InCSE1/container");
        iotdm.sendRequestAndGetResponseWithoutPrint(request);
    }
}
