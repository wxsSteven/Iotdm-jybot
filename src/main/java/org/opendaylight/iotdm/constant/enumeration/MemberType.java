package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum MemberType  implements EnumApi {
    ACCESS_CONTROL_POLICY("accessControlPolicy", BigInteger.valueOf(1)),
    AE("AE", BigInteger.valueOf(2)),
    CONTAINER("container", BigInteger.valueOf(3)),
    CONTENT_INSTANCE("contentInstance", BigInteger.valueOf(4)),
    CSE_BASE("CSEBase", BigInteger.valueOf(5)),
    DELIVERY("delivery", BigInteger.valueOf(6)),
    EVENT_CONFIG("eventConfig", BigInteger.valueOf(7)),
    EXEC_INSTANCE("execInstance", BigInteger.valueOf(8)),
    GROUP("group", BigInteger.valueOf(9)),
    LOCATION_POLICY("locationPolicy", BigInteger.valueOf(10)),
    M2M_SERVICE_SUBSCRIPTION("m2mServiceSubscription", BigInteger.valueOf(11)),
    MGMT_CMD("mgmtCmd", BigInteger.valueOf(12)),
    MGMT_OBJ("mgmtObj", BigInteger.valueOf(13)),
    NODE("node", BigInteger.valueOf(14)),
    POLLING_CHANNEL("pollingChannel", BigInteger.valueOf(15)),
    REMOTE_CSE("remoteCSE", BigInteger.valueOf(16)),
    REQUEST("request", BigInteger.valueOf(17)),
    SCHEDULE("schedule", BigInteger.valueOf(18)),
    SERVICE_SUBSCRIBED_APP_RULE("serviceSubscribedAppRule", BigInteger.valueOf(19)),
    SERVICE_SUBSCRIBED_NODE("serviceSubscribedNode", BigInteger.valueOf(20)),
    STATS_COLLECT("statsCollect", BigInteger.valueOf(21)),
    STATS_CONFIG("statsConfig", BigInteger.valueOf(22)),
    SUBSCRIPTION("subscription", BigInteger.valueOf(23)),
    MIXED("mixed", BigInteger.valueOf(24));


    private String interpretation;
    private BigInteger value;

    MemberType(String interpretation, BigInteger value) {
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
