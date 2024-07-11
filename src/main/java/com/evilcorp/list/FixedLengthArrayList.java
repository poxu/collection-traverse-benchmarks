package com.evilcorp.list;

public class FixedLengthArrayList<TYPE> {
    private final int size;
    TYPE[] els;

    public FixedLengthArrayList(int size) {
        this.size = size;
        els = (TYPE[]) new Object[size];
    }

    public void put(int idx, TYPE value) {
        els[idx] = value;
    }

    public TYPE get(int idx) {
        return els[idx];
    }

    public int size() {
        return size;
    }
}
