package org.opendaylight.iotdm.robot.plugin;

import org.opendaylight.iotdm.constant.onem2m.OneM2M;
import org.opendaylight.iotdm.robot.api.Plugin;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wenxshi on 4/6/15.
 */
public class PluginCenter {
    public static String DEFAULT_PROTOCOL = "http";
    private static Map<String, Plugin> map = new HashMap<String, Plugin>();

    static {
        map.put(OneM2M.CoAP.NAME, new Coap());
        map.put(OneM2M.Http.NAME, new Http());
    }

    public static Plugin getPlugin(String uri) {
        try {
            String name=new URI(uri).getScheme();
            if (map.containsKey(name.toLowerCase()))
                return map.get(name.toLowerCase());
            return map.get(DEFAULT_PROTOCOL);
        } catch (Exception e) {
            return map.get(DEFAULT_PROTOCOL);
        }
    }
}
