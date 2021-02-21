package com.casestudy.chess;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    private static final int MIN = 1;

    public static int question1(int n, int r, int c) {
        return findAllowed(n, r, c, null);
    }

    public static int question2(int n, int r, int c, int k, Set<Block> blocks) {
        if (blocks.size() != k) {
            System.err.println("Block size not equal k value.");
            return -1;
        }
        Set<Block> extended = extendBlocks(n, c, r, blocks);
        return findAllowed(n, r, c, extended);
    }

    private static Set<Block> extendBlocks(int n, int c, int r, Set<Block> blocks) {
        Set<Block> extendedBlocks = new HashSet<Block>(blocks);
        for (Block currentBlock : blocks) {
            if (currentBlock.getX() < c && currentBlock.getY() == r) {
                for (int x = currentBlock.getX(); x >= MIN; --x) {
                    extendedBlocks.add(new Block(x, currentBlock.getY()));
                }
            } else if (currentBlock.getX() > c && currentBlock.getY() == r) {
                for (int x = currentBlock.getX(); x <= n; ++x) {
                    extendedBlocks.add(new Block(x, currentBlock.getY()));
                }
            } else if (currentBlock.getX() == c && currentBlock.getY() < r) {
                for (int y = currentBlock.getY(); y >= MIN; --y) {
                    extendedBlocks.add(new Block(currentBlock.getX(), y));
                }
            } else if (currentBlock.getX() == c && currentBlock.getY() > r) {
                for (int y = currentBlock.getY(); y <= n; ++y) {
                    extendedBlocks.add(new Block(currentBlock.getX(), y));
                }
            } else if (currentBlock.getX() < c && currentBlock.getY() > r) { // 1
                if (c - currentBlock.getX() == currentBlock.getY() - r) {
                    int y = currentBlock.getY();
                    for (int x = currentBlock.getX(); x >= MIN; --x) {
                        if (y <= n) {
                            extendedBlocks.add(new Block(x, y));
                        }
                        ++y;
                    }
                }
            } else if (currentBlock.getX() > c && currentBlock.getY() > r) { // 2
                if (c - currentBlock.getX() == currentBlock.getY() - r) {
                    int y = currentBlock.getY();
                    for (int x = currentBlock.getX(); x <= n; ++x) {
                        if (y <= n) {
                            extendedBlocks.add(new Block(x, y));
                        }
                        ++y;
                    }
                }
            } else if (currentBlock.getX() > c && currentBlock.getY() < r) { // 3
                if (c - currentBlock.getX() == currentBlock.getY() - r) {
                    int y = currentBlock.getY();
                    for (int x = currentBlock.getX(); x <= n; ++x) {
                        if (y >= MIN) {
                            extendedBlocks.add(new Block(x, y));
                        }
                        --y;
                    }
                }
            } else if (currentBlock.getX() < c && currentBlock.getY() < r) { // 4
                if (c - currentBlock.getX() == currentBlock.getY() - r) {
                    int y = currentBlock.getY();
                    for (int x = currentBlock.getX(); x >= MIN; --x) {
                        if (y >= MIN) {
                            extendedBlocks.add(new Block(x, y));
                        }
                        --y;
                    }
                }
            }
        }
        return extendedBlocks;
    }

    private static boolean checkBlockedBase(int x, int y, Set<Block> blocks) {
        if (blocks == null) {
            //System.err.println("List of blocks is null. ");
            return true;
        }
        for (Block block : blocks) {
            if (block.getX() == x && block.getY() == y) {
                return false;
            }
        }
        return true;
    }

    private static int findAllowed(int n, int r, int c, Set<Block> blocks) {
        int counter = 0;
        if (c > n || r > n) {
            System.err.println("Invalid parameter n:" + n + " r:" + r + " c:" + c);
            return -1;
        }
        counter += part1(n, r, c, blocks);
        counter += part2(n, r, c, blocks);
        counter += part3(n, r, c, blocks);
        counter += part4(n, r, c, blocks);
        return counter;
    }

    /**
     * x: c,..1
     * y: r,..n
     * <p>
     * (x,r..n) x: c == x , r != y
     * (c..1,y) y: c != x , r == y
     */
    private static int part1(int n, int r, int c, Set<Block> blocks) {
        int counter = 0;
        for (int x = c; x >= MIN; --x) {
            for (int y = r; y <= n; ++y) {
                if (checkBlockedBase(x, y, blocks) &&
                        ((!(c == x && r == y) && (c - x == y - r))
                                || ((c == x && r != y) || (c != x && r == y)))) {
                    ++counter;
                }
            }
        }
        return counter;
    }

    /**
     * x: c,..n
     * y: r,..n
     * <p>
     * (c..n, y) x: c != x , r == y
     */
    private static int part2(int n, int r, int c, Set<Block> blocks) {
        int counter = 0;
        for (int x = c; x <= n; ++x) {
            for (int y = r; y <= n; ++y) {
                if (checkBlockedBase(x, y, blocks) &&
                        ((!(c == x && r == y) && x - c == y - r) || (c != x && r == y))) {
                    ++counter;
                }
            }
        }
        return counter;
    }

    /**
     * x: c,..n
     * y: r,..1
     * <p>
     * (x, r..1) y: c == x , r != y
     */
    private static int part3(int n, int r, int c, Set<Block> blocks) {
        int counter = 0;
        for (int x = c; x <= n; ++x) {
            for (int y = r; y >= MIN; --y) {
                if (checkBlockedBase(x, y, blocks) &&
                        ((!(c == x && r == y) && x - c == r - y)
                                || (c == x && r != y))) {
                    ++counter;
                }
            }
        }
        return counter;
    }

    /**
     * x: c,..1
     * y: r,..1
     * <p>
     * -
     */
    private static int part4(int n, int r, int c, Set<Block> blocks) {
        int counter = 0;
        for (int x = c; x >= MIN; --x) {
            for (int y = r; y >= MIN; --y) {
                if (checkBlockedBase(x, y, blocks) && ((!(c == x && r == y) && c - x == r - y))) {
                    ++counter;
                }
            }
        }
        return counter;
    }
}
