package junit;

import org.junit.*;
import org.opendaylight.iotdm.constant.onem2m.OneM2M;
import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.primitive.ResponsePrimitive;
import org.opendaylight.iotdm.robot.iotdm.Iotdm;

import java.math.BigInteger;

/**
 * Created by wenxshi on 5/5/15.
 */
public class DeleteTest {

    private static final String RESOURCE_NAME = "container_delete";
    private static final String PATH = "/InCSE1/" + RESOURCE_NAME;
    private static Iotdm iotdm = new Iotdm();

    @Before
    public void build_Resource_Tree() {
        RequestPrimitive createRequest = iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setName(RESOURCE_NAME);
        iotdm.sendRequestAndGetResponse(createRequest);
    }

    @After
    public void clean_Resource_Tree() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);
        iotdm.sendRequestAndGetResponse(deleteRequest);
    }

    @Test
    public void to__Is__Null() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(null);

        ResponsePrimitive deleteResponse = iotdm.sendRequestAndGetResponse(deleteRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(deleteRequest, deleteResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NOT_FOUND.value(), deleteResponse);
        //TODO check with error message in content
    }


    @Test
    public void to__Is__Invalid() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo("/abcjd");

        ResponsePrimitive deleteResponse = iotdm.sendRequestAndGetResponse(deleteRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(deleteRequest, deleteResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NOT_FOUND.value(), deleteResponse);
        //TODO check with error message in content
    }

    @Test
    public void to__Is__Valid() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);

        ResponsePrimitive deleteResponse = iotdm.sendRequestAndGetResponse(deleteRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(deleteRequest, deleteResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.DELETED.value(), deleteResponse);
        //TODO valid the content

    }

    @Test
    public void from_Is_Null() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);
        deleteRequest.setFrom(null);

        ResponsePrimitive deleteResponse = iotdm.sendRequestAndGetResponse(deleteRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(deleteRequest, deleteResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), deleteResponse);
        //TODO check with error message in content
    }

    @Test
    public void from_Is_Invalid() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);
        deleteRequest.setFrom("abcde");

        ResponsePrimitive deleteResponse = iotdm.sendRequestAndGetResponse(deleteRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(deleteRequest, deleteResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), deleteResponse);
        //TODO check with error message in content
    }

    @Test
    public void from_Is_Valid() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);
        deleteRequest.setFrom("abcde");

        ResponsePrimitive deleteResponse = iotdm.sendRequestAndGetResponse(deleteRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(deleteRequest, deleteResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.DELETED.value(), deleteResponse);
        //TODO check with error message in content
    }

    @Test
    public void requestIdentifer_Is_Null() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);
        deleteRequest.setRequestIdentifier(null);

        ResponsePrimitive deleteResponse = iotdm.sendRequestAndGetResponse(deleteRequest);

        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), deleteResponse);
        //TODO check with error message in content
    }

    @Test
    public void resultContent_Is_Null() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);
        deleteRequest.setResultContent(null);

        ResponsePrimitive deleteResponse = iotdm.sendRequestAndGetResponse(deleteRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(deleteRequest, deleteResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.DELETED.value(), deleteResponse);
    }

    @Test
    public void resultContent_Is_Invalid() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);
        deleteRequest.setResultContent(BigInteger.valueOf(-1));

        ResponsePrimitive deleteResponse = iotdm.sendRequestAndGetResponse(deleteRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(deleteRequest, deleteResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), deleteResponse);
    }

    @Test
    public void resultContent_Is_Nothing() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);
        deleteRequest.setResultContent(OneM2M.ResultContent.NOTHING.value());

        ResponsePrimitive deleteResponse = iotdm.sendRequestAndGetResponse(deleteRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(deleteRequest, deleteResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.DELETED.value(), deleteResponse);

    }

    @Test
    public void resultContent_Is_Attributes() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);
        deleteRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES.value());

        ResponsePrimitive deleteResponse = iotdm.sendRequestAndGetResponse(deleteRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(deleteRequest, deleteResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.DELETED.value(), deleteResponse);

    }

    @Test
    public void resultContent_Is_Hierarchical_Address() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);
        deleteRequest.setResultContent(OneM2M.ResultContent.HIERARCHICAL_ADDRESS.value());

        ResponsePrimitive deleteResponse = iotdm.sendRequestAndGetResponse(deleteRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(deleteRequest, deleteResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), deleteResponse);

    }


    @Test
    public void resultContent_Is_Hierarchical_Address_And_Attributes() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);
        deleteRequest.setResultContent(OneM2M.ResultContent.HIERARCHICAL_ADDRESS_AND_ATTRIBUTES.value());

        ResponsePrimitive deleteResponse = iotdm.sendRequestAndGetResponse(deleteRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(deleteRequest, deleteResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), deleteResponse);

    }


    @Test
    public void resultContent_Is_Attributes_And_Child_Resources() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);
        deleteRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES_AND_CHILD_RESOURCES.value());

        ResponsePrimitive deleteResponse = iotdm.sendRequestAndGetResponse(deleteRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(deleteRequest, deleteResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), deleteResponse);

    }

    @Test
    public void resultContent_Is_Attributes_And_Child_Resource_References() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);
        deleteRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES_AND_CHILD_RESOURCE_REFERENCES.value());

        ResponsePrimitive deleteResponse = iotdm.sendRequestAndGetResponse(deleteRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(deleteRequest, deleteResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.DELETED.value(), deleteResponse);

    }

    @Test
    public void resultContent_Is_Child_Resource_References() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);
        deleteRequest.setResultContent(OneM2M.ResultContent.CHILD_RESOURCE_REFERENCES.value());

        ResponsePrimitive deleteResponse = iotdm.sendRequestAndGetResponse(deleteRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(deleteRequest, deleteResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.DELETED.value(), deleteResponse);

    }

    @Test
    public void resultContent_Is_Original_Resource() {
        RequestPrimitive deleteRequest = iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);
        deleteRequest.setResultContent(OneM2M.ResultContent.ORIGINAL_RESOURCE.value());

        ResponsePrimitive deleteResponse = iotdm.sendRequestAndGetResponse(deleteRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(deleteRequest, deleteResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), deleteResponse);
    }
}
