package com.evilcorp.matrix;

public class ArrayMatrix implements Matrix {
    private final int rows;
    private final int cols;
    private final int[] array;

    public ArrayMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.array = new int[rows * cols];
    }

    @Override
    public int valueAt(int row, int col) {
        return array[row * cols + col];
    }

    public void set(int row, int col, int value) {
        array[row * cols + col] = value;
    }

    public int valueAt(int num) {
        return array[num];
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
