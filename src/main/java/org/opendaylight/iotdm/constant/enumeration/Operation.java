package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum Operation  implements EnumApi {
    CREATE("Create", BigInteger.valueOf(1)),
    RETRIEVE("Retrieve", BigInteger.valueOf(2)),
    UPDATE("Update", BigInteger.valueOf(3)),
    DELETE("Delete", BigInteger.valueOf(4)),
    NOTIFY("Notify", BigInteger.valueOf(5));


    private String interpretation;
    private BigInteger value;

    Operation(String interpretation, BigInteger value) {
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
