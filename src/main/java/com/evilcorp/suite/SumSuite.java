package com.evilcorp.suite;

import com.evilcorp.benchmark.sum.SumArrayBenchmark;
import com.evilcorp.benchmark.sum.SumMatrixBenchmark;
import com.evilcorp.benchmark.sum.SumMatrixColsBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


public class SumSuite {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(SumMatrixBenchmark.class.getCanonicalName())
            .include(SumArrayBenchmark.class.getCanonicalName())
            .include(SumMatrixColsBenchmark.class.getCanonicalName())
            .warmupIterations(2)
            .measurementIterations(2)
            .forks(2)
            .build();

        new Runner(opt).run();
    }

}
