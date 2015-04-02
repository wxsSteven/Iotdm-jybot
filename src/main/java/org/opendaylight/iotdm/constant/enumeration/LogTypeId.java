package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum LogTypeId  implements EnumApi {
    SYSTEM("System", BigInteger.valueOf(1)),
    SECURITY("Security", BigInteger.valueOf(2)),
    EVENT("Event", BigInteger.valueOf(3)),
    TRACE("Trace", BigInteger.valueOf(4)),
    PANIC("Panic", BigInteger.valueOf(5));


    private String interpretation;
    private BigInteger value;

    LogTypeId(String interpretation, BigInteger value) {
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
