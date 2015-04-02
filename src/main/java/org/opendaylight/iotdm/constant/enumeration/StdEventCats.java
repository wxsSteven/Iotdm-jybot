package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum StdEventCats  implements EnumApi {
    DEFAULT("Default", BigInteger.valueOf(1)),
    IMMEDIATE("Immediate", BigInteger.valueOf(2)),
    BEST_EFFORT("BestEffort", BigInteger.valueOf(3)),
    LATEST("Latest", BigInteger.valueOf(4));


    private String interpretation;
    private BigInteger value;

    StdEventCats(String interpretation, BigInteger value) {
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
