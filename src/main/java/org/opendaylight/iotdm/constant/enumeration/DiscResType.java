package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum DiscResType  implements EnumApi {
    HIERARCHICAL("hierarchical", BigInteger.valueOf(1)),
    NON_HIERARCHICAL("non_hierarchical", BigInteger.valueOf(2)),
    CSE_ID_AND_RESOURCE_ID("cseID and resourceID", BigInteger.valueOf(3));


    private String interpretation;
    private BigInteger value;

    DiscResType(String interpretation, BigInteger value) {
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
