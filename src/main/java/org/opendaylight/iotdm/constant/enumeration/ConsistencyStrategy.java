package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum ConsistencyStrategy  implements EnumApi {
    ABANDON_MEMBER("ABANDON_MEMBER", BigInteger.valueOf(1)),
    ABANDON_GROUP("ABANDON_GROUP", BigInteger.valueOf(2)),
    SET_MIXED("SET_MIXED", BigInteger.valueOf(3));


    private String interpretation;
    private BigInteger value;

    ConsistencyStrategy(String interpretation, BigInteger value) {
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
