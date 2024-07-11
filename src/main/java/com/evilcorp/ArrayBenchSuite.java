package com.evilcorp;

import com.evilcorp.benchmark.walk.legacy.FixedArrayListWalkBenchmark;
import com.evilcorp.benchmark.walk.legacy.IntegerFixedNoGenericArrayListWalkBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class ArrayBenchSuite {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
//            .include(LongArrayWalkBenchmark.class.getCanonicalName())
//            .include(BigLongArrayWalkBenchmark.class.getCanonicalName())
//            .include(ArrayListWalkBenchmark.class.getCanonicalName())
            .include(FixedArrayListWalkBenchmark.class.getCanonicalName())
//            .include(IntegerFixedArrayListWalkBenchmark.class.getCanonicalName())
            .include(IntegerFixedNoGenericArrayListWalkBenchmark.class.getCanonicalName())
            .forks(2)
            .warmupIterations(2)
            .measurementIterations(2)
            .build();

        new Runner(opt).run();
    }
}
