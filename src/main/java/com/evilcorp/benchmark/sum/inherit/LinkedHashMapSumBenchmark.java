package com.evilcorp.benchmark.sum.inherit;

import com.evilcorp.benchmark.BaseCollectionTest;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Benchmark)
public class LinkedHashMapSumBenchmark extends BaseCollectionTest<LinkedHashMap<Integer, String>> {

    @Override
    public void set(int i, int j, int val) {
        testObject.put(i * cols + j, String.valueOf(val));
    }

    @Override
    public LinkedHashMap<Integer, String> create(int rows, int cols) {
        return new LinkedHashMap<>();
    }

    @Benchmark
    public int sum() {
        int sum = 0;
        for (Map.Entry<Integer, String> e : testObject.entrySet()) {
            sum += e.getKey();
        }
        return sum;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(LinkedHashMapSumBenchmark.class.getCanonicalName())
            .build();

        new Runner(opt).run();
    }

}
