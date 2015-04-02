package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum EncodingType  implements EnumApi {
    PLAIN("Plain", BigInteger.valueOf(0)),
    BASE64_ON_STRING("base64 on string", BigInteger.valueOf(1)),
    BASE64_ON_BINARY("base64 on binary", BigInteger.valueOf(2));


    private String interpretation;
    private BigInteger value;

    EncodingType(String interpretation, BigInteger value) {
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
