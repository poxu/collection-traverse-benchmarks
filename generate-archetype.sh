#!/bin/sh
./mvnw archetype:generate \
  -DinteractiveMode=false \
  -DarchetypeGroupId=org.openjdk.jmh \
  -DarchetypeArtifactId=jmh-java-benchmark-archetype \
  -DgroupId=com.evilcorp \
  -DartifactId=collection-traverse-bench \
  -Dversion=1.0