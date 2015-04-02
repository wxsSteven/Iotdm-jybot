package org.opendaylight.iotdm.robot.util;

import org.opendaylight.iotdm.onem2m.core.constant.OneM2MName;
import org.opendaylight.iotdm.constant.enumeration.*;
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
        Attribute attr = new Attribute();
        attr.setName("aName");
        attr.setValue("aValue");
        pc.getAny().add(attr);
        attr = new Attribute();
        attr.setName("bValue");
        attr.setValue("bValue");
        pc.getAny().add(attr);
        attr = new Attribute();
        attr.setName(OneM2MName.LABELS);
        attr.setValue("TV");
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
        request.setResourceType(ResourceType.AE.getValue());
        request.setName("AE");
        request.setContent(pc);
        request.setOriginatingTimestamp("100000");
        request.setRequestExpirationTimestamp("200000");
        request.setResultExpirationTimestamp("200000");
        request.setOperationExecutionTime("100000");
        request.setResponseType(ResponseType.BLOCKING_REQUEST.getValue());
        try {
            request.setResultPersistence(DatatypeFactory.newInstance().newDuration(10000000));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        request.setResultContent(ResultContent.ATTRIBUTES_AND_CHILD_RESOURCE_REFERENCES.getValue());
        request.setEventCategory(StdEventCats.DEFAULT.getInterpretation());
        request.setDeliveryAggregation(true);
        request.setGroupRequestIdentifier("12345");
        request.setFilterCriteria(fc);
        request.setDiscoveryResultType(DiscResType.HIERARCHICAL.getValue());
        return request;
    }
}
