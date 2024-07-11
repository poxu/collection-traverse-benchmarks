# collection-traverse-benchmarks
Benchmarks to measure collection traverse

# Running on windows

First
```cmd
mvnw clean package
```

**!!!!!you can not put first and second command in the same bat file!!!!!**

Second
```cmd
java -cp target\benchmarks.jar com.evilcorp.suite.HashMapVsLinkedHashMapSuite
```

Or second you can run
```cmd
run-hash-map-vs-linked-hash-map.bat
```

