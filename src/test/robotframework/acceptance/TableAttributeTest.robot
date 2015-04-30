*** Settings ***
Library     org.opendaylight.iotdm.robot.iotdm.Iotdm

*** Test Cases ***
example Create
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Request Identifier     54321
    Change Content Attribute In  ${requestPrimitive}    hahah     3123
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   "output"


#------------Create "to" attribute test---------------------------
100.0 Create: "to" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To   ${null}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Error


100.1 Create: "to" is invalid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /abc
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Error

100.2 Create: "to" is valid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Output

#---------------Create "from" attribute test-----------------
101.0 Create:"from" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set From  ${null}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Lack of mandatory attribute


#-----------------Create "requestIdentifer" attribute test
102.0 Create:"requestIdentifer" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set Request Identifier  ${null}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Lack of mandatory attribute


#------------------Create "resourceType" attribute test--------------
103.0 Create:"resourceType" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set Resource Type  ${null}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Lack of mandatory attribute

103.1 Create:"resourceType" is invalid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set Resource Type  1000000
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   rpcError

103.1 Create:"resourceType" is valid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set Resource Type  3
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   container

#----------------------Create "content" attribute test---------------
105.0 Create:"content" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set Content  ${null}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Lack of mandatory attribute

105.1 Create:"content" is invalid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Content Attribute In     ${requestPrimitive}   a   1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Output


#----------------------Create "originateTimeStamp" attribute test---------------
#----------------------Create "requesteExperationTimeStamp" attribute test---------------
#----------------------Create "resultExpirationTime" attribute test---------------
#----------------------Create "operationExecutionTime" attribute test---------------
#----------------------Create "responseType" attribute test---------------
#----------------------Create "resultPersistence" attribute test---------------
#----------------------Create "resultContent" attribute test---------------
#----------------------Create "eventCategory" attribute test---------------
#----------------------Create "deliveryAggregation" attribute test---------------
#----------------------Create "groupRequestIdentifier" attribute test---------------


#------------Update "to" attribute test---------------------------
200.0 Update: "to" is null

    ${requestPrimitive} =    Get Initilazed Update Request Primitive
    change Attribute In     ${requestPrimitive}     Set To   ${null}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Error


200.1 Update: "to" is invalid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Update Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /abc
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Error

200.2 Update: "to" is valid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Update Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  ${to}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Output

#---------------Update "from" attribute test-----------------
201.0 Update:"from" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Update Request Primitive
    change Attribute In     ${requestPrimitive}     Set From  ${null}
    change Attribute In     ${requestPrimitive}     Set To  ${to}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Lack of mandatory attribute


#-----------------Update "requestIdentifer" attribute test
202.0 Update:"requestIdentifer" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Update Request Primitive
    change Attribute In     ${requestPrimitive}     Set Request Identifier  ${null}
    change Attribute In     ${requestPrimitive}     Set To  ${to}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Lack of mandatory attribute


#------------------Update "resourceType" attribute test--------------
203.0 Update:"resourceType" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Update Request Primitive
    change Attribute In     ${requestPrimitive}     Set Resource Type  ${null}
    change Attribute In     ${requestPrimitive}     Set To   InCSE1/10006
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   output

203.1 Update:"resourceType" is invalid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Update Request Primitive
    change Attribute In     ${requestPrimitive}     Set Resource Type  1000000
    change Attribute In     ${requestPrimitive}     Set To  ${to}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   output

203.2 Update:"resourceType" is valid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Update Request Primitive
    change Attribute In     ${requestPrimitive}     Set Resource Type  3
    change Attribute In     ${requestPrimitive}     Set To  ${to}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   output

#----------------------Update "content" attribute test---------------
205.0 Update:"content" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Update Request Primitive
    change Attribute In     ${requestPrimitive}     Set Content  ${null}
    change Attribute In     ${requestPrimitive}     Set To  ${to}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Error

