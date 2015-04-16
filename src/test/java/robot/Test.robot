*** Settings ***
Library     org.opendaylight.iotdm.robot.iotdm.Iotdm

*** Test Cases ***
Create
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Request Identifier     54321
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    log     ${result}


    



