package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum EventType  implements EnumApi {
    DATAOPERATION("DATAOPERATION", BigInteger.valueOf(1)),
    STORAGEBASED("STORAGEBASED", BigInteger.valueOf(2)),
    TIMERBASED("TIMERBASED", BigInteger.valueOf(3));


    private String interpretation;
    private BigInteger value;

    EventType(String interpretation, BigInteger value) {
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
