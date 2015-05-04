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
        RequestPrimitive createRequest=iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setTo(null);

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NOT_FOUND.value(),responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void to__Is__Invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest=iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setTo("/abcjd");

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NOT_FOUND.value(),responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void to__Is__Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest=iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setTo("/InCSE1");

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(),responsePrimitive);
        //TODO valid the content

    }

    @Test
    public void from_Is_Null(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setFrom(null);

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void from_Is_Invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setFrom("abcde");

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void from_Is_Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setFrom("abcde");

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(),responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void requestIdentifer_Is_Null(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setRequestIdentifier(null);

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void resourceType_Is_Null(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setResourceType(null);

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void resourceType_Is_Invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setResourceType(BigInteger.valueOf(-2));

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),responsePrimitive);
        //TODO check with error message in content
    }


    @Test
    public void resourceType_Is_Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setResourceType(OneM2M.ResourceType.CONTAINER.value());

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(),responsePrimitive);
        //TODO valid content
    }


    @Test
    public void name_Is_Null(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setName(null);

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(),responsePrimitive);
    }

    @Test
    public void name_Is_Duplicated(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setName("Hello");

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);

        responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CONFLICT.value(), responsePrimitive);
        //todo duplicate name error
    }

    @Test
    public void name_Is_Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setName("World");

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), responsePrimitive);
    }


    @Test
    public void content_Is_Null(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setContent(null);

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CONTENTS_UNACCEPTABLE.value(), responsePrimitive);
    }

    @Test
    public void content_Is_Inconsist_With_ResourceType(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setResourceType(OneM2M.ResourceType.AE.value());

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest,responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CONTENTS_UNACCEPTABLE.value(),responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void content_Is_Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();

        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), responsePrimitive);
    }

    @Test
    public void resultContent_Is_Null(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setResultContent(null);
        ResponsePrimitive responsePrimitive=iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), responsePrimitive);
    }

    @Test
    public void resultContent_Is_Invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setResultContent(BigInteger.valueOf(-1));

        ResponsePrimitive createResponse=iotdm.sendRequestAndGetResponse(createRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, createResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CONTENTS_UNACCEPTABLE.value(), createResponse);
    }

    @Test
    public void resultContent_Is_Nothing(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setResultContent(OneM2M.ResultContent.NOTHING.value());

        ResponsePrimitive createResponse=iotdm.sendRequestAndGetResponse(createRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, createResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), createResponse);

    }

    @Test
    public void resultContent_Is_Attributes(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES.value());

        ResponsePrimitive createResponse=iotdm.sendRequestAndGetResponse(createRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, createResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), createResponse);

    }

    @Test
    public void resultContent_Is_Hierarchical_Address(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setResultContent(OneM2M.ResultContent.HIERARCHICAL_ADDRESS.value());

        ResponsePrimitive createResponse=iotdm.sendRequestAndGetResponse(createRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, createResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), createResponse);

    }


    @Test
    public void resultContent_Is_Hierarchical_Address_And_Attributes(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setResultContent(OneM2M.ResultContent.HIERARCHICAL_ADDRESS_AND_ATTRIBUTES.value());

        ResponsePrimitive createResponse=iotdm.sendRequestAndGetResponse(createRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, createResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), createResponse);

    }


    @Test
    public void resultContent_Is_Attributes_And_Child_Resources(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES_AND_CHILD_RESOURCES.value());

        ResponsePrimitive createResponse=iotdm.sendRequestAndGetResponse(createRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, createResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CONTENTS_UNACCEPTABLE.value(), createResponse);

    }

    @Test
    public void resultContent_Is_Attributes_And_Child_Resource_References(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES_AND_CHILD_RESOURCE_REFERENCES.value());

        ResponsePrimitive createResponse=iotdm.sendRequestAndGetResponse(createRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, createResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), createResponse);

    }

    @Test
    public void resultContent_Is_Child_Resource_References(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setResultContent(OneM2M.ResultContent.CHILD_RESOURCE_REFERENCES.value());

        ResponsePrimitive createResponse=iotdm.sendRequestAndGetResponse(createRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, createResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), createResponse);

    }

    @Test
    public void resultContent_Is_Original_Resource(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setResultContent(OneM2M.ResultContent.ORIGINAL_RESOURCE.value());

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(createRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CONTENTS_UNACCEPTABLE.value(), retrieveResponse);
    }

}
