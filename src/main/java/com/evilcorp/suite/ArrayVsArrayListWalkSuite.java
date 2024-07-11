package com.evilcorp.suite;

import com.evilcorp.benchmark.walk.legacy.ArrayListWalkBenchmark;
import com.evilcorp.benchmark.walk.legacy.ArrayWalkBenchmark;
import com.evilcorp.benchmark.walk.legacy.LinkedListWalkBenchmark;
import com.evilcorp.benchmark.walk.legacy.LongArrayWalkBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class ArrayVsArrayListWalkSuite {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(ArrayListWalkBenchmark.class.getCanonicalName())
            .include(ArrayWalkBenchmark.class.getCanonicalName())
            .include(LinkedListWalkBenchmark.class.getCanonicalName())
            .include(LongArrayWalkBenchmark.class.getCanonicalName())
            .build();

        new Runner(opt).run();
    }

}
