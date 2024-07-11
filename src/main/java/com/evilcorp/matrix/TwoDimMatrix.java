package com.evilcorp.matrix;

public class TwoDimMatrix implements Matrix {
    private final int rows;
    private final int cols;
    private final int[][] matrix;

    public TwoDimMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new int[rows][cols];
    }

    @Override
    public int valueAt(int row, int col) {
        return matrix[row][col];
    }

    public void set(int row, int col, int value) {
        matrix[row][col] = value;
    }

    @Override
    public int rows() {
        return rows;
    }

    @Override
    public int cols() {
        return cols;
    }
}
