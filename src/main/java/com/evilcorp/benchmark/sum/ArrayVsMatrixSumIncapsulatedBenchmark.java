package com.evilcorp.benchmark.sum;

import com.evilcorp.dto.MatrixDescription;
import com.evilcorp.matrix.ArrayMatrix;
import com.evilcorp.matrix.TwoDimMatrix;
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
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static com.evilcorp.dto.MatrixDescription.dim;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Benchmark)
public class ArrayVsMatrixSumIncapsulatedBenchmark {

    private final static MatrixDescription[] dims = {
        dim(200, 200),
        dim(2000, 20),
        dim(20000, 2)
    };

    @Param({"0"
//            , "1"
//            , "2"
    })
    private int matrixType;

    private int rows = 2000;
    private int cols = 20;

    private int[][] matrix;
    private int[] array;
    private ArrayMatrix arrMat;
    private TwoDimMatrix dimMat;

    @Setup(Level.Iteration)
    public void setup() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        MatrixDescription dim = dims[matrixType];
        rows = dim.getRows();
        cols = dim.getCols();

        arrMat = new ArrayMatrix(rows, cols);
        dimMat = new TwoDimMatrix(rows, cols);

        matrix = new int[rows][];
        for (int i = 0; i < rows; i++) {
            matrix[i] = new int[cols];
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt();
            }
        }
        array = new int[rows * cols];
        for (int i = 0; i < rows * cols; i++) {
            array[i] = random.nextInt();
        }
    }

    @Benchmark
    public int sumArrayMatrix() {
        int sum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum += arrMat.valueAt(i, j);
            }
        }
        return sum;
    }

    @Benchmark
    public int sumDimMatrix() {
        int sum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum += dimMat.valueAt(i, j);
            }
        }
        return sum;
    }

    @Benchmark
    public int sumDimMatrixReverse() {
        int sum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum += dimMat.valueAt(j, i);
            }
        }
        return sum;
    }

    @Benchmark
    public int sumArrayMatrixReverse() {
        int sum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum += arrMat.valueAt(j, i);
            }
        }
        return sum;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(".*" + ArrayVsMatrixSumIncapsulatedBenchmark.class.getSimpleName() + ".*")
            .build();

        new Runner(opt).run();
    }

}
