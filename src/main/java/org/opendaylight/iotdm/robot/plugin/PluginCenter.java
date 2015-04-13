package org.opendaylight.iotdm.robot.plugin;

import org.opendaylight.iotdm.robot.api.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wenxshi on 4/6/15.
 */
public class PluginCenter {
    private static Map<String, Plugin> map = new HashMap<String, Plugin>();

    static {
        map.put(Coap.SCHEMA, new Coap());
        map.put(Http.SCHEMA, new Http());
    }

    public static Plugin getPlugin(String schema) {
        return map.get(schema.toLowerCase());
    }

    public static void register(String name, Plugin plugin) {
        map.put(name.toString(), plugin);
    }
}
