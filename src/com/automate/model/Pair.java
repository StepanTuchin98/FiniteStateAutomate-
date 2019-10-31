package com.automate.model;

public class Pair {
    private boolean isExist;
    private int size;

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Pair(boolean isExist, int size) {
        this.isExist = isExist;
        this.size = size;
    }
}
