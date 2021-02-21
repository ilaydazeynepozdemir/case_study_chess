package com.casestudy.chess;

import java.util.HashSet;
import java.util.Set;

import static com.casestudy.chess.Solution.question1;
import static com.casestudy.chess.Solution.question2;

public class Main {
    public static void main(String[] args) {
        System.out.println("##################");
        System.out.println("### Question 1 ###");
        System.out.println("##################");
        Input inputQ1 = new Input(8, 4, 4, 0, null);
        System.out.println(inputQ1);
        System.out.println("RESULT: "+question1(inputQ1.getN(), inputQ1.getR(), inputQ1.getC()));
        System.out.println("##################");
        System.out.println("### Question 2 ###");
        System.out.println("##################");
        Set<Block> blocks = new HashSet<Block>();
        blocks.add(new Block(5, 3));
        Input inputQ2 = new Input(8, 4, 4, 1, blocks);
        System.out.println(inputQ2);
        System.out.println("RESULT: "+question2(inputQ2.getN(), inputQ2.getR(), inputQ2.getC(), inputQ2.getK(), inputQ2.getBlocks()));
        System.out.println("##################");
        System.out.println("##################");
        System.out.println("### Sample 1 ###");
        System.out.println("##################");
        Input inputS1 = new Input(4, 4, 4, 0, null);
        System.out.println(inputS1);
        System.out.println("RESULT: "+question1(inputS1.getN(), inputS1.getR(), inputS1.getC()));
        System.out.println("##################");
        System.out.println("##################");
        System.out.println("### Sample 2 ###");
        System.out.println("##################");
        blocks = new HashSet<Block>();
        blocks.add(new Block(2, 4));
        blocks.add(new Block(3, 2));
        blocks.add(new Block(5, 5));
        Input inputS2 = new Input(5, 3, 4, 3, blocks);
        System.out.println(inputS2);
        System.out.println("RESULT: "+question2(5, 4, 3, 3, blocks));
        System.out.println("##################");
    }
}
