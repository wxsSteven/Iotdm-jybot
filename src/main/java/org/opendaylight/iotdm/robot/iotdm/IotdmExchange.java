package org.opendaylight.iotdm.robot.iotdm;

import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.primitive.ResponsePrimitive;
import org.opendaylight.iotdm.robot.api.Plugin;
import org.opendaylight.iotdm.robot.util.GsonUtil;

/**
 * Created by wenxshi on 5/6/15.
 */
public class IotdmExchange {
    private String name;
    private String description;
    private RequestPrimitive requestPrimitive=new RequestPrimitive();
    private ResponsePrimitive responsePrimitive=new ResponsePrimitive();
    private String host;
    private String port;
    private String timeout;
    private Plugin plugin;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }



    public void setRequestPrimitive(RequestPrimitive requestPrimitive) {
        this.requestPrimitive = requestPrimitive;
    }

    public void setResponsePrimitive(ResponsePrimitive responsePrimitive) {
        this.responsePrimitive = responsePrimitive;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public void setName(String name){
        this.name=name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(name!=null){
            sb.append("///////////////////////////////"+name+" Start/////////////////////////////////////////\n");
        }
        if(description!=null){
            sb.append("=====================Description Start========================\n");
            sb.append(description);
            sb.append("=====================Description End========================\n");
        }
        if (requestPrimitive != null) {
            sb.append("------------------------------RequestPrimitive-----------------------------------\n");
            sb.append(GsonUtil.toPrettyJson(requestPrimitive));
            sb.append("\n");
        }
        if (plugin != null) {
            sb.append(plugin.toString());
            sb.append("\n");
        }
        if (responsePrimitive != null) {
            sb.append("------------------------------ResponsePrimitive------------------------------------\n");
            sb.append(GsonUtil.jsonToPrettyJson(GsonUtil.jsonToFullNameJson(GsonUtil.toJson(responsePrimitive))));
        }

        if(name!=null){
            sb.append("///////////////////////////////"+name+" End/////////////////////////////////////////"+"\n");
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public RequestPrimitive getRequestPrimitive() {
        return requestPrimitive;
    }

    public ResponsePrimitive getResponsePrimitive() {
        return responsePrimitive;
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
