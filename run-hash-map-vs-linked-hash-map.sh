#!/bin/sh
./mvnw clean package
java -cp target/benchmarks.jar com.evilcorp.suite.HashMapVsLinkedHashMapSuite

