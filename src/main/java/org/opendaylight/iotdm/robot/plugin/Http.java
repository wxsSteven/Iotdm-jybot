package org.opendaylight.iotdm.robot.plugin;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.opendaylight.iotdm.constant.onem2m.OneM2M;
import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.robot.api.Plugin;
import org.opendaylight.iotdm.robot.util.GsonUtil;
import org.opendaylight.iotdm.robot.util.Prepare;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by wenxshi on 3/30/15.
 */
public class Http implements Plugin {

    private static final int PORT = 8989;
    private static final String SCHEMA="http";
    private static final String CREATE_IN_HTTP = "post";
    private static final String RETRIEVE_IN_HTTP = "get";
    private static final String UPDATE_IN_HTTP = "put";
    private static final String DELETE_IN_HTTP = "delete";
    private static final String NOTIFY_IN_HTTP = "post";


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

    public String sendRequestAndGetResponse(RequestPrimitive requestPrimitive,String host,String port, String timeout) {
        ContentExchange exchange = new ContentExchange();
        String url = Prepare.uri(requestPrimitive,host,port,SCHEMA).toString();
        String payload =Prepare.payload(requestPrimitive);


        System.out.println("Uri:");
        System.out.println(url + "\n");
        System.out.println("Payload:");
        System.out.println(payload);


        exchange.setURL(url);


        if (payload != null && !payload.equals(""))
            exchange.setRequestContentSource(new ByteArrayInputStream(payload.getBytes()));

        OneM2M.Operation x = OneM2M.Operation.CREATE;
        switch (OneM2M.Operation.getEnum(requestPrimitive.getOperation())) {
            case CREATE:
                exchange.setMethod(CREATE_IN_HTTP);
                break;
            case RETRIEVE:
                exchange.setMethod(RETRIEVE_IN_HTTP);
                break;
            case UPDATE:
                exchange.setMethod(UPDATE_IN_HTTP);
                break;
            case DELETE:
                exchange.setMethod(DELETE_IN_HTTP);
                break;
            case NOTIFY:
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
