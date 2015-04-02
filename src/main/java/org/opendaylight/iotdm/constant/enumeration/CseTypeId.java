package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum CseTypeId  implements EnumApi {
    IN__CSE("IN_CSE", BigInteger.valueOf(1)),
    MN__CSE("MN_CSE", BigInteger.valueOf(2)),
    ASN__CSE("ASN_CSE", BigInteger.valueOf(3));

    private String interpretation;
    private BigInteger value;

    CseTypeId(String interpretation, BigInteger value) {
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
