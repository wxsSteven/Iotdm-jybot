package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum NotificationContentType  implements EnumApi {
    MODIFIED_ATTRIBUTES("modifiedAttributes", BigInteger.valueOf(1)),
    WHOLE_RESOURCE("wholeResource", BigInteger.valueOf(2)),
    REFERENCE_ONLY("referenceOnly", BigInteger.valueOf(3));


    private String interpretation;
    private BigInteger value;

    NotificationContentType(String interpretation, BigInteger value) {
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
