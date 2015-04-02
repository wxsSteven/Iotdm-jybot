package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum ResponseType  implements EnumApi {
    NON_BLOCKING_REQUEST_SYNCH("nonBlockingRequestSynch", BigInteger.valueOf(1)),
    NON_BLOCKING_REQUEST_ASYNCH("nonBlockingRequestAsynch", BigInteger.valueOf(2)),
    BLOCKING_REQUEST("blockingRequest", BigInteger.valueOf(3));


    private String interpretation;
    private BigInteger value;

    ResponseType(String interpretation, BigInteger value) {
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
