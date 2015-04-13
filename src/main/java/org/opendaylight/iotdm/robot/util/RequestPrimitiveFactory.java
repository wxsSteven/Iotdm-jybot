package org.opendaylight.iotdm.robot.util;

import org.opendaylight.iotdm.constant.onem2m.OneM2M;
import org.opendaylight.iotdm.primitive.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by wenxshi on 3/31/15.
 */
public class RequestPrimitiveFactory {
    public static RequestPrimitive makeDefaultRequestPrimitive() {
//PrimitiveContent construction
        PrimitiveContent pc = new PrimitiveContent();
        AE ae=new AE();
        ae.setAEID("AE_ID");
        ae.setAppName("iphone");
        ae.setOntologyRef("http://hey/you");
        pc.getAny().add(ae);
//FilterCriteria Content construction
        FilterCriteria fc = new FilterCriteria();
        fc.setCreatedAfter("12345");
        fc.setCreatedBefore("123344");
        fc.setFilterUsage(BigInteger.ONE);
        fc.getLabels().addAll(Arrays.asList("b", "c", "d"));
        Attribute attribute = new Attribute();
        attribute.setName("key");
        attribute.setValue("value");
        fc.getAttribute().add(attribute);
        attribute = new Attribute();
        attribute.setName("key");
        attribute.setValue("value");
        fc.getAttribute().add(attribute);
//ResponseType construction
        ResponseTypeInfo rti = new ResponseTypeInfo();
        rti.getNotificationURI().add("localhost");
        rti.getNotificationURI().add("localhost");
        rti.setResponseType(BigInteger.ONE);
//RequestPrimitive Constuction
        RequestPrimitive request = new RequestPrimitive();
        request.setOperation(BigInteger.ONE);
        request.setTo("http://localhost:8282/InCSE1");
        request.setFrom("http://localhost:8989");
        request.setRequestIdentifier("1234");
        request.setResourceType(OneM2M.ResourceType.AE.value());
        request.setName("AE");
        request.setContent(pc);
        request.setOriginatingTimestamp("100000");
        request.setRequestExpirationTimestamp("200000");
        request.setResultExpirationTimestamp("200000");
        request.setOperationExecutionTime("100000");
        request.setResponseType(OneM2M.ResponseType.BLOCKING_REQUEST.value());
        try {
            request.setResultPersistence(DatatypeFactory.newInstance().newDuration(10000000));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        request.setResultContent(OneM2M.ResultContent.ATTRIBUTES_AND_CHILD_RESOURCE_REFERENCES.value());
        request.setEventCategory(OneM2M.StdEventCats.DEFAULT.value().toString());
        request.setDeliveryAggregation(true);
        request.setGroupRequestIdentifier("12345");
        request.setFilterCriteria(fc);
        request.setDiscoveryResultType(OneM2M.DiscResType.HIERARCHICAL.value());
        return request;
    }
}
