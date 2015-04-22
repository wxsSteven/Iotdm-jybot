package junit;


import org.opendaylight.iotdm.constant.onem2m.OneM2M;
import org.opendaylight.iotdm.primitive.FilterCriteria;
import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.primitive.ResponsePrimitive;
import org.opendaylight.iotdm.robot.iotdm.Iotdm;
import org.junit.Test;

import java.math.BigInteger;
import java.util.logging.FileHandler;


/**
 * Created by wenxshi on 4/17/15.
 */
public class CreateTest {

    @Test
    public void to__Is__Null(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive requestPrimitive=iotdm.getInitilazedCreateRequestPrimitive();
        requestPrimitive.setTo(null);

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(requestPrimitive);

        OneM2M.Assert.assertEqualRequestIdentifer(requestPrimitive,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.ACCESS_DENIED.value(),responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void to__Is__Invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive requestPrimitive=iotdm.getInitilazedCreateRequestPrimitive();
        requestPrimitive.setTo("/abcjd");

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(requestPrimitive);

        OneM2M.Assert.assertEqualRequestIdentifer(requestPrimitive,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.ACCESS_DENIED.value(),responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void to__Is__Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive requestPrimitive=iotdm.getInitilazedCreateRequestPrimitive();
        requestPrimitive.setTo("/InCSE1");

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(requestPrimitive);

        OneM2M.Assert.assertEqualRequestIdentifer(requestPrimitive,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.ACCESS_DENIED.value(),responsePrimitive);
        //TODO valid the content

    }

    @Test
    public void from_Is_Null(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive requestPrimitive = iotdm.getInitilazedCreateRequestPrimitive();
        requestPrimitive.setFrom(null);

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(requestPrimitive);

        OneM2M.Assert.assertEqualRequestIdentifer(requestPrimitive,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void from_Is_Invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive requestPrimitive = iotdm.getInitilazedCreateRequestPrimitive();
        requestPrimitive.setFrom("abcde");

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(requestPrimitive);

        OneM2M.Assert.assertEqualRequestIdentifer(requestPrimitive,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void from_Is_Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive requestPrimitive = iotdm.getInitilazedCreateRequestPrimitive();
        requestPrimitive.setFrom("abcde");

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(requestPrimitive);

        OneM2M.Assert.assertEqualRequestIdentifer(requestPrimitive,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void requestIdentifer_Is_Null(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive requestPrimitive = iotdm.getInitilazedCreateRequestPrimitive();
        requestPrimitive.setRequestIdentifier(null);

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(requestPrimitive);

        OneM2M.Assert.assertEqualRequestIdentifer(requestPrimitive,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void resourceType_Is_Null(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive requestPrimitive = iotdm.getInitilazedCreateRequestPrimitive();
        requestPrimitive.setResourceType(null);

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(requestPrimitive);

        OneM2M.Assert.assertEqualRequestIdentifer(requestPrimitive,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void resourceType_Is_Invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive requestPrimitive = iotdm.getInitilazedCreateRequestPrimitive();
        requestPrimitive.setResourceType(BigInteger.valueOf(-2));

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(requestPrimitive);

        OneM2M.Assert.assertEqualRequestIdentifer(requestPrimitive,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),responsePrimitive);
        //TODO check with error message in content
    }


    @Test
    public void resourceType_Is_Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive requestPrimitive = iotdm.getInitilazedCreateRequestPrimitive();
        requestPrimitive.setResourceType(OneM2M.ResourceType.CONTAINER.value());

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(requestPrimitive);

        OneM2M.Assert.assertEqualRequestIdentifer(requestPrimitive,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),responsePrimitive);
        //TODO valid content
    }


    @Test
    public void name_Is_Null(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive requestPrimitive = iotdm.getInitilazedCreateRequestPrimitive();
        requestPrimitive.setName(null);

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(requestPrimitive);
        OneM2M.Assert.assertEqualRequestIdentifer(requestPrimitive, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),responsePrimitive);
    }

    @Test
    public void name_Is_Duplicated(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive requestPrimitive = iotdm.getInitilazedCreateRequestPrimitive();
        requestPrimitive.setName("Hello");

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(requestPrimitive);

        responsePrimitive=iotdm.sendRequestAndGetResponse(requestPrimitive);

        OneM2M.Assert.assertEqualRequestIdentifer(requestPrimitive, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CONFLICT.value(), responsePrimitive);
        //todo duplicate name error
    }

    @Test
    public void name_Is_Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive requestPrimitive = iotdm.getInitilazedCreateRequestPrimitive();
        requestPrimitive.setName("World");

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(requestPrimitive);

        OneM2M.Assert.assertEqualRequestIdentifer(requestPrimitive, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), responsePrimitive);
    }


    @Test
    public void content_Is_Null(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive requestPrimitive = iotdm.getInitilazedCreateRequestPrimitive();
        requestPrimitive.setContent(null);

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(requestPrimitive);

        OneM2M.Assert.assertEqualRequestIdentifer(requestPrimitive, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), responsePrimitive);
    }

    @Test
    public void content_Is_Inconsist_With_ResourceType(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive requestPrimitive = iotdm.getInitilazedCreateRequestPrimitive();
        requestPrimitive.setResourceType(OneM2M.ResourceType.AE.value());

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(requestPrimitive);

        OneM2M.Assert.assertEqualRequestIdentifer(requestPrimitive,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void content_Is_Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive requestPrimitive = iotdm.getInitilazedCreateRequestPrimitive();

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(requestPrimitive);

        OneM2M.Assert.assertEqualRequestIdentifer(requestPrimitive, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), responsePrimitive);
    }

}
