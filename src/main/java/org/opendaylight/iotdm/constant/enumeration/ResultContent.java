package org.opendaylight.iotdm.constant.enumeration;

import java.math.BigInteger;

/**
 * Created by wenxshi on 3/31/15.
 */
public enum ResultContent  implements EnumApi {
    NOTHING("nothing", BigInteger.valueOf(0)),
    ATTRIBUTES("attributes", BigInteger.valueOf(1)),
    HIERARCHICAL_ADDRESS("hierarchical address", BigInteger.valueOf(2)),
    HIERARCHICAL_ADDRESS_AND_ATTRIBUTES("hierarchical address and attributes", BigInteger.valueOf(3)),
    ATTRIBUTES_AND_CHILD_RESOURCES("attributes and child resources", BigInteger.valueOf(4)),
    ATTRIBUTES_AND_CHILD_RESOURCE_REFERENCES("attributes and child resource references", BigInteger.valueOf(5)),
    CHILD_RESOURCE_REFERENCES("child resource references", BigInteger.valueOf(6)),
    ORIGINAL_RESOURCE("original resource", BigInteger.valueOf(7));


    private String interpretation;
    private BigInteger value;

    ResultContent(String interpretation, BigInteger value) {
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
