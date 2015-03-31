package org.opendaylight.iotdm.robot.util;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by wenxshi on 3/4/15.
 */
public class Uri {


    private String scheme = "http";
    private String host = "";
    private int port = 8989;
    private String path = "";
    private Map<String, List<String>> query = new HashMap<String, List<String>>();


    public Uri(String uriStr) {
        try {
            URI uri = new URI(uriStr);
            scheme = uri.getScheme();
            host = uri.getHost();
            port = uri.getPort();
            path = uri.getPath();
            for (String str : uri.getQuery().split("&")) {
                String[] arr = str.split("=");
                String name = arr[0];
                String value = arr[1];
                addQuery(name, value);
            }
        } catch (Exception e) {

        }
    }


    public List<String> getQueryValue(String name){
        return query.get(name);
    }

    public void addQuery(String name, String value) {
        if (!query.containsKey(name)) {
            query.put(name, new LinkedList<String>());
        }
        query.get(name).add(value);
    }


    public void removeQuery(String name) {
        if (query.containsKey(name)) {
            query.remove(name);
        }
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(scheme);
        sb.append("://");
        sb.append(host);
        if (port > 0) {
            sb.append(":");
            sb.append(port);
        }
        if (!path.startsWith("/"))
            sb.append("/");
        sb.append(path);
        sb.append(toQuery());
        return sb.toString();
    }
}
