package org.opendaylight.iotdm.constant.enumeration;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum SRoleId {
    SOFTWARE_MANAGEMENT("Software Management", "01-001"),
    DEVICE_CONFIGURATION("Device Configuration", "02-001"),
    DEVICE_DIAGNOSTICS_AND_MANAGEMENT("Device Diagnostics and Management", "02-002"),
    DEVICE_FIRMWARE_MANAGEMENT("Device Firmware Management", "02-003"),
    DEVICE_TOPOLOGY("Device Topology", "02-004"),
    LOCATION("Location", "03-001"),
    BASIC_DATA("Basic Data", "04-001"),
    ONBOARDING("Onboarding", "05-001"),
    SECURITY_ADMINISTRATION("Security Administration", "06-001"),
    GROUPS_MANAGEMENT("Groups Management", "07-001"),
    EVENT_COLLECTION("Event Collection", "08-001");


    private String interpretation;
    private String value;

    SRoleId(String interpretation, String value) {
        this.interpretation = interpretation;
        this.value = value;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public String getValue() {
        return value;
    }
}
