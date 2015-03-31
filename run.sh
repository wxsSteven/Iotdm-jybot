#!/bin/bash
export CLASSPATH=${PWD}/target/robot-1.0-SNAPSHOT-jar-with-dependencies.jar
jybot ${PWD}/src/test/java/robot/Test.robot
