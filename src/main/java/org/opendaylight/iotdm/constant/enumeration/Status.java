package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum Status  implements EnumApi {
    SUCCESSFUL("Successful", BigInteger.valueOf(1)),
    FAILURE("Failure", BigInteger.valueOf(2)),
    IN__PROCESS("In_Process", BigInteger.valueOf(3));


    private String interpretation;
    private BigInteger value;

    Status(String interpretation, BigInteger value) {
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
