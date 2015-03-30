package org.opendaylight.iotdm.robot.example;


import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.opendaylight.iotdm.onem2m.core.constant.OneM2MName;
import org.opendaylight.iotdm.robot.client.NotificationHandler;
import org.opendaylight.iotdm.robot.client.OneM2MClient;
import org.opendaylight.iotdm.robot.util.PayloadBuilder;
import org.opendaylight.iotdm.robot.util.UriBuilder;

/**
 * Created by wenxshi on 3/4/15.
 * This is example describer how a client create a contanier resource in the server and create subscription to that container.
 * When a new contentInstance is added in this container, a notifcation will be received by client.
 * <p/>
 * Our http server default port is 8282
 * Our client uses the 8989 port for listening the notification.
 */
public class Example {

    public static void prettyPrint(String str) {
        JsonParser parser = new JsonParser();
        JsonElement object = parser.parse(str);
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(object));
        System.out.println();
    }

    public static void main(String[] args) {

        //Create a oneM2MClient, set the handler for notification and then start it
        OneM2MClient client = new OneM2MClient();
        client.setHandlerForNotification(new NotificationHandler());
        client.start();


        String uri = "";
        String payload = "";
        String rst = "";

        System.out.println("-------------------------------------------------------------");
        //Step one:Create a container
        System.out.println("Step one:Create a container");
        uri = new UriBuilder("http://localhost:8282/InCSE1").build();
        payload = new PayloadBuilder().addCommonAttributePair(OneM2MName.FROM, "http://localhost:8989")
                .addCommonAttributePair(OneM2MName.REQUEST_IDENTIFIER, "12345")
                .addCommonAttributePair(OneM2MName.RESOURCE_TYPE, OneM2MName.CONTAINER)
                .addContentAttributePair("labels", "TV")
                .addContentAttributePair("ontologyRef", "ces1").build();

        rst = client.create(uri, payload);
        prettyPrint(rst);
        System.out.println("\n\n\n");

        //Step two:Create Subscription to the container
        System.out.println("Step two:Create Subscription to the container");
        uri = new UriBuilder("http://localhost:8282/InCSE1/20001").build();
        payload = new PayloadBuilder()
                .addCommonAttributePair(OneM2MName.FROM, "http://localhost:8989")
                .addCommonAttributePair(OneM2MName.REQUEST_IDENTIFIER, "12345")
                .addSubscriber("http://localhost:8989", "wholeResource").build();
        rst = client.create(uri, payload);
        prettyPrint(rst);
        System.out.println("\n\n\n");

        //Step three:Create contentInstance under container
        System.out.println("Step three:Create contentInstance under container");
        uri = new UriBuilder("http://localhost:8282/InCSE1/20001").build();
        payload = new PayloadBuilder().addCommonAttributePair(OneM2MName.FROM, "http://localhost:8989")
                .addCommonAttributePair(OneM2MName.REQUEST_IDENTIFIER, "12345")
                .addCommonAttributePair(OneM2MName.RESOURCE_TYPE, OneM2MName.CONTENT_INSTANCE)
                .addContentAttributePair("labels", "TV")
                .addContentAttributePair("ontologyRef", "ces1").build();
        rst = client.create(uri, payload);
        prettyPrint(rst);
        System.out.println("\n\n\n");

        //Step four: Client received a resourceChanged notification from server and client process the notification
        //by print it out.
        System.out.println("Step four: Client received a resourceChanged notification from server and client process the notification by print it out.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n\n");


        //Step Five:Create more resource under InCse1
        System.out.println("Step Five:Create more resource under InCse1");
        uri = new UriBuilder("http://localhost:8282/InCSE1").build();
        payload = new PayloadBuilder().addCommonAttributePair(OneM2MName.FROM, "http://localhost:8989")
                .addCommonAttributePair(OneM2MName.REQUEST_IDENTIFIER, "12345")
                .addCommonAttributePair(OneM2MName.RESOURCE_TYPE, OneM2MName.AE)
                .addContentAttributePair("labels", "TV")
                .addContentAttributePair("ontologyRef", "ces1").build();

        rst = client.create(uri, payload);

        uri = new UriBuilder("http://localhost:8282/InCSE1").build();
        payload = new PayloadBuilder().addCommonAttributePair(OneM2MName.FROM, "http://localhost:8989")
                .addCommonAttributePair(OneM2MName.REQUEST_IDENTIFIER, "12345")
                .addCommonAttributePair(OneM2MName.RESOURCE_TYPE, OneM2MName.AE)
                .addContentAttributePair("labels", "TV")
                .addContentAttributePair("ontologyRef", "ces1").build();

        rst = client.create(uri, payload);

        uri = new UriBuilder("http://localhost:8282/InCSE1").build();
        payload = new PayloadBuilder().addCommonAttributePair(OneM2MName.FROM, "http://localhost:8989")
                .addCommonAttributePair(OneM2MName.REQUEST_IDENTIFIER, "12345")
                .addCommonAttributePair(OneM2MName.RESOURCE_TYPE, OneM2MName.CONTAINER)
                .addContentAttributePair("labels", "TV")
                .addContentAttributePair("ontologyRef", "ces1").build();
        rst = client.create(uri, payload);

        prettyPrint(rst);
        System.out.println("\n\n\n");

        //Step Six: Retrieve resource under inCSE1 which resourceType is AE only
        System.out.println("Step Six: Retrieve resource under inCSE1 which resourceType is container only");
        uri = new UriBuilder("http://localhost:8282/InCSE1").addQueryFrom("localhost:8989").addQueryRequestIdentifier("1234")
                .addQuery(OneM2MName.RESOURCE_TYPE, OneM2MName.CONTAINER).build();
        rst = client.retrieve(uri);
        prettyPrint(rst);
        System.out.println("\n\n\n");
    }
}


