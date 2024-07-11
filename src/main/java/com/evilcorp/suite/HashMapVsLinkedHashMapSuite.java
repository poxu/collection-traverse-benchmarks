package com.evilcorp.suite;

import com.evilcorp.benchmark.walk.inherit.HashMapWalkBenchmark;
import com.evilcorp.benchmark.walk.inherit.LinkedHashMapWalkBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


public class HashMapVsLinkedHashMapSuite {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(HashMapWalkBenchmark.class.getCanonicalName())
            .include(LinkedHashMapWalkBenchmark.class.getCanonicalName())
            .warmupIterations(2)
            .measurementIterations(2)
            .forks(2)
            .build();

        new Runner(opt).run();
    }

}
