package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum RequestStatus  implements EnumApi {
    COMPLETED("COMPLETED", BigInteger.valueOf(1)),
    FAILED("FAILED", BigInteger.valueOf(2)),
    PENDING("PENDING", BigInteger.valueOf(3)),
    FORWARDED("FORWARDED", BigInteger.valueOf(4));


    private String interpretation;
    private BigInteger value;

    RequestStatus(String interpretation, BigInteger value) {
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
