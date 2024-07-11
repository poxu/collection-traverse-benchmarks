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
public class ArrayWalkBenchmark {

    @Param({"40000"
//            , "1"
//            , "2"
    })
    private int arrayLength;

    private int[] array;

    @Setup(Level.Iteration)
    public void setup() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        int size = arrayLength;

        array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }

    }

    @Benchmark
    public void walkArray(Blackhole box) {
        for (int j : array) {
            box.consume(j);
        }
    }

    @Benchmark
    public void walkArrayIdx(Blackhole box) {
        for (int i = 0; i < array.length; i++) {
            box.consume(array[i]);
        }
    }

    //    @Benchmark
    public void walkArrayIdxBoxed(Blackhole box) {
        final int[] arr = array;
        for (int i = 0; i < arr.length; i++) {
            final int j = arr[i];
            box.consume(j);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(ArrayWalkBenchmark.class.getCanonicalName())
            .build();

        new Runner(opt).run();

    }

}
