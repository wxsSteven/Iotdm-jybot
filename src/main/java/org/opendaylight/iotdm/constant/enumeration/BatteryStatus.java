package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum BatteryStatus  implements EnumApi  {
    NORMAL("NORMAL", BigInteger.valueOf(1)),
    CHARGING("CHARGING", BigInteger.valueOf(2)),
    CHARGING_COMPLETE("CHARGING_COMPLETE", BigInteger.valueOf(3)),
    DAMAGED("DAMAGED", BigInteger.valueOf(4)),
    LOW_BATTERY("LOW_BATTERY", BigInteger.valueOf(5)),
    NOT_INSTALLED("NOT_INSTALLED", BigInteger.valueOf(6)),
    UNKNOWN("UNKNOWN", BigInteger.valueOf(7));


    private String interpretation;
    private BigInteger value;

    BatteryStatus(String interpretation, BigInteger value) {
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
