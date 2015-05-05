package junit;

import org.junit.*;
import org.opendaylight.iotdm.constant.onem2m.OneM2M;
import org.opendaylight.iotdm.primitive.FilterCriteria;
import org.opendaylight.iotdm.primitive.RequestPrimitive;
import org.opendaylight.iotdm.primitive.ResponsePrimitive;
import org.opendaylight.iotdm.primitive.ResponseTypeInfo;
import org.opendaylight.iotdm.robot.iotdm.Iotdm;
import org.opendaylight.iotdm.robot.util.Onem2mDateTime;

import java.math.BigInteger;

/**
 * Created by wenxshi on 5/5/15.
 */
public class UpdateTest {

    private static final String RESOURCE_NAME="container_update";
    private static final String PATH="/InCSE1/"+RESOURCE_NAME;
    private static Iotdm iotdm = new Iotdm();
    private static BigInteger RESULT_CONTENT=OneM2M.ResultContent.ATTRIBUTES.value();

    @Before
    public void createResource(){
        RequestPrimitive createRequest=iotdm.getInitilazedCreateRequestPrimitive();
        createRequest.setName(RESOURCE_NAME);
        iotdm.sendRequestAndGetResponse(createRequest);
    }
    @After
    public void cleanResource(){
        RequestPrimitive deleteRequest=iotdm.getInitilazedDeleteRequestPrimitive();
        deleteRequest.setTo(PATH);
        iotdm.sendRequestAndGetResponse(deleteRequest);
    }

    private ResponsePrimitive retrieveResponse(){
        RequestPrimitive retrieveRequestPrimitive=iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequestPrimitive.setTo(PATH);
        retrieveRequestPrimitive.setResultContent(RESULT_CONTENT);
        return iotdm.sendRequestAndGetResponse(retrieveRequestPrimitive);
    }

    private RequestPrimitive updateRequest(){
        RequestPrimitive updateRequest=iotdm.getInitilazedUpdateRequestPrimitive();
        updateRequest.setResultContent(RESULT_CONTENT);
        return updateRequest;
    }

