package junit;


import org.junit.After;
import org.junit.Test;
import org.opendaylight.iotdm.constant.onem2m.OneM2M;
import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.primitive.ResponsePrimitive;
import org.opendaylight.iotdm.robot.iotdm.Iotdm;

import java.math.BigInteger;


/**
 * Created by wenxshi on 4/17/15.
 */
public class CreateTest {
    private static final String RESOURCE_NAME = "test";
    private static final String PATH = "/InCSE1/" + RESOURCE_NAME;
    private static Iotdm iotdm = new Iotdm();


    @After
    public void clean_Resource_Tree() {
        System.out.println("//////////////////////////////////After//////////////////////////////////////");
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);
        iotdm.sendRequestAndGetResponse(deleteRequest);
    }

    private RequestPrimitive createRequest() {
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setName(RESOURCE_NAME);
        return createRequest;
    }

    @Test
    public void to__Is__Null() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setTo(null);

        ResponsePrimitive responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NOT_FOUND.value(), responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void to__Is__Invalid() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setTo("/abcjd");

        ResponsePrimitive responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NOT_FOUND.value(), responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void to__Is__Valid() {
        RequestPrimitive createRequest = createRequest();

        ResponsePrimitive responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), responsePrimitive);
        //TODO valid the content
    }

    @Test
    public void from_Is_Null() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setFrom(null);

        ResponsePrimitive responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void from_Is_Invalid() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setFrom("abcde");

        ResponsePrimitive responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void from_Is_Valid() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setFrom("http://localhost");

        ResponsePrimitive responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void requestIdentifer_Is_Null() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setRequestIdentifier(null);

        ResponsePrimitive responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void resourceType_Is_Null() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setResourceType(null);

        ResponsePrimitive responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void resourceType_Is_Invalid() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setResourceType(BigInteger.valueOf(-2));

        ResponsePrimitive responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), responsePrimitive);
        //TODO check with error message in content
    }


    @Test
    public void resourceType_Is_Valid() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setResourceType(OneM2M.ResourceType.CONTAINER.value());

        ResponsePrimitive responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), responsePrimitive);
        //TODO valid content
    }


    @Test
    public void name_Is_Null() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setName(null);

        ResponsePrimitive responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), responsePrimitive);
    }

    @Test
    public void name_Is_Duplicated() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setName("Hello");

        ResponsePrimitive responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);

        responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CONFLICT.value(), responsePrimitive);
        //todo duplicate name error
    }

    @Test
    public void name_Is_Valid() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setName("World");

        ResponsePrimitive responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), responsePrimitive);
    }


    @Test
    public void content_Is_Null() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setContent(null);

        ResponsePrimitive responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CONTENTS_UNACCEPTABLE.value(), responsePrimitive);
    }

    @Test
    public void content_Is_Inconsist_With_ResourceType() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setResourceType(OneM2M.ResourceType.AE.value());

        ResponsePrimitive responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CONTENTS_UNACCEPTABLE.value(), responsePrimitive);
        //TODO check with error message in content
    }

    @Test
    public void content_Is_Valid() {
        RequestPrimitive createRequest = createRequest();

        ResponsePrimitive responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), responsePrimitive);
    }

    @Test
    public void resultContent_Is_Null() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setResultContent(null);

        ResponsePrimitive responsePrimitive = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, responsePrimitive);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), responsePrimitive);
    }

    @Test
    public void resultContent_Is_Invalid() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setResultContent(BigInteger.valueOf(-1));

        ResponsePrimitive createResponse = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, createResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CONTENTS_UNACCEPTABLE.value(), createResponse);
    }

    @Test
    public void resultContent_Is_Nothing() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setResultContent(OneM2M.ResultContent.NOTHING.value());

        ResponsePrimitive createResponse = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, createResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), createResponse);
    }

    @Test
    public void resultContent_Is_Attributes() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES.value());

        ResponsePrimitive createResponse = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, createResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), createResponse);
    }

    @Test
    public void resultContent_Is_Hierarchical_Address() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setResultContent(OneM2M.ResultContent.HIERARCHICAL_ADDRESS.value());

        ResponsePrimitive createResponse = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, createResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), createResponse);
    }


    @Test
    public void resultContent_Is_Hierarchical_Address_And_Attributes() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setResultContent(OneM2M.ResultContent.HIERARCHICAL_ADDRESS_AND_ATTRIBUTES.value());

        ResponsePrimitive createResponse = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, createResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), createResponse);
    }


    @Test
    public void resultContent_Is_Attributes_And_Child_Resources() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES_AND_CHILD_RESOURCES.value());

        ResponsePrimitive createResponse = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, createResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CONTENTS_UNACCEPTABLE.value(), createResponse);
    }

    @Test
    public void resultContent_Is_Attributes_And_Child_Resource_References() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES_AND_CHILD_RESOURCE_REFERENCES.value());

        ResponsePrimitive createResponse = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, createResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), createResponse);
    }

    @Test
    public void resultContent_Is_Child_Resource_References() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setResultContent(OneM2M.ResultContent.CHILD_RESOURCE_REFERENCES.value());

        ResponsePrimitive createResponse = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, createResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CREATED.value(), createResponse);
    }

    @Test
    public void resultContent_Is_Original_Resource() {
        RequestPrimitive createRequest = createRequest();
        createRequest.setResultContent(OneM2M.ResultContent.ORIGINAL_RESOURCE.value());

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(createRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(createRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.CONTENTS_UNACCEPTABLE.value(), retrieveResponse);
    }

}
