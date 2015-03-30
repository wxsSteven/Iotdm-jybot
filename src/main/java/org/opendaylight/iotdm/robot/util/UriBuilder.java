package org.opendaylight.iotdm.robot.util;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by wenxshi on 3/4/15.
 */
public class UriBuilder {

    public static String REQUEST_IDENTIFIER = "requestIdentifier";
    public static String FROM = "from";

    private String scheme = "http";
    private String host = "";
    private int port = -1;
    private String path = "";
    private Map<String, List<String>> query = new HashMap<String, List<String>>();


    public UriBuilder(String uriStr) {
        try {
            URI uri = new URI(uriStr);
            scheme = uri.getScheme();
            host = uri.getHost();
            port = uri.getPort();
            path = uri.getPath();
            for (String str : uri.getQuery().split(":")) {
                String[] arr = str.split("&");
                String name = arr[0];
                String value = arr[1];
                addQuery(name,value);
            }
        } catch (Exception e) {

        }
    }

    public UriBuilder addQuery(String name, String value) {
        if (!query.containsKey(name)) {
            query.put(name, new LinkedList<String>());
        }
        query.get(name).add(value);
        return this;
    }


    public UriBuilder removeQuery(String name) {
        if (query.containsKey(name)) {
            query.remove(name);
        }
        return this;
    }


    public UriBuilder addQueryRequestIdentifier(String requestIdentifer) {
        addQuery(REQUEST_IDENTIFIER, requestIdentifer);
        return this;
    }

    public UriBuilder addQueryFrom(String from) {
        addQuery(FROM, from);
        return this;
    }

    public UriBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    public UriBuilder setPort(String port) {
        this.port = Integer.valueOf(port);
        return this;
    }

    private String toQuery() {
        StringBuffer sb = new StringBuffer();

        for (Map.Entry<String, List<String>> entry : query.entrySet()) {
            for (String value : entry.getValue()) {
                sb.append("&");
                sb.append(entry.getKey());
                if (value != null && !value.isEmpty()) {
                    sb.append("=");
                    sb.append(value);
                }
            }
        }
        if (!query.isEmpty()) {
            sb.setCharAt(0, '?');
        }
        return sb.toString();
    }

    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append(scheme);
        sb.append("://");
        sb.append(host);
        if (port > 0) {
            sb.append(":");
            sb.append(port);
        }
        if(!path.startsWith("/"))
            sb.append("/");
        sb.append(path);
        sb.append(toQuery());
        return sb.toString();
    }

}
