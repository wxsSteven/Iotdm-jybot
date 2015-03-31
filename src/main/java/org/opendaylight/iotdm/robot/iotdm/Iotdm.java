package org.opendaylight.iotdm.robot.iotdm;

import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.robot.plugin.Http;
import org.opendaylight.iotdm.robot.util.GsonUtil;
import org.opendaylight.iotdm.robot.util.RequestPrimitiveFactory;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public class Iotdm {
    public static final String HOST="localhost:8282";

    /**
     {
        "operation": 1,
        "to": "http://localhost:8282/InCSE1",
        "from": "http://localhost:8989",
        "requestIdentifier": "1234",
        "resourceType": 2,
        "name": "AE",
        "content": {
            "aName": "aValue",
            "bValue": "bValue"
        },
        "originatingTimestamp": "100000",
        "requestExpirationTimestamp": "200000",
        "resultExpirationTimestamp": "200000",
        "operationExecutionTime": "100000",
        "responseType": 3,
        "resultPersistence": {
           "signum": 1,
            "years": 0,
            "months": 0,
            "days": 0,
            "hours": 2,
            "minutes": 46,
            "seconds": 40.000
        },
        "resultContent": 5,
        "eventCategory": "Default",
        "deliveryAggregation": true,
        "groupRequestIdentifier": "12345",
        "filterCriteria": {
            "createdBefore": "123344",
            "createdAfter": "12345",
            "labels": ["b","c","d"],
            "attribute": [
                {
                    "name": "key",
                    "value": "value"
                },
                {
                    "name": "key",
                    "value": "value"
                }
            ],
            "filterUsage": 1
        },
        "discoveryResultType": 1
     }
     * @return
     */

    public RequestPrimitive getInitilazedRequestPrimitive(){
        return RequestPrimitiveFactory.makeDefaultRequestPrimitive();
    }

    public RequestPrimitive getEmptyRequestPrimitive(){
        return new RequestPrimitive();
    }

    public RequestPrimitive getInitilazedCreateRequestPrimitive(){
        RequestPrimitive rp=RequestPrimitiveFactory.makeDefaultRequestPrimitive();
        rp.setFilterCriteria(null);
        rp.setDiscoveryResultType(null);
        return rp;
    }

    public RequestPrimitive getInitilazedRetrieveRequestPrimitive(){
        RequestPrimitive rp=RequestPrimitiveFactory.makeDefaultRequestPrimitive();
        rp.setResourceType(null);
        rp.setName(null);
        return rp;
    }

    public RequestPrimitive getInitilazedUpdateRequestPrimitive(){
        RequestPrimitive rp=RequestPrimitiveFactory.makeDefaultRequestPrimitive();
        rp.setResourceType(null);
        rp.setName(null);
        rp.setDiscoveryResultType(null);
        return rp;
    }

    public RequestPrimitive getInitilazedDeleteRequestPrimitive(){
        RequestPrimitive rp=RequestPrimitiveFactory.makeDefaultRequestPrimitive();
        rp.setResourceType(null);
        rp.setName(null);
        rp.setContent(null);
        rp.setDiscoveryResultType(null);
        return rp;
    }

    public RequestPrimitive getInitilazedNotifyRequestPrimitive(){
        RequestPrimitive rp=RequestPrimitiveFactory.makeDefaultRequestPrimitive();
        rp.setResourceType(null);
        rp.setName(null);
        rp.setResultPersistence(null);
        rp.setResultContent(null);
        rp.setFilterCriteria(null);
        rp.setDiscoveryResultType(null);
        return rp;
    }


    public String sendRequestAndGetResponse(RequestPrimitive requestPrimitive){
        Http http=new Http();
        http.start();
        System.out.println("Request:");
        String rst=GsonUtil.jsonToPrettyJson(http.sendRequestAndGetResponse(requestPrimitive));
        System.out.print("\n\n");
        System.out.println("Response:");
        System.out.println(rst);
        http.close();
        return rst;
    }

    public BigInteger toBigInteger(String b){
        return new BigInteger(b);
    }
}
