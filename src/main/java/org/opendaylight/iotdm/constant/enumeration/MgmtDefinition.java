package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum MgmtDefinition  implements EnumApi {
    FIRMWARE("firmware", BigInteger.valueOf(1001)),
    SOFTWARE("software", BigInteger.valueOf(1002)),
    MEMORY("memory", BigInteger.valueOf(1003)),
    AREA_NWK_INFO("areaNwkInfo", BigInteger.valueOf(1004)),
    AREA_NWK_DEVICE_INFO("areaNwkDeviceInfo", BigInteger.valueOf(1005)),
    BATTERY("battery", BigInteger.valueOf(1006)),
    DEVICE_INFO("deviceInfo", BigInteger.valueOf(1007)),
    DEVICE_CAPABILITY("deviceCapability", BigInteger.valueOf(1008)),
    REBOOT("reboot", BigInteger.valueOf(1009)),
    EVENT_LOG("eventLog", BigInteger.valueOf(1010)),
    CMDH_POLICY("cmdhPolicy", BigInteger.valueOf(1011)),
    ACTIVE_CMDH_POLICY("activeCmdhPolicy", BigInteger.valueOf(1012)),
    CMDH_DEFAULTS("cmdhDefaults", BigInteger.valueOf(1013)),
    CMDH_DEF_EC_VALUE("cmdhDefEcValue", BigInteger.valueOf(1014)),
    CMDH_EC_DEF_PARAM_VALUES("cmdhEcDefParamValues", BigInteger.valueOf(1015)),
    CMDH_LIMITS("cmdhLimits", BigInteger.valueOf(1016)),
    CMDH_NETWORK_ACCESS_RULES("cmdhNetworkAccessRules", BigInteger.valueOf(1017)),
    CMDH_NW_ACCESS_RULE("cmdhNwAccessRule", BigInteger.valueOf(1018)),
    CMDH_BUFFER("cmdhBuffer", BigInteger.valueOf(1019)),
    UNSPECIFIED("Unspecified", BigInteger.valueOf(0));


    private String interpretation;
    private BigInteger value;

    MgmtDefinition(String interpretation, BigInteger value) {
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
