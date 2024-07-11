package com.evilcorp.benchmark.walk.inherit;

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

import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class MatrixWalkBenchmark extends BaseCollectionTest<int[][]> {

    @Benchmark
    public void walkMatrix(Blackhole box) {
        int[][] matrix = this.testObject;
        final int rows = matrix.length;
        for (int i = 0; i < rows - 1; i++) {
            final int cols = matrix[i].length;
            for (int j = 0; j < cols - 1; j++) {
                box.consume(matrix[i][j]);
            }
        }
    }

    @Override
    public void set(int i, int j, int val) {
        testObject[i][j] = val;
    }

    @Override
    public int[][] create(int rows, int cols) {
        return new int[rows][cols];
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(MatrixWalkBenchmark.class.getCanonicalName())
            .build();

        new Runner(opt).run();
    }
}
