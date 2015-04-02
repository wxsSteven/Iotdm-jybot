package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum AccessControlOperations implements EnumApi {
    CREATE("CREATE", BigInteger.valueOf(1)),
    RETRIEVE("RETRIEVE", BigInteger.valueOf(2)),
    UPDATE("UPDATE", BigInteger.valueOf(4)),
    DELETE("DELETE", BigInteger.valueOf(8)),
    DISCOVERY("DISCOVERY", BigInteger.valueOf(16)),
    NOTIFY("NOTIFY", BigInteger.valueOf(32));


    private String interpretation;
    private BigInteger value;

    AccessControlOperations(String interpretation, BigInteger value) {
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
