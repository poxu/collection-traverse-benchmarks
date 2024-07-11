package com.evilcorp.list;

public class FixedLengthNoGenericArrayList {
    private final int size;
    Integer[] els;

    public FixedLengthNoGenericArrayList(int size) {
        this.size = size;
        els = new Integer[size];
    }

    public void put(int idx, Integer value) {
        els[idx] = value;
    }

    public Integer get(int idx) {
        return els[idx];
    }

    public int size() {
        return size;
    }
}
