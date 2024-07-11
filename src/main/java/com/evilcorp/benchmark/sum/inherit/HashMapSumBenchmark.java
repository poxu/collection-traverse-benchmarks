package com.evilcorp.benchmark.sum.inherit;

import com.evilcorp.benchmark.BaseCollectionTest;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Benchmark)
public class HashMapSumBenchmark extends BaseCollectionTest<HashMap<Integer, String>> {

    @Override
    public void set(int i, int j, int val) {
        testObject.put(i * cols + j, String.valueOf(val));
    }

    @Override
    public HashMap<Integer, String> create(int rows, int cols) {
        return new HashMap<>();
    }

    @Benchmark
    public int sumHashMap() {
        int sum = 0;
        for (Map.Entry<Integer, String> e : testObject.entrySet()) {
            sum += e.getKey();
        }
        return sum;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(HashMapSumBenchmark.class.getCanonicalName())
            .build();

        new Runner(opt).run();
    }

}
