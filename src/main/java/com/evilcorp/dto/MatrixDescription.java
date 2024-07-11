package com.evilcorp.dto;

public class MatrixDescription {
    private final int rows;
    private final int cols;

    public MatrixDescription(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public static MatrixDescription dim(int rows, int cols) {
        return new MatrixDescription(rows, cols);
    }
}
