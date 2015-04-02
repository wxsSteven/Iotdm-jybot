package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum ResourceStatus  implements EnumApi {
    CHILD_CREATED("childCreated", BigInteger.valueOf(1)),
    CHILD_DELETED("childDeleted", BigInteger.valueOf(2)),
    UPDATED("updated", BigInteger.valueOf(3)),
    DELETED("deleted", BigInteger.valueOf(4));


    private String interpretation;
    private BigInteger value;

    ResourceStatus(String interpretation, BigInteger value) {
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
