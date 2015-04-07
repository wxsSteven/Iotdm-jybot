*** Settings ***
Library     org.opendaylight.iotdm.robot.iotdm.Iotdm

*** Test Cases ***
#example Create
#    ${requestPrimitive} =    Get Initilazed Create Request Primitive
#    Change Attribute In     ${requestPrimitive}   Set Request Identifier     54321
#    Change Content Attribute In  ${requestPrimitive}    hahah     3123
#    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#    Should Contain  ${result}   "output"


#------------Create "to" attribute test---------------------------
100.0 Create: "to" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To   ${null}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   rpcerror

    
100.1 Create: "to" is invalid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /abc
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   rpcerror

100.2 Create: "to" is valid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   path:

#---------------Create "from" attribute test-----------------
102.0 Create:"from" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set From  ${null}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Lack of mandatory attribute


#-----------------Create "requestIdentifer" attribute test
103.0 Create:"requestIdentifer" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set Request Identifier  ${null}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Lack of mandatory attribute


#------------------Create "resourceType" attribute test--------------
104.0 Create:"resourceType" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set Resource Type  ${null}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Lack of mandatory attribute

104.1 Create:"resourceType" is invalid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set Resource Type  1000000
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   rpcError

104.1 Create:"resourceType" is valid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set Resource Type  3
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   container

#----------------------Create "content" attribute test---------------
106.0 Create:"content" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set Content  ${null}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Lack of mandatory attribute

106.1 Create:"content" is invalid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set Content  {"a":"b"}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Lack of mandatory attribute