    @Test
    public void to__Is__Null(){
        ResponsePrimitive retrieveResponse=retrieveResponse();

        RequestPrimitive updateRequest=updateRequest();
        updateRequest.setTo(null);

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NOT_FOUND.value(), updateResponse);
        //TODO check with error message in content
    }



    @Test
    public void to__Is__Invalid(){
        ResponsePrimitive retrieveResponse=retrieveResponse();

        RequestPrimitive updateRequest=updateRequest();
        updateRequest.setTo("/abcjd");

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NOT_FOUND.value(), updateResponse);
        //TODO check with error message in content
    }

    @Test
    public void to__Is__Valid(){
        ResponsePrimitive retrieveResponse=retrieveResponse();

        RequestPrimitive updateRequest=updateRequest();
        updateRequest.setTo(PATH);

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest,updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(),updateResponse);
        //TODO valid the content

    }

    @Test
    public void from_Is_Null(){
        ResponsePrimitive retrieveResponse=retrieveResponse();

        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        updateRequest.setFrom(null);

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest,updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),updateResponse);
        //TODO check with error message in content
    }

    @Test
    public void from_Is_Invalid(){
        ResponsePrimitive retrieveResponse=retrieveResponse();

        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        updateRequest.setFrom("abcde");

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest,updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NOT_FOUND.value(),updateResponse);
        //TODO check with error message in content
    }

    @Test
    public void from_Is_Valid(){
        ResponsePrimitive retrieveResponse=retrieveResponse();
        
        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        updateRequest.setFrom("abcde");

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest,updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(),updateResponse);
        //TODO check with error message in content
    }

    @Test
    public void requestIdentifer_Is_Null(){
        ResponsePrimitive retrieveResponse=retrieveResponse();
        
        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        updateRequest.setRequestIdentifier(null);

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);

        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),updateResponse);
        //TODO check with error message in content
    }

    @Test
    public void responseType_Is_Null(){
        ResponsePrimitive retrieveResponse=retrieveResponse();
        
        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        updateRequest.setResponseType(null);

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);
    }

    @Test
    public void responseType_Is_Invalid(){
        ResponsePrimitive retrieveResponse=retrieveResponse();
        
        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        ResponseTypeInfo info=new ResponseTypeInfo();
        info.setResponseType(BigInteger.valueOf(-1));
        updateRequest.setResponseType(info);

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);
    }

    @Test
    public void responseType_Is_Blocking_Request(){
        ResponsePrimitive retrieveResponse=retrieveResponse();
        
        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        ResponseTypeInfo info=new ResponseTypeInfo();
        info.setResponseType(OneM2M.ResponseType.BLOCKING_REQUEST.value());
        updateRequest.setResponseType(info);

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);
    }

    @Test
    public void responseType_Is_Non_Blocking_Request_Asynch(){
        ResponsePrimitive retrieveResponse=retrieveResponse();
        
        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        ResponseTypeInfo info=new ResponseTypeInfo();
        info.setResponseType(OneM2M.ResponseType.NON_BLOCKING_REQUEST_ASYNCH.value());
        updateRequest.setResponseType(info);

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NON_BLOCKING_REQUEST_NOT_SUPPORTED.value(), updateResponse);
    }

    @Test
    public void responseType_Is_Non_Blocking_Request_Synch(){
        ResponsePrimitive retrieveResponse=retrieveResponse();
        
        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        ResponseTypeInfo info=new ResponseTypeInfo();
        info.setResponseType(OneM2M.ResponseType.NON_BLOCKING_REQUEST_SYNCH.value());
        updateRequest.setResponseType(info);

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NON_BLOCKING_REQUEST_NOT_SUPPORTED.value(), updateResponse);
    }

    @Test
    public void resultContent_Is_Null(){
        ResponsePrimitive retrieveResponse=retrieveResponse();
        
        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        updateRequest.setResultContent(null);

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);
    }

    @Test
    public void resultContent_Is_Invalid(){
        ResponsePrimitive retrieveResponse=retrieveResponse();
        
        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        updateRequest.setResultContent(BigInteger.valueOf(-1));

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);
    }

    @Test
    public void resultContent_Is_Nothing(){
        RESULT_CONTENT=OneM2M.ResultContent.NOTHING.value();
        ResponsePrimitive retrieveResponse=retrieveResponse();
        
        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        updateRequest.setResultContent(OneM2M.ResultContent.NOTHING.value());

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);

    }

    @Test
    public void resultContent_Is_Attributes(){
        RESULT_CONTENT=OneM2M.ResultContent.ATTRIBUTES.value();
        ResponsePrimitive retrieveResponse=retrieveResponse();
        
        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        updateRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES.value());

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);

    }

    @Test
    public void resultContent_Is_Hierarchical_Address(){
        RESULT_CONTENT=OneM2M.ResultContent.HIERARCHICAL_ADDRESS.value();
        ResponsePrimitive retrieveResponse=retrieveResponse();
        
        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        updateRequest.setResultContent(OneM2M.ResultContent.HIERARCHICAL_ADDRESS.value());

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);

    }


    @Test
    public void resultContent_Is_Hierarchical_Address_And_Attributes(){
        RESULT_CONTENT=OneM2M.ResultContent.HIERARCHICAL_ADDRESS_AND_ATTRIBUTES.value();
        ResponsePrimitive retrieveResponse=retrieveResponse();
        
        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        updateRequest.setResultContent(OneM2M.ResultContent.HIERARCHICAL_ADDRESS_AND_ATTRIBUTES.value());

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);

    }


    @Test
    public void resultContent_Is_Attributes_And_Child_Resources(){
        RESULT_CONTENT=OneM2M.ResultContent.ATTRIBUTES_AND_CHILD_RESOURCES.value();
        ResponsePrimitive retrieveResponse=retrieveResponse();
        
        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        updateRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES_AND_CHILD_RESOURCES.value());

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);

    }

    @Test
    public void resultContent_Is_Attributes_And_Child_Resource_References(){
        RESULT_CONTENT=OneM2M.ResultContent.ATTRIBUTES_AND_CHILD_RESOURCES.value();

        ResponsePrimitive retrieveResponse=retrieveResponse();
        
        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        updateRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES_AND_CHILD_RESOURCE_REFERENCES.value());

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);

    }

    @Test
    public void resultContent_Is_Child_Resource_References(){
        RESULT_CONTENT=OneM2M.ResultContent.CHILD_RESOURCE_REFERENCES.value();

        ResponsePrimitive retrieveResponse=retrieveResponse();
        
        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        updateRequest.setResultContent(OneM2M.ResultContent.CHILD_RESOURCE_REFERENCES.value());

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);

    }

    @Test
    public void resultContent_Is_Original_Resource(){
        RESULT_CONTENT=OneM2M.ResultContent.ORIGINAL_RESOURCE.value();
        ResponsePrimitive retrieveResponse=retrieveResponse();

        RequestPrimitive updateRequest = updateRequest();
        updateRequest.setTo(PATH);
        updateRequest.setResultContent(OneM2M.ResultContent.ORIGINAL_RESOURCE.value());

        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);
    }

