package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum LogStatus  implements EnumApi {
    STARTED("Started", BigInteger.valueOf(1)),
    STOPPED("Stopped", BigInteger.valueOf(2)),
    UNKNOWN("Unknown", BigInteger.valueOf(3)),
    NOT_PRESENT("NotPresent", BigInteger.valueOf(4)),
    ERROR("Error", BigInteger.valueOf(5));


    private String interpretation;
    private BigInteger value;

    LogStatus(String interpretation, BigInteger value) {
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
