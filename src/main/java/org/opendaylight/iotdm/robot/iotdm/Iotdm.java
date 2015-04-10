package org.opendaylight.iotdm.robot.iotdm;

import org.opendaylight.iotdm.primitive.Attribute;
import org.opendaylight.iotdm.primitive.PrimitiveContent;
import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.robot.api.Plugin;
import org.opendaylight.iotdm.robot.plugin.PluginCenter;
import org.opendaylight.iotdm.robot.util.GsonUtil;
import org.opendaylight.iotdm.robot.util.RequestPrimitiveFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by wenxshi on 3/31/15.
 */
public class Iotdm {
    private String host = "localhost";
    private String port = "8282";
    private String timeout="10000";

    /**
     * {
     * "operation": 1,
     * "to": "http://localhost:8282/InCSE1",
     * "from": "http://localhost:8989",
     * "requestIdentifier": "1234",
     * "resourceType": 2,
     * "name": "AE",
     * "content": {
     * "aName": "aValue",
     * "bValue": "bValue"
     * },
     * "originatingTimestamp": "100000",
     * "requestExpirationTimestamp": "200000",
     * "resultExpirationTimestamp": "200000",
     * "operationExecutionTime": "100000",
     * "responseType": 3,
     * "resultPersistence": {
     * "signum": 1,
     * "years": 0,
     * "months": 0,
     * "days": 0,
     * "hours": 2,
     * "minutes": 46,
     * "seconds": 40.000
     * },
     * "resultContent": 5,
     * "eventCategory": "Default",
     * "deliveryAggregation": true,
     * "groupRequestIdentifier": "12345",
     * "filterCriteria": {
     * "createdBefore": "123344",
     * "createdAfter": "12345",
     * "labels": ["b","c","d"],
     * "attribute": [
     * {
     * "name": "key",
     * "value": "value"
     * },
     * {
     * "name": "key",
     * "value": "value"
     * }
     * ],
     * "filterUsage": 1
     * },
     * "discoveryResultType": 1
     * }
     *
     * @return
     */

    public void setAccessPoint(String host, String port,String timeout) {
        this.host = host;
        this.port = port;
        this.timeout=timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public RequestPrimitive getInitilazedRequestPrimitive() {
        return RequestPrimitiveFactory.makeDefaultRequestPrimitive();
    }

    public RequestPrimitive getEmptyRequestPrimitive() {
        return new RequestPrimitive();
    }

    public RequestPrimitive getInitilazedCreateRequestPrimitive() {
        RequestPrimitive rp = RequestPrimitiveFactory.makeDefaultRequestPrimitive();
        rp.setFilterCriteria(null);
        rp.setDiscoveryResultType(null);
        return rp;
    }

    public RequestPrimitive getInitilazedRetrieveRequestPrimitive() {
        RequestPrimitive rp = RequestPrimitiveFactory.makeDefaultRequestPrimitive();
        rp.setResourceType(null);
        rp.setName(null);
        return rp;
    }

    public RequestPrimitive getInitilazedUpdateRequestPrimitive() {
        RequestPrimitive rp = RequestPrimitiveFactory.makeDefaultRequestPrimitive();
        rp.setResourceType(null);
        rp.setName(null);
        rp.setDiscoveryResultType(null);
        return rp;
    }

    public RequestPrimitive getInitilazedDeleteRequestPrimitive() {
        RequestPrimitive rp = RequestPrimitiveFactory.makeDefaultRequestPrimitive();
        rp.setResourceType(null);
        rp.setName(null);
        rp.setContent(null);
        rp.setDiscoveryResultType(null);
        return rp;
    }

    public RequestPrimitive getInitilazedNotifyRequestPrimitive() {
        RequestPrimitive rp = RequestPrimitiveFactory.makeDefaultRequestPrimitive();
        rp.setResourceType(null);
        rp.setName(null);
        rp.setResultPersistence(null);
        rp.setResultContent(null);
        rp.setFilterCriteria(null);
        rp.setDiscoveryResultType(null);
        return rp;
    }

    public void changeAttributeIn(Object object, String methodName, Object newValue) {
        methodName = methodName.replaceAll(" ", "");
        try {
            for (Method method : object.getClass().getMethods()) {
                if (methodName.equalsIgnoreCase(method.getName())) {
                    Class clazz = method.getParameterTypes()[0];
                    if (clazz.equals(BigInteger.class))
                        method.invoke(object, new BigInteger(newValue.toString()));
                    else if (clazz.equals(String.class))
                        method.invoke(object, newValue.toString());
                    else
                        method.invoke(object, newValue);
                    return;
                }
            }
            throw new AssertionError("No methond called \"" + methodName + "\" in " + object.getClass().getName() + ".class");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getAttributeIn(Object object, String methodName) {
        methodName = methodName.replaceAll(" ", "");
        try {
            for (Method method : object.getClass().getMethods()) {
                if (methodName.equalsIgnoreCase(method.getName())) {
                    return method.invoke(object, null);
                }
            }

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new AssertionError("No methond called \"" + methodName + "\" in " + object.getClass().getName() + ".class");
    }

    /**
     * This method is only for the old ODL testing, it will be removed in near future.
     *
     * @param name
     * @param value
     */
    public void changeContentAttributeIn(RequestPrimitive requestPrimitive, String name, Object value) {
        PrimitiveContent content = requestPrimitive.getContent();
        if (content == null) {
            content = new PrimitiveContent();
        }
        List<Object> list = content.getAny();

        for (Object object : list) {
            Attribute attr = (Attribute) object;
            if (attr.getName().equals(name)) {
                if (value == null)
                    list.remove(attr);
                else {
                    attr.setValue(value);
                }
                return;
            }
        }
        Attribute attr = new Attribute();
        attr.setName(name);
        attr.setValue(value);
        list.add(attr);
    }

    public String sendRequestAndGetResponse(RequestPrimitive requestPrimitive) {
        Plugin plugin = PluginCenter.getPlugin(requestPrimitive.getTo());
        plugin.start();
        System.out.println("Request:");
        String rst = GsonUtil.jsonToPrettyJson(plugin.sendRequestAndGetResponse(requestPrimitive,host,port,timeout));
        System.out.print("\n\n");
        System.out.println("Response:");
        System.out.println(rst);
        plugin.close();
        return rst;
    }
}
