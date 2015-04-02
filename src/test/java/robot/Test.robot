*** Settings ***
Library     org.opendaylight.iotdm.robot.iotdm.Iotdm

*** Test Cases ***
Create
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Request Identifier     54321
    Change Content Attribute In  ${requestPrimitive}    hahah     3123
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   "output"


    



