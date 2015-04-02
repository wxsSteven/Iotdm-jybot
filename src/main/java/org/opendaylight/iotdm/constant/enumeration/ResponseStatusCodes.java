package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum ResponseStatusCodes  implements EnumApi {
    ACCEPTED("ACCEPTED", BigInteger.valueOf(1000)),
    OK("OK", BigInteger.valueOf(2000)),
    CREATED("CREATED", BigInteger.valueOf(2001)),
    DELETED("DELETED", BigInteger.valueOf(2002)),
    CHANGED("CHANGED", BigInteger.valueOf(2004)),
    BAD_REQUEST("BAD_REQUEST", BigInteger.valueOf(4000)),
    NOT_FOUND("NOT_FOUND", BigInteger.valueOf(4004)),
    OPERATION_NOT_ALLOWED("OPERATION_NOT_ALLOWED", BigInteger.valueOf(4005)),
    REQUEST_TIMEOUT("REQUEST_TIMEOUT", BigInteger.valueOf(4008)),
    SUBSCRIPTION_CREATOR_HAS_NO_PRIVILEGE_("SUBSCRIPTION_CREATOR_HAS_NO_PRIVILEGE–", BigInteger.valueOf(4101)),
    CONTENTS_UNACCEPTABLE("CONTENTS_UNACCEPTABLE", BigInteger.valueOf(4102)),
    ACCESS_DENIED("ACCESS_DENIED", BigInteger.valueOf(4103)),
    GROUP_REQUEST_IDENTIFIER_EXISTS("GROUP_REQUEST_IDENTIFIER_EXISTS", BigInteger.valueOf(4104)),
    CONFLICT("CONFLICT", BigInteger.valueOf(4105)),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", BigInteger.valueOf(5000)),
    NOT_IMPLEMENTED("NOT_IMPLEMENTED", BigInteger.valueOf(5001)),
    TARGET_NOT_REACHABLE("TARGET_NOT_REACHABLE", BigInteger.valueOf(5103)),
    NO_PRIVILEGE("NO_PRIVILEGE", BigInteger.valueOf(5105)),
    ALREADY_EXISTS("ALREADY_EXISTS", BigInteger.valueOf(5106)),
    TARGET_NOT_SUBSCRIBABLE("TARGET_NOT_SUBSCRIBABLE", BigInteger.valueOf(5203)),
    SUBSCRIPTION_VERIFICATION_INITIATION_FAILED("SUBSCRIPTION_VERIFICATION_INITIATION_FAILED", BigInteger.valueOf(5204)),
    SUBSCRIPTION_HOST_HAS_NO_PRIVILEGE("SUBSCRIPTION_HOST_HAS_NO_PRIVILEGE", BigInteger.valueOf(5205)),
    NON_BLOCKING_REQUEST_NOT_SUPPORTED("NON_BLOCKING_REQUEST_NOT_SUPPORTED", BigInteger.valueOf(5206)),
    EXTENAL_OBJECT_NOT_REACHABLE("EXTENAL_OBJECT_NOT_REACHABLE", BigInteger.valueOf(6003)),
    EXTENAL_OBJECT_NOT_FOUND("EXTENAL_OBJECT_NOT_FOUND", BigInteger.valueOf(6005)),
    MAX_NUMBERF_OF_MEMBER_EXCEEDED("MAX_NUMBERF_OF_MEMBER_EXCEEDED", BigInteger.valueOf(6010)),
    MEMBER_TYPE_INCONSISTENT("MEMBER_TYPE_INCONSISTENT", BigInteger.valueOf(6011)),
    MGMT_SESSION_CANNOT_BE_ESTABLISHED("MGMT_SESSION_CANNOT_BE_ESTABLISHED", BigInteger.valueOf(6020)),
    MGMT_SESSION_ESTABLISHMENT__TIMEOUT("MGMT_SESSION_ESTABLISHMENT _TIMEOUT", BigInteger.valueOf(6021)),
    INVALID__CMDTYPE("INVALID _CMDTYPE", BigInteger.valueOf(6022)),
    INVALID_ARGUMENTS("INVALID_ARGUMENTS", BigInteger.valueOf(6023)),
    INSUFFICIENT_ARGUMENTS("INSUFFICIENT_ARGUMENTS", BigInteger.valueOf(6024)),
    MGMT_CONVERSION_ERROR("MGMT_CONVERSION_ERROR", BigInteger.valueOf(6025)),
    MGMT_CANCELATION_FAILURE("MGMT_CANCELATION_FAILURE", BigInteger.valueOf(6026)),
    ALREADY_COMPLETE("ALREADY_COMPLETE", BigInteger.valueOf(6028)),
    COMMAND_NOT_CANCELLABLE("COMMAND_NOT_CANCELLABLE", BigInteger.valueOf(6029));


    private String interpretation;
    private BigInteger value;

    ResponseStatusCodes(String interpretation, BigInteger value) {
        this.interpretation = interpretation;
        this.value = value;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public BigInteger getValue() {
        return value;
    }
}