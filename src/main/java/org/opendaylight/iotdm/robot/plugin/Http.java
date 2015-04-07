package org.opendaylight.iotdm.robot.plugin;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.robot.api.OneM2MOperation;
import org.opendaylight.iotdm.robot.api.Plugin;
import org.opendaylight.iotdm.robot.util.GsonUtil;
import org.opendaylight.iotdm.robot.util.Prepare;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by wenxshi on 3/30/15.
 */
public class Http implements Plugin {

    public static final String SCHEME="http";
    public static final int PORT = 8989;
    public static final String CREATE_IN_HTTP = "post";
    public static final String RETRIEVE_IN_HTTP = "get";
    public static final String UPDATE_IN_HTTP = "put";
    public static final String DELETE_IN_HTTP = "delete";
    public static final String NOTIFY_IN_HTTP = "post";


    private HttpClient httpClient;
    private Server httpServer;

    public Http() {
        httpServer = new Server(PORT);
        httpClient = new HttpClient();
        httpServer.setHandler(new NotificationHandler());
    }

    public void start() {
        try {
            httpClient.start();
            httpServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            httpClient.stop();
            httpServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendRequestAndGetResponse(RequestPrimitive requestPrimitive,String host,String port) {

        String url = Prepare.uriAdapter(SCHEME+"://"+host+":"+port+Prepare.uri(requestPrimitive));
        String payload =Prepare.payloadAdapter(Prepare.payload(requestPrimitive));


        System.out.println("Uri:");
        System.out.println(url+"\n");
        System.out.println("Payload:");
        System.out.println(payload);

        ContentExchange exchange = new ContentExchange();
        exchange.setURL(url);
        if (payload != null && !payload.equals(""))
            exchange.setRequestContentSource(new ByteArrayInputStream(payload.getBytes()));

        switch (requestPrimitive.getOperation().intValue()) {
            case OneM2MOperation.CREATE:
                exchange.setMethod(CREATE_IN_HTTP);
                break;
            case OneM2MOperation.RETRIEVE:
                exchange.setMethod(RETRIEVE_IN_HTTP);
                break;
            case OneM2MOperation.UPDATE:
                exchange.setMethod(UPDATE_IN_HTTP);
                break;
            case OneM2MOperation.DELETE:
                exchange.setMethod(DELETE_IN_HTTP);
                break;
            case OneM2MOperation.NOTIFY:
                exchange.setMethod(NOTIFY_IN_HTTP);
            default:
                return null;
        }
        return send(exchange);
    }

    private String send(ContentExchange exchange) {
        try {
            httpClient.send(exchange);
            exchange.waitForDone();
            return exchange.getResponseContent();
        } catch (IOException e) {
            return e.getMessage();
        } catch (InterruptedException e) {
            return e.getMessage();
        }
    }


    public class NotificationHandler extends AbstractHandler {
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse
                response) throws IOException, ServletException {
            String payload = IOUtils.toString(baseRequest.getInputStream());
            System.out.println("server notification:");
            System.out.println(GsonUtil.jsonToPrettyJson(payload));
        }
    }
}
