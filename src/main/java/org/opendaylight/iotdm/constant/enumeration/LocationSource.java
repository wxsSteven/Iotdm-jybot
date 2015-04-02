package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum LocationSource  implements EnumApi {
    NETWORK_BASED("Network_based", BigInteger.valueOf(1)),
    DEVICE_BASED("Device_based", BigInteger.valueOf(2)),
    SHARING_BASED("Sharing_based", BigInteger.valueOf(3));


    private String interpretation;
    private BigInteger value;

    LocationSource(String interpretation, BigInteger value) {
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
