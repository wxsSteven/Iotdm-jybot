package junit;

import org.junit.*;
import org.opendaylight.iotdm.constant.onem2m.OneM2M;
import org.opendaylight.iotdm.primitive.*;
import org.opendaylight.iotdm.robot.iotdm.Iotdm;
import org.opendaylight.iotdm.robot.util.Onem2mDateTime;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wenxshi on 5/1/15.
 */
public class RetrieveTest {
    private static List<String> timeStamp=new LinkedList<>();

    @BeforeClass
    public static void build_Resource_Tree() throws InterruptedException {
        Iotdm iotdm=new Iotdm();
        RequestPrimitive request=iotdm.getInitilazedCreateRequestPrimitive();
        PrimitiveContent content=new PrimitiveContent();
        Container container=new Container();
        ContentInstance contentInstance=new ContentInstance();

        container.getLabels().add("default");
        contentInstance.getLabels().add("default");
        content.getAny().add(container);
        container.setCreator("mac");
        contentInstance.setContent("data");


        request.setName("container");
        request.setTo("/InCSE1");
        request.setResourceType(OneM2M.ResourceType.CONTAINER.value());
        request.setContent(content);
        iotdm.sendRequestAndGetResponse(request);
        timeStamp.add(Onem2mDateTime.getCurrDateTime());
        Thread.sleep(1000);

        request.setName("containerChild1");
        request.setTo("/InCSE1/container");
        request.setResourceType(OneM2M.ResourceType.CONTAINER.value());
        container.getLabels().set(0, "ipad");
        content.getAny().set(0, container);
        iotdm.sendRequestAndGetResponse(request);
        timeStamp.add(Onem2mDateTime.getCurrDateTime());
        Thread.sleep(1000);

        request.setName("containerChild2");
        request.setTo("/InCSE1/container");
        request.setResourceType(OneM2M.ResourceType.CONTAINER.value());
        container.getLabels().set(0, "iphone");
        content.getAny().set(0, container);
        iotdm.sendRequestAndGetResponse(request);
        timeStamp.add(Onem2mDateTime.getCurrDateTime());
        Thread.sleep(1000);

        request.setName("contentInstanceChild1");
        request.setTo("/InCSE1/container");
        request.setResourceType(OneM2M.ResourceType.CONTENT_INSTANCE.value());
        contentInstance.getLabels().set(0, "iphone");
        content.getAny().set(0, contentInstance);
        iotdm.sendRequestAndGetResponse(request);
        timeStamp.add(Onem2mDateTime.getCurrDateTime());
        Thread.sleep(1000);

        request.setName("contentInstanceChild2");
        request.setTo("/InCSE1/container");
        request.setResourceType(OneM2M.ResourceType.CONTENT_INSTANCE.value());
        contentInstance.getLabels().set(0, "iphone");
        content.getAny().set(0, contentInstance);
        iotdm.sendRequestAndGetResponse(request);
        timeStamp.add(Onem2mDateTime.getCurrDateTime());
        Thread.sleep(1000);
    }

    @AfterClass
    public static void clean_Resource_Tree(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive request = iotdm.getInitilazedDeleteRequestPrimitive();
        request.setTo("/InCSE1/container");
        iotdm.sendRequestAndGetResponse(request);
    }


