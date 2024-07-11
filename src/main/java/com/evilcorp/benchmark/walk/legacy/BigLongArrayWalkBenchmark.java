package com.evilcorp.benchmark.walk.legacy;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Benchmark)
public class BigLongArrayWalkBenchmark {

    @Param({"40000"
//            , "1"
//            , "2"
    })
    private int arrayLength;

    private Long[] longArray;

    @Setup(Level.Iteration)
    public void setup() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        int size = arrayLength;

        longArray = new Long[size];
        for (int i = 0; i < size; i++) {
            longArray[i] = random.nextLong();
        }
    }

    @Benchmark
    public void walkArray(Blackhole box) {
        for (Long j : longArray) {
            box.consume(j);
        }
    }

    @Benchmark
    public void walkArrayIdx(Blackhole box) {
        for (int i = 0; i < longArray.length; i++) {
            final Long j = longArray[i];
            box.consume(j);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(LongArrayWalkBenchmark.class.getSimpleName())
//                .include(BigLongArrayWalkBenchmark.class.getSimpleName())
//                .include(ArrayListWalkBenchmark.class.getSimpleName())
            .build();

        new Runner(opt).run();
    }

}
