package org.opendaylight.iotdm.robot.client;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by wenxshi on 3/4/15.
 */
public class OneM2MClient {
    public static final String CREATE ="post";
    public static final String RETRIEVE ="get";
    public static final String UPDATE ="put";
    public static final String DELETE="delete";
    public static final int PORT=8989;

    private HttpClient httpClient;
    private Server httpServer;

    public OneM2MClient() {
        httpServer = new Server(PORT);
        httpClient = new HttpClient();
    }

    public String create(String url, String payload) {
        ContentExchange exchange = new ContentExchange();
        exchange.setURL(url);
        exchange.setRequestContentSource(new ByteArrayInputStream(payload.getBytes()));
        exchange.setMethod(OneM2MClient.CREATE);
        return operation(exchange);
    }


    public String retrieve(String url) {
        ContentExchange exchange = new ContentExchange();
        exchange.setURL(url);
        exchange.setMethod(OneM2MClient.RETRIEVE);
        return operation(exchange);

    }

    public String update(String url, String payload) {
        ContentExchange exchange = new ContentExchange();
        exchange.setURL(url);
        exchange.setRequestContentSource(new ByteArrayInputStream(payload.getBytes()));
        exchange.setMethod(OneM2MClient.UPDATE);
        return operation(exchange);
    }

    public String delete(String url) {
        ContentExchange exchange = new ContentExchange();
        exchange.setURL(url);
        exchange.setMethod(OneM2MClient.DELETE);
        return operation(exchange);
    }

    public void start() {
        try {
            httpServer.setHandler(new NotificationHandler());
            httpServer.start();
            httpClient.start();
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

    public void setHandlerForNotification(AbstractHandler handler) {
        httpServer.setHandler(handler);
    }

    private String operation(ContentExchange exchange) {
        try {
            httpClient.send(exchange);
            exchange.waitForDone();
            return exchange.getResponseContent();
        } catch (IOException e) {
            return "Response Error";
        } catch (InterruptedException e) {
            return "Response Error";
        }
    }
}
