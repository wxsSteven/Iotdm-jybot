package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum StatsRuleStatusType  implements EnumApi {
    ACTIVE("ACTIVE", BigInteger.valueOf(1)),
    INACTIVE("INACTIVE", BigInteger.valueOf(2));


    private String interpretation;
    private BigInteger value;

    StatsRuleStatusType(String interpretation, BigInteger value) {
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
