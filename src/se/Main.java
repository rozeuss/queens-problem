package se;

import se.queens.NQueenProblem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter Number of Queens");
        Scanner s = new Scanner(System.in);
        int input = s.nextInt();
        new NQueenProblem(input).solve();
    }

}
