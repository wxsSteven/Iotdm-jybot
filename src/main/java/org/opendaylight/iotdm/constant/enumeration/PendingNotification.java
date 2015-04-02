package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum PendingNotification  implements EnumApi {
    SEND_LATEST("sendLatest", BigInteger.valueOf(1)),
    SEND_ALL_PENDING("sendAllPending", BigInteger.valueOf(2));


    private String interpretation;
    private BigInteger value;

    PendingNotification(String interpretation, BigInteger value) {
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
