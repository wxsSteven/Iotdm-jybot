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
        Container container=new Container();
        container.setOntologyRef("icloud");
        container.setCreator("iphone");
        pc.getAny().add(container);
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
        rti.getNotificationURI().add("localhost1");
        rti.getNotificationURI().add("localhost2");
        rti.setResponseType(OneM2M.ResponseType.BLOCKING_REQUEST.value());
//RequestPrimitive Constuction
        RequestPrimitive request = new RequestPrimitive();

        request.setOperation(OneM2M.Operation.CREATE.value());
        request.setTo("http://localhost:8282/InCSE1");
        request.setFrom("http://localhost:8989");
        request.setRequestIdentifier("1234");
        request.setResourceType(OneM2M.ResourceType.CONTAINER.value());
        request.setContent(pc);
        request.setOriginatingTimestamp(Onem2mDateTime.getCurrDateTime());
        request.setRequestExpirationTimestamp(Onem2mDateTime.getCurrDateTime());
        request.setResultExpirationTimestamp(Onem2mDateTime.getCurrDateTime());
        request.setOperationExecutionTime(Onem2mDateTime.getCurrDateTime());
        request.setResponseType(rti);
        try {
            request.setResultPersistence(DatatypeFactory.newInstance().newDuration(10000000));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        request.setResultContent(OneM2M.ResultContent.ATTRIBUTES.value());
        request.setEventCategory(OneM2M.StdEventCats.DEFAULT.value().toString());
        request.setDeliveryAggregation(true);
        request.setGroupRequestIdentifier("12345");
        request.setFilterCriteria(fc);
        request.setDiscoveryResultType(OneM2M.DiscResType.HIERARCHICAL.value());
        return request;
    }
}
