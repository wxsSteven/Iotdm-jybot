package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum ExecStatusType  implements EnumApi {
    INITIATED("INITIATED", BigInteger.valueOf(1)),
    PENDING("PENDING", BigInteger.valueOf(2)),
    FINISHED("FINISHED", BigInteger.valueOf(3)),
    CANCELLING("CANCELLING", BigInteger.valueOf(4)),
    CANCELLED("CANCELLED", BigInteger.valueOf(5)),
    STATUS_NON_CANCELLABLE("STATUS_NON_CANCELLABLE", BigInteger.valueOf(6));


    private String interpretation;
    private BigInteger value;

    ExecStatusType(String interpretation, BigInteger value) {
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