    @Test
    public void to__Is__Null(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest=iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo(null);

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NOT_FOUND.value(), retrieveResponse);
        //TODO check with error message in content
    }



    @Test
    public void to__Is__Invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest=iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/abcjd");

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NOT_FOUND.value(), retrieveResponse);
        //TODO check with error message in content
    }

    @Test
    public void to__Is__Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest=iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest,retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(),retrieveResponse);
        //TODO valid the content

    }

    @Test
    public void from_Is_Null(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        retrieveRequest.setFrom(null);

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest,retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),retrieveResponse);
        //TODO check with error message in content
    }

    @Test
    public void from_Is_Invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        retrieveRequest.setFrom("abcde");

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest,retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.NOT_FOUND.value(),retrieveResponse);
        //TODO check with error message in content
    }

    @Test
    public void from_Is_Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        retrieveRequest.setFrom("abcde");

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);

        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest,retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(),retrieveResponse);
        //TODO check with error message in content
    }

    @Test
    public void requestIdentifer_Is_Null(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        retrieveRequest.setRequestIdentifier(null);

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);

        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(),retrieveResponse);
        //TODO check with error message in content
    }

    @Test
    public void responseType_Is_Null(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        retrieveRequest.setResponseType(null);

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void responseType_Is_Invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        ResponseTypeInfo info=new ResponseTypeInfo();
        info.setResponseType(BigInteger.valueOf(-1));
        retrieveRequest.setResponseType(info);

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void responseType_Is_valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        ResponseTypeInfo info=new ResponseTypeInfo();
        info.setResponseType(BigInteger.valueOf(-1));
        retrieveRequest.setResponseType(info);

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void resultContent_Is_Null(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        retrieveRequest.setResultContent(null);

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void resultContent_Is_Invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        retrieveRequest.setResultContent(BigInteger.valueOf(-1));

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void resultContent_Is_Nothing(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        retrieveRequest.setResultContent(OneM2M.ResultContent.NOTHING.value());

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);

    }

    @Test
    public void resultContent_Is_Attributes(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        retrieveRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES.value());

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);

    }

    @Test
    public void resultContent_Is_Hierarchical_Address(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        retrieveRequest.setResultContent(OneM2M.ResultContent.HIERARCHICAL_ADDRESS.value());

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);

    }


    @Test
    public void resultContent_Is_Hierarchical_Address_And_Attributes(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        retrieveRequest.setResultContent(OneM2M.ResultContent.HIERARCHICAL_ADDRESS_AND_ATTRIBUTES.value());

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);

    }


    @Test
    public void resultContent_Is_Attributes_And_Child_Resources(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        retrieveRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES_AND_CHILD_RESOURCES.value());

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);

    }

    @Test
    public void resultContent_Is_Attributes_And_Child_Resource_References(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        retrieveRequest.setResultContent(OneM2M.ResultContent.ATTRIBUTES_AND_CHILD_RESOURCE_REFERENCES.value());

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);

    }

    @Test
    public void resultContent_Is_Child_Resource_References(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        retrieveRequest.setResultContent(OneM2M.ResultContent.CHILD_RESOURCE_REFERENCES.value());

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);

    }

    @Test
    public void resultContent_Is_Original_Resource(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        retrieveRequest.setResultContent(OneM2M.ResultContent.ORIGINAL_RESOURCE.value());

        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void createdBefore_Is_invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setCreatedBefore("dfjajdnf");

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void createdBefore_Is_valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setCreatedBefore(Onem2mDateTime.getCurrDateTime());

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void createdAfter_Is_invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setCreatedAfter("dfjajdnf");

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void createdAfter_Is_valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setCreatedAfter(Onem2mDateTime.getCurrDateTime());

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void modifiedSince_Is_invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setModifiedSince("dfjajdnf");

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void modifiedSince_Is_valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setModifiedSince(Onem2mDateTime.getCurrDateTime());

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void unmodifiedSince_Is_invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setUnmodifiedSince("dfjajdnf");

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void unmodifiedSince_Is_Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setUnmodifiedSince(Onem2mDateTime.getCurrDateTime());

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void  stateTagSmaller_Is_Invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setStateTagSmaller(BigInteger.valueOf(-1));

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);

    }

    @Test
    public void  stateTagSmaller_Is_Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setStateTagSmaller(BigInteger.TEN);

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);

    }

    @Test
    public void  stateTagBigger_Is_Invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setStateTagBigger(BigInteger.valueOf(-1));

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);

    }

    @Test
    public void  stateTagBigger_Is_Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setStateTagBigger(BigInteger.TEN);

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);

    }

    @Test
    public void expireBefore_Is_invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setExpireBefore("dfjajdnf");

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void expireBefore_Is_Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setExpireBefore(Onem2mDateTime.getCurrDateTime());

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void expireAfter_Is_invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setExpireAfter("dfjajdnf");

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void labels_Is_Iphone(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.getLabels().add("iphone");

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void labels_Is_Iphone_And_Ipad(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.getLabels().add("iphone");
        fc.getLabels().add("ipad");

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void filterCriteria_resourceType_Is_Invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setResourceType(BigInteger.valueOf(-1));

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void filterCriteria_resourceType_Is_Container(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setResourceType(OneM2M.ResourceType.CONTAINER.value());

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void filterCriteria_resourceType_Is_ContentInstance(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setResourceType(OneM2M.ResourceType.CONTENT_INSTANCE.value());

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void sizeAbove_Is_invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setSizeAbove(BigInteger.valueOf(-1));

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void sizeAbove_Is_Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setSizeAbove(BigInteger.valueOf(1));

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }

    @Test
    public void sizeBelow_Is_invalid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setSizeBelow(BigInteger.valueOf(-1));

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.BAD_REQUEST.value(), retrieveResponse);
    }

    @Test
    public void sizeBelow_Is_Valid(){
        Iotdm iotdm=new Iotdm();
        RequestPrimitive retrieveRequest = iotdm.getInitilazedRetrieveRequestPrimitive();
        retrieveRequest.setTo("/InCSE1/container");
        FilterCriteria fc=new FilterCriteria();

        fc.setSizeBelow(BigInteger.valueOf(1));

        retrieveRequest.setFilterCriteria(fc);
        ResponsePrimitive retrieveResponse=iotdm.sendRequestAndGetResponse(retrieveRequest);
        OneM2M.Assert.assertEqualRequestIdentifer(retrieveRequest, retrieveResponse);
        OneM2M.Assert.assertEqualResponseCode(OneM2M.ResponseStatusCodes.OK.value(), retrieveResponse);
    }
        

}
