package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum ExecModeType  implements EnumApi {
    IMMEDIATEONCE("IMMEDIATEONCE", BigInteger.valueOf(1)),
    IMMEDIATEREPEAT("IMMEDIATEREPEAT", BigInteger.valueOf(2)),
    RANDOMONCE("RANDOMONCE", BigInteger.valueOf(3)),
    RANDOMREPEAT("RANDOMREPEAT", BigInteger.valueOf(4));


    private String interpretation;
    private BigInteger value;

    ExecModeType(String interpretation, BigInteger value) {
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