205.1 Update:"content" is invalid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Update Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  ${to}
    change Content Attribute In     ${requestPrimitive}   a   1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Error


#----------------------Update "originateTimeStamp" attribute test---------------
#----------------------Update "requesteExperationTimeStamp" attribute test---------------
#----------------------Update "resultExpirationTime" attribute test---------------
#----------------------Update "operationExecutionTime" attribute test---------------
#----------------------Update "responseType" attribute test---------------
#----------------------Update "resultPersistence" attribute test---------------
#----------------------Update "resultContent" attribute test---------------
#----------------------Update "eventCategory" attribute test---------------
#----------------------Update "deliveryAggregation" attribute test---------------
#----------------------Update "groupRequestIdentifier" attribute test---------------



#------------Retrieve "to" attribute test---------------------------
300.0 Retrieve: "to" is null

    ${requestPrimitive} =    Get Initilazed Retrieve Request Primitive
    change Attribute In     ${requestPrimitive}     Set To   ${null}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Error


300.1 Retrieve: "to" is invalid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Retrieve Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /abc
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Error

300.2 Retrieve: "to" is valid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Retrieve Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  ${to}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Output

#---------------Retrieve "from" attribute test-----------------
301.0 Retrieve:"from" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Retrieve Request Primitive
    change Attribute In     ${requestPrimitive}     Set From  ${null}
    change Attribute In     ${requestPrimitive}     Set To  ${to}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Lack of mandatory attribute


#-----------------Retrieve "requestIdentifer" attribute test
302.0 Retrieve:"requestIdentifer" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Retrieve Request Primitive
    change Attribute In     ${requestPrimitive}     Set Request Identifier  ${null}
    change Attribute In     ${requestPrimitive}     Set To  ${to}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Lack of mandatory attribute


#------------------Retrieve "resourceType" attribute test--------------
303.0 Retrieve:"resourceType" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Retrieve Request Primitive
    change Attribute In     ${requestPrimitive}     Set Resource Type  ${null}
    change Attribute In     ${requestPrimitive}     Set To   InCSE1/10006
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   output

303.1 Retrieve:"resourceType" is invalid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Retrieve Request Primitive
    change Attribute In     ${requestPrimitive}     Set Resource Type  1000000
    change Attribute In     ${requestPrimitive}     Set To  ${to}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   output

303.2 Retrieve:"resourceType" is valid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Retrieve Request Primitive
    change Attribute In     ${requestPrimitive}     Set Resource Type  3
    change Attribute In     ${requestPrimitive}     Set To  ${to}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   output

#----------------------Retrieve "content" attribute test---------------
305.0 Retrieve:"content" is null
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Retrieve Request Primitive
    change Attribute In     ${requestPrimitive}     Set Content  ${null}
    change Attribute In     ${requestPrimitive}     Set To  ${to}
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Error

305.1 Retrieve:"content" is invalid
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  /InCSE1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    ${to} =     Get Hier URI    ${result}

    ${requestPrimitive} =    Get Initilazed Retrieve Request Primitive
    change Attribute In     ${requestPrimitive}     Set To  ${to}
    change Content Attribute In     ${requestPrimitive}   a   1
    ${result} =     Send Request And Get Response  ${requestPrimitive}
    Should Contain   ${result}   Error


#----------------------Retrieve "originateTimeStamp" attribute test---------------
#----------------------Retrieve "requesteExperationTimeStamp" attribute test---------------
#----------------------Retrieve "resultExpirationTime" attribute test---------------
#----------------------Retrieve "operationExecutionTime" attribute test---------------
#----------------------Retrieve "responseType" attribute test---------------
#----------------------Retrieve "resultPersistence" attribute test---------------
#----------------------Retrieve "resultContent" attribute test---------------
#----------------------Retrieve "eventCategory" attribute test---------------
#----------------------Retrieve "deliveryAggregation" attribute test---------------
#----------------------Retrieve "groupRequestIdentifier" attribute test---------------