//    @Test
//    public void createdBefore_Is_invalid(){
//        ResponsePrimitive retrieveResponse=retrieveResponse();
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setCreatedBefore("dfjajdnf");
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);
//    }
//
//    @Test
//    public void createdBefore_Is_valid(){
//        ResponsePrimitive retrieveResponse=retrieveResponse();
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setCreatedBefore(Onem2mDateTime.getCurrDateTime());
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);
//    }
//
//    @Test
//    public void createdAfter_Is_invalid(){
//        ResponsePrimitive retrieveResponse=retrieveResponse();
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setCreatedAfter("dfjajdnf");
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);
//    }
//
//    @Test
//    public void createdAfter_Is_valid(){
//        ResponsePrimitive retrieveResponse=retrieveResponse();
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setCreatedAfter(Onem2mDateTime.getCurrDateTime());
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);
//    }
//
//    @Test
//    public void modifiedSince_Is_invalid(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setModifiedSince("dfjajdnf");
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);
//    }
//
//    @Test
//    public void modifiedSince_Is_valid(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setModifiedSince(Onem2mDateTime.getCurrDateTime());
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);
//    }
//
//    @Test
//    public void unmodifiedSince_Is_invalid(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setUnmodifiedSince("dfjajdnf");
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);
//    }
//
//    @Test
//    public void unmodifiedSince_Is_Valid(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setUnmodifiedSince(Onem2mDateTime.getCurrDateTime());
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);
//    }
//
//    @Test
//    public void  stateTagSmaller_Is_Invalid(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setStateTagSmaller(BigInteger.valueOf(-1));
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);
//
//    }
//
//    @Test
//    public void  stateTagSmaller_Is_Valid(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setStateTagSmaller(BigInteger.TEN);
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);
//
//    }
//
//    @Test
//    public void  stateTagBigger_Is_Invalid(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setStateTagBigger(BigInteger.valueOf(-1));
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);
//
//    }
//
//    @Test
//    public void  stateTagBigger_Is_Valid(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setStateTagBigger(BigInteger.TEN);
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);
//
//    }
//
//    @Test
//    public void expireBefore_Is_invalid(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setExpireBefore("dfjajdnf");
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);
//    }
//
//    @Test
//    public void expireBefore_Is_Valid(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setExpireBefore(Onem2mDateTime.getCurrDateTime());
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);
//    }
//
//    @Test
//    public void expireAfter_Is_invalid(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setExpireAfter("dfjajdnf");
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);
//    }
//
//    @Test
//    public void labels_Is_Iphone(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.getLabels().add("iphone");
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);
//    }
//
//    @Test
//    public void labels_Is_Iphone_And_Ipad(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.getLabels().add("iphone");
//        fc.getLabels().add("ipad");
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);
//    }
//
//    @Test
//    public void filterCriteria_resourceType_Is_Invalid(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setResourceType(BigInteger.valueOf(-1));
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);
//    }
//
//    @Test
//    public void filterCriteria_resourceType_Is_Container(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setResourceType(OneM2M.ResourceType.CONTAINER.value());
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);
//    }
//
//    @Test
//    public void filterCriteria_resourceType_Is_ContentInstance(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setResourceType(OneM2M.ResourceType.CONTENT_INSTANCE.value());
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);
//    }
//
//    @Test
//    public void sizeAbove_Is_invalid(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setSizeAbove(BigInteger.valueOf(-1));
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);
//    }
//
//    @Test
//    public void sizeAbove_Is_Valid(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setSizeAbove(BigInteger.valueOf(1));
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);
//    }
//
//    @Test
//    public void sizeBelow_Is_invalid(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setSizeBelow(BigInteger.valueOf(-1));
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), updateResponse);
//    }
//
//    @Test
//    public void sizeBelow_Is_Valid(){
//
//        RequestPrimitive updateRequest = updateRequest();
//        updateRequest.setTo(PATH);
//        FilterCriteria fc=new FilterCriteria();
//
//        fc.setSizeBelow(BigInteger.valueOf(1));
//
//        updateRequest.setFilterCriteria(fc);
//        ResponsePrimitive updateResponse=iotdm.sendRequestAndGetResponse(updateRequest);
//        OneM2M.Assert.assertEqualRequestIdentifer(updateRequest, updateResponse);
//        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), updateResponse);
//    }
}
