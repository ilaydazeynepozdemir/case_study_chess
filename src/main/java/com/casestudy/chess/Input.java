package com.casestudy.chess;

import java.util.Set;

public class Input {
    private int n;
    private int c; // x
    private int r; // y
    private int k; // unnecassary
    private Set<Block> blocks;

    Input(int n, int c, int r, int k, Set<Block> blocks) {
        this.n = n;
        this.c = c;
        this.r = r;
        this.k = k;
        this.blocks = blocks;
    }

    public int getC() {
        return c;
    }

    public int getN() {
        return n;
    }

    public int getR() {
        return r;
    }

    public int getK() {
        return k;
    }

    public Set<Block> getBlocks() {
        return blocks;
    }

    @Override
    public String toString() {
        return "Input{" +
                "n=" + n +
                ", c=" + c +
                ", r=" + r +
                ", blocks=" + blocks +
                '}';
    }
}
