package org.opendaylight.iotdm.robot.iotdm;

import org.opendaylight.iotdm.constant.enumeration.Operation;
import org.opendaylight.iotdm.primitive.Attribute;
import org.opendaylight.iotdm.primitive.PrimitiveContent;
import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.robot.plugin.Http;
import org.opendaylight.iotdm.robot.util.GsonUtil;
import org.opendaylight.iotdm.robot.util.RequestPrimitiveFactory;

import java.lang.System;
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
    private String schema = "http";


    /**
     * {
     * "operation": 1,
     * "to": "/InCSE1",
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

    public void setAccessServer(String host, String port, String schema) {
        this.host = host;
        this.port = port;
        this.schema = schema;
    }


    public RequestPrimitive getInitilazedRequestPrimitive() {
        return RequestPrimitiveFactory.makeDefaultRequestPrimitive();
    }

    public RequestPrimitive getEmptyRequestPrimitive() {
        return new RequestPrimitive();
    }

    public RequestPrimitive getInitilazedCreateRequestPrimitive() {
        RequestPrimitive rp = RequestPrimitiveFactory.makeDefaultRequestPrimitive();
        rp.setOperation(Operation.CREATE.getValue());
        rp.setFilterCriteria(null);
        rp.setDiscoveryResultType(null);
        return rp;
    }

    public RequestPrimitive getInitilazedRetrieveRequestPrimitive() {
        RequestPrimitive rp = RequestPrimitiveFactory.makeDefaultRequestPrimitive();
        rp.setOperation(Operation.RETRIEVE.getValue());
        rp.setResourceType(null);
        rp.setName(null);
        return rp;
    }

    public RequestPrimitive getInitilazedUpdateRequestPrimitive() {
        RequestPrimitive rp = RequestPrimitiveFactory.makeDefaultRequestPrimitive();
        rp.setOperation(Operation.UPDATE.getValue());
        rp.setResourceType(null);
        rp.setName(null);
        rp.setDiscoveryResultType(null);
        return rp;
    }

    public RequestPrimitive getInitilazedDeleteRequestPrimitive() {
        RequestPrimitive rp = RequestPrimitiveFactory.makeDefaultRequestPrimitive();
        rp.setOperation(Operation.DELETE.getValue());
        rp.setResourceType(null);
        rp.setName(null);
        rp.setContent(null);
        rp.setDiscoveryResultType(null);
        return rp;
    }

    public RequestPrimitive getInitilazedNotifyRequestPrimitive() {
        RequestPrimitive rp = RequestPrimitiveFactory.makeDefaultRequestPrimitive();
        rp.setOperation(Operation.NOTIFY.getValue());
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

                    if (newValue != null && clazz.equals(BigInteger.class))
                        method.invoke(object, new BigInteger(newValue.toString()));
                    else if (newValue != null && clazz.equals(String.class))
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
        Http http = new Http();
        http.start();
        System.out.println("Request:");
        String rst = GsonUtil.jsonToPrettyJson(http.sendRequestAndGetResponse(requestPrimitive, host, port));
        System.out.print("\n\n");
        System.out.println("Response:");
        System.out.println(rst);
        http.close();
        return rst;
    }

    public String getHierURI (String output){
        try{
            String[] inputlist = output.split("path:");
            // String[1] = InCSE1/10057,Non-hierarchical

            String[] hierlist = inputlist[1].split(",");
            System.out.println(hierlist[0]);
            return hierlist[0];
        }catch(Exception pe){
            System.out.println("Cannot find path in:" + output);
            System.out.println(pe);
            return null;
        }
    }

    public String getNonHierURI (String output){
        try{
            String[] inputlist = output.split("path:");
            // String[2] = InCSE1/1609248733" ...

            String[] hierlist = inputlist[2].split("\"");
            System.out.println(hierlist[0]);
            return hierlist[0];
        }catch(Exception pe){
            System.out.println("Cannot find path in:" + output);
            System.out.println(pe);
            return null;
        }
    }
    // test the for loops in the robot framework?
}
