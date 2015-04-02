package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum CmdType  implements EnumApi {
    RESET("RESET", BigInteger.valueOf(1)),
    REBOOT("REBOOT", BigInteger.valueOf(2)),
    UPLOAD("UPLOAD", BigInteger.valueOf(3)),
    DOWNLOAD("DOWNLOAD", BigInteger.valueOf(4)),
    SOFTWAREINSTALL("SOFTWAREINSTALL", BigInteger.valueOf(5)),
    SOFTWAREUNINSTALL("SOFTWAREUNINSTALL", BigInteger.valueOf(6)),
    SOFTWAREUPDATE("SOFTWAREUPDATE", BigInteger.valueOf(7));


    private String interpretation;
    private BigInteger value;

    CmdType(String interpretation, BigInteger value) {
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
