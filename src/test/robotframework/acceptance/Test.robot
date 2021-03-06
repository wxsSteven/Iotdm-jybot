*** Settings ***
Library     org.opendaylight.iotdm.robot.iotdm.Iotdm
Library     json
Library     org.robotframework.javalib.library.AnnotationLibrary     org/wuokko/robot/restlib/*.class

*** Variable ***


*** Test Cases ***
Create
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Request Identifier     54321
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    log     ${result}

#==================================================
#       AE Test
#==================================================

#---------------  Create And Retrieve  ------------


1.11 Valid Input for AE without name
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    createAE
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   responseStatusCode

#   test the retrieve part with hierURI
    ${hierURI} =     Get Hier URI   ${result}
    ${nonhierURI} =  Get Non Hier URI   ${result}
    Change Attribute In     ${requestPrimitive}   Set Operation    2
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/${hierURI}
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#   Update this method once "response" is following the document
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   AE

#   test the retrieve part with nonhierURI

#    Change Attribute In     ${requestPrimitive}   Set discoveryResultType    2

#    delete the whole tree
#    Change Attribute In     ${requestPrimitive}   Set Operation    4
#    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#    Should Contain  ${result}   responseStatusCode

1.12 Valid Input for AE with name and label
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    createAE
	Change Content Attribute In  ${requestPrimitive} 	resourceName	validName
	Change Content Attribute In  ${requestPrimitive} 	labels	mylabels
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   AE

#   test the retrieve part with hierURI
    ${hierURI} =     Get Hier URI   ${result}
    ${nonhierURI} =  Get Non Hier URI   ${result}
    Change Attribute In     ${requestPrimitive}   Set Operation    2
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/${hierURI}
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#   Update this method once "response" is following the document
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   AE
    Should Be Equal   ${hierURI}    InCSE1/validName
#   test the retrieve part with nonhierURI
#    Change Attribute In     ${requestPrimitive}   Set discoveryResultType    2


1.13 Invalid Input for AE with name (Already Exist)
	${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    createAE
    Change Content Attribute In  ${requestPrimitive} 	resourceName	validName
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   RpcError
    Should Contain  ${result}   5106

1.14 Invalid Input for AE with name (Invalid name)
	${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    createAE
    Change Content Attribute In  ${requestPrimitive} 	resourceName	valid/Name
    Change Content Attribute In  ${requestPrimitive} 	lables	        my labels
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   RpcError

1.15 Invalid Input for AE (AE cannot be created under AE)
	${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/validName
    Change Attribute In     ${requestPrimitive}   Set Name    createAE
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   RpcError

# -----------------   Update and Retrieve -------------

1.21 Valid Update AE's labels
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    updateAE
    Change Attribute In     ${requestPrimitive}   Set Operation    3
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/validName
	Change Content Attribute In  ${requestPrimitive} 	labels	updateAE
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   update
    Should Contain  ${result}   AE

#   test the retrieve part with hierURI

    Change Attribute In     ${requestPrimitive}   Set Operation    2
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#   Update this method once "response" is following the document
    Should Contain  ${result}   updateAE
    Should Contain  ${result}   AE

1.22 InValid Update AE's name
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    updateAE
    Change Attribute In     ${requestPrimitive}   Set Operation    3
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/validName
	Change Content Attribute In  ${requestPrimitive} 	resourceName	updateAE
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Not Contain  ${result}    updateAE


#   test the retrieve part with hierURI

    Change Attribute In     ${requestPrimitive}   Set Operation    2
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#   Update this method once "response" is following the document
    Should Contain  ${result}   validName
    Should Contain  ${result}   AE

#------------------------------------------------------

# Delete the whole tree
#    #delete the whole tree
#    ${requestPrimitive} =    Get Initilazed Create Request Primitive
#    Change Attribute In     ${requestPrimitive}   Set Operation    4
#    ${result} =  Send Request And GetResponse   ${requestPrimitive}

# ParentAE cannot be deleted

#==================================================
#       Container Test
#==================================================

#------------------  Create -----------------------

2.11 Valid Input for container under AE without name
	${requestPrimitive} =    Get Initilazed Create Request Primitive
	Change Attribute In     ${requestPrimitive}   Set Name    createContainerUnderAE
    Change Attribute In     ${requestPrimitive}   Set ResourceType    3
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/validName
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   container

#   test the retrieve part with hierURI
    ${hierURI} =     Get Hier URI   ${result}
    ${nonhierURI} =  Get Non Hier URI   ${result}
    Change Attribute In     ${requestPrimitive}   Set Operation    2
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/${hierURI}
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#   Update this method once "response" is following the document
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   container

2.12 Valid Input for container under AE with name and label
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    createContainerUnderAE
    Change Attribute In     ${requestPrimitive}   Set ResourceType    3
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/validName
    Change Content Attribute In  ${requestPrimitive} 	resourceName	containerUnderAE
    Change Content Attribute In  ${requestPrimitive} 	labels	container1
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   container

#   test the retrieve part with hierURI
    ${hierURI} =     Get Hier URI   ${result}
    ${nonhierURI} =  Get Non Hier URI   ${result}
    Change Attribute In     ${requestPrimitive}   Set Operation    2
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/${hierURI}
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#   Update this method once "response" is following the document
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   container
    Should Be Equal   ${hierURI}    InCSE1/validName/containerUnderAE

2.13 Invalid Input for container under AE with name (Already Exist)
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    createContainerUnderAE
    Change Attribute In     ${requestPrimitive}   Set ResourceType    3
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/validName
    Change Content Attribute In  ${requestPrimitive} 	resourceName	containerUnderAE
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   RpcError
    Should Contain  ${result}   5106

2.14 Invalid Input for container under AE with name (Invalid name)
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
	Change Attribute In     ${requestPrimitive}   Set Name    createContainerUnderAE
    Change Attribute In     ${requestPrimitive}   Set ResourceType    3
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/validName
    Change Content Attribute In  ${requestPrimitive} 	resourceName	container/UnderAE
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   RpcError

# -----------------   Update and Retrieve -------------


2.15 Valid Update container's labels
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    updateContainer
    Change Attribute In     ${requestPrimitive}   Set Operation    3
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/validName/containerUnderAE
	Change Content Attribute In  ${requestPrimitive} 	labels	updateContainer
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   update
    Should Contain  ${result}   container

#   test the retrieve part with hierURI

    Change Attribute In     ${requestPrimitive}   Set Operation    2
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#   Update this method once "response" is following the document
    Should Contain  ${result}   updateContainer
    Should Contain  ${result}   container

2.16 InValid Update container's name
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    updateContainer
    Change Attribute In     ${requestPrimitive}   Set Operation    3
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/validName/containerUnderAE
	Change Content Attribute In  ${requestPrimitive} 	resourceName	updateContainer
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Not Contain  ${result}    updateContainer


#   test the retrieve part with hierURI

    Change Attribute In     ${requestPrimitive}   Set Operation    2
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#   Update this method once "response" is following the document
    Should Contain  ${result}   containerUnderAE
    Should Contain  ${result}   container

#------------------  Create -----------------------

2.21 Valid Input for container under CSEBase without name
	${requestPrimitive} =    Get Initilazed Create Request Primitive
	Change Attribute In     ${requestPrimitive}   Set Name    createContainerUnderCSEBase
    Change Attribute In     ${requestPrimitive}   Set ResourceType    3
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   container

#   test the retrieve part with hierURI
    ${hierURI} =     Get Hier URI   ${result}
    ${nonhierURI} =  Get Non Hier URI   ${result}
    Change Attribute In     ${requestPrimitive}   Set Operation    2
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/${hierURI}
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#   Update this method once "response" is following the document
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   container

2.22 Valid Input for container under CSEBase with name and label
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    createContainerUnderCSEBase
    Change Attribute In     ${requestPrimitive}   Set ResourceType    3
    Change Content Attribute In  ${requestPrimitive} 	resourceName	containerUnderCSEBase
    Change Content Attribute In  ${requestPrimitive} 	labels	container1
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   container

#   test the retrieve part with hierURI
    ${hierURI} =     Get Hier URI   ${result}
    ${nonhierURI} =  Get Non Hier URI   ${result}
    Change Attribute In     ${requestPrimitive}   Set Operation    2
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/${hierURI}
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#   Update this method once "response" is following the document
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   container
    Should Be Equal   ${hierURI}    InCSE1/containerUnderCSEBase

2.23 Invalid Input for container under CSEBase with name(Already exist)
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    createContainerUnderCSEBase
    Change Attribute In     ${requestPrimitive}   Set ResourceType    3
    Change Content Attribute In  ${requestPrimitive} 	resourceName	containerUnderCSEBase
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   RpcError
    Should Contain  ${result}   5106

2.24 Invalid Input for container under CSEBase with name(Invalid name)
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    createContainerUnderCSEBase
    Change Attribute In     ${requestPrimitive}   Set ResourceType    3
    Change Content Attribute In  ${requestPrimitive} 	resourceName	container/UnderCSEBase
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   RpcError



#------------  Update And Retrieve ---------------------

2.25 Valid Update container's labels
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    updateContainer
    Change Attribute In     ${requestPrimitive}   Set Operation    3
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/containerUnderCSEBase
	Change Content Attribute In  ${requestPrimitive} 	labels	updateContainer
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   update
    Should Contain  ${result}   container

#   test the retrieve part with hierURI

    Change Attribute In     ${requestPrimitive}   Set Operation    2
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#   Update this method once "response" is following the document
    Should Contain  ${result}   updateContainer
    Should Contain  ${result}   container

2.26 InValid Update container's name
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    updateContainer
    Change Attribute In     ${requestPrimitive}   Set Operation    3
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/containerUnderCSEBase
	Change Content Attribute In  ${requestPrimitive} 	resourceName	updateContainer
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Not Contain  ${result}    updateContainer


#   test the retrieve part with hierURI

    Change Attribute In     ${requestPrimitive}   Set Operation    2
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#   Update this method once "response" is following the document
    Should Contain  ${result}   containerUnderCSEBase
    Should Contain  ${result}   container

#------------------  Create -----------------------

2.31 Valid Input for container under container without name
	${requestPrimitive} =    Get Initilazed Create Request Primitive
	Change Attribute In     ${requestPrimitive}   Set Name    createContainerUnderContainer
    Change Attribute In     ${requestPrimitive}   Set ResourceType    3
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/containerUnderCSEBase
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   container

#   test the retrieve part with hierURI
    ${hierURI} =     Get Hier URI   ${result}
    ${nonhierURI} =  Get Non Hier URI   ${result}
    Change Attribute In     ${requestPrimitive}   Set Operation    2
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/${hierURI}
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#   Update this method once "response" is following the document
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   container

2.32 Valid Input for container under container with name and label
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    createContainerUnderContainer
    Change Attribute In     ${requestPrimitive}   Set ResourceType    3
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/containerUnderCSEBase
    Change Content Attribute In  ${requestPrimitive} 	resourceName	containerUnderContainer
    Change Content Attribute In  ${requestPrimitive} 	labels	container1
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   container

#   test the retrieve part with hierURI
    ${hierURI} =     Get Hier URI   ${result}
    ${nonhierURI} =  Get Non Hier URI   ${result}
    Change Attribute In     ${requestPrimitive}   Set Operation    2
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/${hierURI}
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#   Update this method once "response" is following the document
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   container
    Should Be Equal   ${hierURI}    InCSE1/containerUnderCSEBase/containerUnderContainer

2.33 InValid Input for container under container with name (alredy exist)
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    createContainerUnderContainer
    Change Attribute In     ${requestPrimitive}   Set ResourceType    3
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/containerUnderCSEBase
    Change Content Attribute In  ${requestPrimitive} 	resourceName	containerUnderContainer
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   RpcError
    Should Contain  ${result}   5106

2.34 Invalid Input for container under container with name (Invalid name)
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    createContainerUnderContainer
    Change Attribute In     ${requestPrimitive}   Set ResourceType    3
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/containerUnderCSEBase
    Change Content Attribute In  ${requestPrimitive} 	resourceName	containerUnder/Container
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   RpcError


#----------  Update And Retrieve -------------------

2.35 Valid Update container's labels
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    updateContainer
    Change Attribute In     ${requestPrimitive}   Set Operation    3
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/containerUnderCSEBase/containerUnderContainer
	Change Content Attribute In  ${requestPrimitive} 	labels	updateContainer
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   update
    Should Contain  ${result}   container

#   test the retrieve part with hierURI

    Change Attribute In     ${requestPrimitive}   Set Operation    2
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#   Update this method once "response" is following the document
    Should Contain  ${result}   updateContainer
    Should Contain  ${result}   container

2.36 InValid Update container's name
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    updateAE
    Change Attribute In     ${requestPrimitive}   Set Operation    3
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/containerUnderCSEBase/containerUnderContainer
	Change Content Attribute In  ${requestPrimitive} 	resourceName	updateContainer
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Not Contain  ${result}    updateContainer


#   test the retrieve part with hierURI

    Change Attribute In     ${requestPrimitive}   Set Operation    2
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
#   Update this method once "response" is following the document
    Should Contain  ${result}   containerUnderContainer
    Should Contain  ${result}   container

#--------------------- test one deeper layer

2.51 Invalid Input for AE under container withoutname(mess up layer)
	${requestPrimitive} =    Get Initilazed Create Request Primitive
	Change Attribute In     ${requestPrimitive}   Set Name    createContainerUnderContainer
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/validName
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   RpcError

2.52 Invalid Input for AE under container with name (mess up layer)
    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Name    createContainerUnderContainer
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/validName
    Change Content Attribute In  ${requestPrimitive} 	resourceName	containerUnderContainer
    Change Content Attribute In  ${requestPrimitive} 	labels	container1
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   RpcError


#==================================================
#       ContentInstance Test
#==================================================

#------------------  Create -----------------------

3.11 Valid contentInstance under CSEBase/container without content
	${requestPrimitive} =    Get Initilazed Create Request Primitive
	Change Attribute In     ${requestPrimitive}   Set Name    createContentInstanceUnderContainerOfBase
    Change Attribute In     ${requestPrimitive}   Set ResourceType    4
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/containerUnderCSEBase
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   contentInstance

    # Create more contentInstance for next test
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   contentInstance

    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   contentInstance

3.12 Valid contentInstance under CSEBase/container with content and name
	${requestPrimitive} =    Get Initilazed Create Request Primitive
	Change Attribute In     ${requestPrimitive}   Set Name    createContentInstanceUnderContainerOfBase
    Change Attribute In     ${requestPrimitive}   Set ResourceType    4
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/containerUnderCSEBase
    Change Content Attribute In  ${requestPrimitive} 	content	        SomeContent
    Change Content Attribute In  ${requestPrimitive} 	resourceName    contentInstance1
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   contentInstance

3.21 Valid contentInstance under AE/container without content
	${requestPrimitive} =    Get Initilazed Create Request Primitive
	Change Attribute In     ${requestPrimitive}   Set Name    createContentInstanceUnderContainerOfAE
    Change Attribute In     ${requestPrimitive}   Set ResourceType    4
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/validName/containerUnderAE
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   contentInstance

3.22 Valid contentInstance under AE/container with content
	${requestPrimitive} =    Get Initilazed Create Request Primitive
	Change Attribute In     ${requestPrimitive}   Set Name    createContentInstanceUnderContainerOfAE
    Change Attribute In     ${requestPrimitive}   Set ResourceType    4
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/validName/containerUnderAE
    Change Content Attribute In  ${requestPrimitive} 	content	        SomeContent
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   contentInstance

3.31 Valid contentInstance under container/container without content
	${requestPrimitive} =    Get Initilazed Create Request Primitive
	Change Attribute In     ${requestPrimitive}   Set Name    createContentInstanceUnderContainerOfContainer
    Change Attribute In     ${requestPrimitive}   Set ResourceType    4
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/containerUnderCSEBase/containerUnderContainer
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   contentInstance

3.32 Valid contentInstance under container/container with content
	${requestPrimitive} =    Get Initilazed Create Request Primitive
	Change Attribute In     ${requestPrimitive}   Set Name    createContentInstanceUnderContainerOfContainer
    Change Attribute In     ${requestPrimitive}   Set ResourceType    4
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/containerUnderCSEBase/containerUnderContainer
    Change Content Attribute In  ${requestPrimitive} 	content	        SomeContent
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   responseStatusCode
    Should Contain  ${result}   contentInstance

3.51 Invalid contentInstance under AE
	${requestPrimitive} =    Get Initilazed Create Request Primitive
	Change Attribute In     ${requestPrimitive}   Set Name    createContentInstanceUnderAE
    Change Attribute In     ${requestPrimitive}   Set ResourceType    4
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/validName
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   RpcError

3.52 Invalid contentInstance under CSEBase
	${requestPrimitive} =    Get Initilazed Create Request Primitive
	Change Attribute In     ${requestPrimitive}   Set Name    createContentInstanceUnderCSEBase
    Change Attribute In     ${requestPrimitive}   Set ResourceType    4
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   RpcError

#==================================================



#==================================================
#       Delete Test
#==================================================

#----- Delte contentInstance and Retrieve  --------
4.11 Delete the contentInstance named contentInstance1
	${requestPrimitive} =    Get Initilazed Create Request Primitive
	Change Attribute In     ${requestPrimitive}   Set Name    deleteContentInstance
    Change Attribute In     ${requestPrimitive}   Set Operation    4
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/containerUnderCSEBase/contentInstance1
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   delete
    Should Contain  ${result}   contentInstance

# use retrieve parent to check this contentInstance is deleted


# Delte again should return error


#--------- Delte container  -----------------------

4.12 Delete the container named containerUnderCSEBase
	${requestPrimitive} =    Get Initilazed Create Request Primitive
	Change Attribute In     ${requestPrimitive}   Set Name    deleteContainer
    Change Attribute In     ${requestPrimitive}   Set Operation    4
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/containerUnderCSEBase
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   delete
    Should Contain  ${result}   container


#--------- Delte AE  ------------------------------

4.13 Delete the AE named validName
	${requestPrimitive} =    Get Initilazed Create Request Primitive
	Change Attribute In     ${requestPrimitive}   Set Name    deleteAE
    Change Attribute In     ${requestPrimitive}   Set Operation    4
    Change Attribute In     ${requestPrimitive}   Set To    http://localhost:8282/InCSE1/validName
    ${result} =  Send Request And GetResponse   ${requestPrimitive}
    Should Contain  ${result}   delete
    Should Contain  ${result}   AE




#   Delete the whole tree

    ${requestPrimitive} =    Get Initilazed Create Request Primitive
    Change Attribute In     ${requestPrimitive}   Set Operation    4
    ${result} =  Send Request And GetResponse   ${requestPrimitive}