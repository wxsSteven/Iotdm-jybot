package org.opendaylight.iotdm.robot.plugin;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.http.HttpFields;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.opendaylight.iotdm.constant.onem2m.OneM2M;
import org.opendaylight.iotdm.primitive.PrimitiveContent;
import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.primitive.ResponsePrimitive;
import org.opendaylight.iotdm.primitive.ResponseTypeInfo;
import org.opendaylight.iotdm.robot.api.Plugin;
import org.opendaylight.iotdm.robot.util.GsonUtil;
import org.opendaylight.iotdm.robot.util.Prepare;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/30/15.
 */
public class Http implements Plugin {

    public static final String SCHEME="http";
    public static final int PORT = 8989;
    public static final String SCHEMA = "http";
    public static final String CREATE_IN_HTTP = "post";
    public static final String RETRIEVE_IN_HTTP = "get";
    public static final String UPDATE_IN_HTTP = "put";
    public static final String DELETE_IN_HTTP = "delete";
    public static final String NOTIFY_IN_HTTP = "post";
    public static final String CONTENT_TYPE = "application/json";


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


    public ResponsePrimitive sendRequestAndGetResponse(RequestPrimitive requestPrimitive, String host, String port, String timeout) {
        ContentExchange exchange = new ContentExchange(true);
        String url = Prepare.uri(requestPrimitive, host, port, SCHEMA).toString();
        String payload = GsonUtil.jsonToPrettyJson(Prepare.payload(requestPrimitive));

        exchange.setURL(url);
        exchange.setRequestContentType(CONTENT_TYPE);

        if (payload != null && !payload.equals(""))
            exchange.setRequestContentSource(new ByteArrayInputStream(payload.getBytes()));
        prepareHeader(requestPrimitive, exchange);

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

        System.out.println("Uri:");
        System.out.println(url + "\n");
        System.out.println("Header:");
        System.out.println(exchange.getRequestFields().toString() + "\n");
        System.out.println("Payload:");
        System.out.println(payload);


        try {
            httpClient.send(exchange);
            exchange.waitForDone();
        } catch (Exception e) {
        }
        ResponsePrimitive responsePrimitive = prepareResponsePrimitive(exchange);
        return responsePrimitive;
    }

    private ResponsePrimitive prepareResponsePrimitive(ContentExchange exchange) {
        ResponsePrimitive responsePrimitive = new ResponsePrimitive();
        HttpFields fields = exchange.getResponseFields();

        if (fields != null) {
            for (String key : fields.getFieldNamesCollection()) {
                switch (key) {
                    case OneM2M.Http.Header.X_M2M_RSC:
                        responsePrimitive.setResponseStatusCode(BigInteger.valueOf(fields.getLongField(key)));
                        break;
                    case OneM2M.Http.Header.X_M2M_RI:
                        responsePrimitive.setRequestIdentifier(fields.getStringField(key));
                        break;
                    case OneM2M.Http.Header.X_M2M_ORIGIN:
                        responsePrimitive.setFrom(fields.getStringField(key));
                        break;
                    case OneM2M.Http.Header.X_M2M_OT:
                        responsePrimitive.setOriginatingTimestamp(fields.getStringField(key));
                        break;
                    case OneM2M.Http.Header.X_M2M_RST:
                        responsePrimitive.setResultExpirationTimestamp(fields.getStringField(key));
                        break;
                    case OneM2M.Http.Header.X_M2M_EC:
                        responsePrimitive.setEventCategory(fields.getStringField(key));
                }
            }
        }

        PrimitiveContent pc = new PrimitiveContent();
        String payload = "";
        try {
            payload = exchange.getResponseContent();
            JsonArray array = new JsonParser().parse(payload).getAsJsonObject().get("any").getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                pc.getAny().add(GsonUtil.fromJson(array.get(i).toString()));
            }
        } catch (Exception e) {
            pc.getAny().add(payload);
        }
        responsePrimitive.setContent(pc);
        return responsePrimitive;
    }


    private void prepareHeader(RequestPrimitive requestPrimitive, ContentExchange exchange) {
        if (requestPrimitive.getFrom() != null)
            exchange.addRequestHeader(OneM2M.Http.Header.X_M2M_ORIGIN, requestPrimitive.getFrom());


        if (requestPrimitive.getRequestIdentifier() != null)
            exchange.addRequestHeader(OneM2M.Http.Header.X_M2M_RI, requestPrimitive.getRequestIdentifier());


        if (requestPrimitive.getName() != null)
            exchange.addRequestHeader(OneM2M.Http.Header.X_M2M_NM, requestPrimitive.getName());


        if (requestPrimitive.getGroupRequestIdentifier() != null)
            exchange.addRequestHeader(OneM2M.Http.Header.X_M2M_GID, requestPrimitive.getGroupRequestIdentifier());


        if (requestPrimitive.getResponseType() != null) {
            ResponseTypeInfo rti = requestPrimitive.getResponseType();

            if (!rti.getNotificationURI().isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (String ss : rti.getNotificationURI()) {
                    sb.append("&");
                    sb.append(ss);
                }
                exchange.addRequestHeader(OneM2M.Http.Header.X_M2M_RTU, sb.substring("&".length()));
            }
        }

        if (requestPrimitive.getOriginatingTimestamp() != null)
            exchange.addRequestHeader(OneM2M.Http.Header.X_M2M_OT, requestPrimitive.getOriginatingTimestamp());


        if (requestPrimitive.getResultExpirationTimestamp() != null)
            exchange.addRequestHeader(OneM2M.Http.Header.X_M2M_RST, requestPrimitive.getResultExpirationTimestamp());


        if (requestPrimitive.getRequestExpirationTimestamp() != null)
            exchange.addRequestHeader(OneM2M.Http.Header.X_M2M_RET, requestPrimitive.getRequestExpirationTimestamp());


        if (requestPrimitive.getOperationExecutionTime() != null)
            exchange.addRequestHeader(OneM2M.Http.Header.X_M2M_OET, requestPrimitive.getOperationExecutionTime());


        if (requestPrimitive.getEventCategory() != null)
            exchange.addRequestHeader(OneM2M.Http.Header.X_M2M_EC, requestPrimitive.getEventCategory());
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
