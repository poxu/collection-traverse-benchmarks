package com.evilcorp.benchmark;

import com.evilcorp.dto.MatrixDescription;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.ThreadLocalRandom;

import static com.evilcorp.dto.MatrixDescription.dim;

@State(Scope.Benchmark)
public abstract class BaseCollectionTest<T> {

    private final static MatrixDescription[] dims = {
        dim(200, 200),
        dim(2000, 20),
        dim(20000, 2)
    };

    @Param({"0"})
    private int matrixType;

    protected int rows = 200;
    protected int cols = 200;

    protected T testObject;

    @Setup(Level.Iteration)
    public void setup() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        MatrixDescription dim = dims[matrixType];
        rows = dim.getRows();
        cols = dim.getCols();

        testObject = create(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                set(i, j, i * cols + j);
            }
        }
    }

    public abstract void set(int i, int j, int val);

    public abstract T create(int rows, int cols);
}
