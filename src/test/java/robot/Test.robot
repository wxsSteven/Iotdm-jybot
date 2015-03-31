*** Settings ***
Library     org.opendaylight.iotdm.robot.iotdm.Iotdm
Library     org.opendaylight.iotdm.primitive.RequestPrimitive

*** Test Cases ***
Test
    ${requestPrimitive} =    Get Initilazed Request Primitive
    Set Result Content      ${None}
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   "output"


    



