package com.evilcorp.benchmark.walk.inherit;

import com.evilcorp.benchmark.BaseCollectionTest;
import com.evilcorp.matrix.TwoDimMatrix;
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
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Benchmark)
public class TwoDimMatrixWalkBenchmark
    extends BaseCollectionTest<TwoDimMatrix> {

    @Benchmark
    public void walkDimMatrix(Blackhole box) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                box.consume(testObject.valueAt(i, j));
            }
        }
    }

    @Benchmark
    public void walkDimMatrixReverse(Blackhole box) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                box.consume(testObject.valueAt(j, i));
            }
        }
    }

    @Override
    public void set(int i, int j, int val) {
        testObject.set(i, j, val);
    }

    @Override
    public TwoDimMatrix create(int rows, int cols) {
        return new TwoDimMatrix(rows, cols);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(TwoDimMatrixWalkBenchmark.class.getCanonicalName())
            .include(com.evilcorp.benchmark.walk.legacy.TwoDimMatrixWalkBenchmark.class.getCanonicalName())
            .build();

        new Runner(opt).run();
    }

}
