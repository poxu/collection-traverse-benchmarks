package com.evilcorp.suite;

import com.evilcorp.benchmark.walk.inherit.HashMapWalkBenchmark;
import com.evilcorp.benchmark.walk.inherit.LinkedHashMapWalkBenchmark;
import com.evilcorp.benchmark.walk.legacy.LinkedListWalkBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


public class WalkSuite {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
//                .include(ArrayMatrixWalkBenchmark.class.getCanonicalName())
//                .include(ArrayWalkBenchmark.class.getCanonicalName())
//                .include(MatrixWalkBenchmark.class.getCanonicalName())
//                .include(MatrixWalkColsRowsBenchmark.class.getCanonicalName())
            .include(HashMapWalkBenchmark.class.getCanonicalName())
            .include(LinkedHashMapWalkBenchmark.class.getCanonicalName())
            .include(LinkedListWalkBenchmark.class.getCanonicalName())

            .warmupIterations(2)
            .measurementIterations(2)
//                .addProfiler("perfnorm")
            .forks(2)
//                .jvmArgsAppend("-Djmh.blackhole.autoDetect=false")
            .build();
        new Runner(opt).run();
    }

}
