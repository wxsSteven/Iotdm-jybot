package junit;

import junit.util.ResourceTree;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opendaylight.iotdm.constant.onem2m.OneM2M;
import org.opendaylight.iotdm.primitive.FilterCriteria;
import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.primitive.ResponsePrimitive;
import org.opendaylight.iotdm.primitive.ResponseTypeInfo;
import org.opendaylight.iotdm.robot.iotdm.Iotdm;
import org.opendaylight.iotdm.robot.util.Onem2mDateTime;

import java.math.BigInteger;

/**
 * Created by wenxshi on 5/1/15.
 */
public class RetrieveTest {

    private static Iotdm iotdm = new Iotdm();

    @BeforeClass
    public static void build_Resource_Tree() throws InterruptedException {
        iotdm.provision();
        ResourceTree.buildResourceTree();
    }

    @AfterClass
    public static void clean_Resource_Tree() {
        iotdm.cleanUpStore();
    }

    @Test
    public void to__Is__Null() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(null);

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NOT_FOUND.value(), retrieveResponse);
        //TODO check with error message in content
    }


    @Test
    public void to__Is__Invalid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/abcjd");

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NOT_FOUND.value(), retrieveResponse);
        //TODO check with error message in content
    }

    @Test
    public void to__Is__Valid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
        //TODO valid the content

    }

    @Test
    public void from_Is_Null() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        retrieveRequest.setFrom(null);

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
        //TODO check with error message in content
    }

    @Test
    public void from_Is_Invalid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        retrieveRequest.setFrom("abcde");

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NOT_FOUND.value(), retrieveResponse);
        //TODO check with error message in content
    }

    @Test
    public void from_Is_Valid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        retrieveRequest.setFrom("abcde");

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
        //TODO check with error message in content
    }

    @Test
    public void requestIdentifer_Is_Null() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        retrieveRequest.setRequestIdentifier(null);

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);

        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
        //TODO check with error message in content
    }

    @Test
    public void responseType_Is_Null() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        retrieveRequest.setResponseType(null);

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void responseType_Is_Invalid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        ResponseTypeInfo info = new ResponseTypeInfo();
        info.setResponseType(BigInteger.valueOf(-1));
        retrieveRequest.setResponseType(info);

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void responseType_Is_Blocking_Request() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        ResponseTypeInfo info = new ResponseTypeInfo();
        info.setResponseType(OneM2M.ResponseType.BLOCKING_REQUEST.value());
        retrieveRequest.setResponseType(info);

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void responseType_Is_Non_Blocking_Request_Asynch() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        ResponseTypeInfo info = new ResponseTypeInfo();
        info.setResponseType(OneM2M.ResponseType.NON_BLOCKING_REQUEST_ASYNCH.value());
        retrieveRequest.setResponseType(info);

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NON_BLOCKING_REQUEST_NOT_SUPPORTED.value(), retrieveResponse);
    }

    @Test
    public void responseType_Is_Non_Blocking_Request_Synch() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        ResponseTypeInfo info = new ResponseTypeInfo();
        info.setResponseType(OneM2M.ResponseType.NON_BLOCKING_REQUEST_SYNCH.value());
        retrieveRequest.setResponseType(info);

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NON_BLOCKING_REQUEST_NOT_SUPPORTED.value(), retrieveResponse);
    }

    @Test
    public void resultContent_Is_Null() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        retrieveRequest.setResultContent(null);

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void resultContent_Is_Invalid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        retrieveRequest.setResultContent(BigInteger.valueOf(-1));

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void resultContent_Is_Nothing() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        retrieveRequest.setResultContent(OneM2M.ResultContent.NOTHING.value());

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);

    }

    @Test
    public void resultContent_Is_Attributes() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        retrieveRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES.value());

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);

    }

    @Test
    public void resultContent_Is_Hierarchical_Address() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        retrieveRequest.setResultContent(OneM2M.ResultContent.HIERARCHICAL_ADDRESS.value());

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);

    }


    @Test
    public void resultContent_Is_Hierarchical_Address_And_Attributes() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        retrieveRequest.setResultContent(OneM2M.ResultContent.HIERARCHICAL_ADDRESS_AND_ATTRIBUTES.value());

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);

    }


    @Test
    public void resultContent_Is_Attributes_And_Child_Resources() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        retrieveRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES_AND_CHILD_RESOURCES.value());

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);

    }

    @Test
    public void resultContent_Is_Attributes_And_Child_Resource_References() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        retrieveRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES_AND_CHILD_RESOURCE_REFERENCES.value());

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);

    }

    @Test
    public void resultContent_Is_Child_Resource_References() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        retrieveRequest.setResultContent(OneM2M.ResultContent.CHILD_RESOURCE_REFERENCES.value());

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);

    }

    @Test
    public void resultContent_Is_Original_Resource() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        retrieveRequest.setResultContent(OneM2M.ResultContent.ORIGINAL_RESOURCE.value());

        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void createdBefore_Is_invalid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setCreatedBefore("dfjajdnf");

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void createdBefore_Is_valid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setCreatedBefore(Onem2mDateTime.getCurrDateTime());

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void createdAfter_Is_invalid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setCreatedAfter("dfjajdnf");

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void createdAfter_Is_valid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setCreatedAfter(Onem2mDateTime.getCurrDateTime());

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void modifiedSince_Is_invalid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setModifiedSince("dfjajdnf");

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void modifiedSince_Is_valid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setModifiedSince(Onem2mDateTime.getCurrDateTime());

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void unmodifiedSince_Is_invalid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setUnmodifiedSince("dfjajdnf");

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void unmodifiedSince_Is_Valid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setUnmodifiedSince(Onem2mDateTime.getCurrDateTime());

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void stateTagSmaller_Is_Invalid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setStateTagSmaller(BigInteger.valueOf(-1));

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);

    }

    @Test
    public void stateTagSmaller_Is_Valid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setStateTagSmaller(BigInteger.TEN);

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);

    }

    @Test
    public void stateTagBigger_Is_Invalid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setStateTagBigger(BigInteger.valueOf(-1));

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);

    }

    @Test
    public void stateTagBigger_Is_Valid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setStateTagBigger(BigInteger.TEN);

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);

    }

    @Test
    public void expireBefore_Is_invalid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setExpireBefore("dfjajdnf");

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void expireBefore_Is_Valid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setExpireBefore(Onem2mDateTime.getCurrDateTime());

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void expireAfter_Is_invalid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setExpireAfter("dfjajdnf");

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void labels_Is_Iphone() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.getLabels().add("iphone");

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void labels_Is_Iphone_And_Ipad() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.getLabels().add("iphone");
        fc.getLabels().add("ipad");

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void filterCriteria_resourceType_Is_Invalid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setResourceType(BigInteger.valueOf(-1));

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void filterCriteria_resourceType_Is_Container() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setResourceType(OneM2M.ResourceType.CONTAINER.value());

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void filterCriteria_resourceType_Is_ContentInstance() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setResourceType(OneM2M.ResourceType.CONTENT_INSTANCE.value());

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void sizeAbove_Is_invalid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setSizeAbove(BigInteger.valueOf(-1));

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void sizeAbove_Is_Valid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setSizeAbove(BigInteger.valueOf(1));

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void sizeBelow_Is_invalid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setSizeBelow(BigInteger.valueOf(-1));

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void sizeBelow_Is_Valid() {

        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(ResourceTree.PATH);
        FilterCriteria fc = new FilterCriteria();

        fc.setSizeBelow(BigInteger.valueOf(1));

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse = iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }
}
