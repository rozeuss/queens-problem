package se.queens;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NQueenProblem {

    private final Position[] positions;
    private final int[][] result;
    private final int size;
    private int iterations = 0;

    public NQueenProblem(int n) {
        this.size = n;
        this.positions = new Position[size];
        this.result = new int[size][size];
    }

    private static void prettyPrint(int[][] result) {
        System.out.println(Arrays.stream(result).map(Arrays::toString).collect(Collectors.joining("\n")));
        System.out.println();
    }

    public void solve() {
        if (getSolution(size, 0)) {
            System.out.println("Found solution.");
            prettyPrint(result);
        } else {
            System.out.println("Solution not available.");
        }
    }

    private boolean getSolution(int n, int row) {
        System.out.println("Iteration: " + iterations++);
        prettyPrint(result);
        if (n == 2 || n == 3) {
            return false;
        }

        if (row == n) {
            return true;
        }

        for (int col = 0; col < n; col++) {
            boolean isSafe = true;
            positions[row] = new Position(row, col);

            for (int placedQueen = 0; placedQueen < row; placedQueen++) {
                if (isQueenNotSafe(placedQueen, col, row)) {
                    isSafe = false;
                }
            }

            if (isSafe) {
                result[positions[row].row][positions[row].col] = 1;
                if (getSolution(n, row + 1)) {
                    return true;
                }
                System.out.println("Returning queen from " + positions[row].toString());
                result[positions[row].row][positions[row].col] = 0;
            }
        }
        return false;
    }

    private boolean isQueenNotSafe(int placedQueen, int col, int row) {
        return checkColumn(placedQueen, col)
                || checkDiagonal(placedQueen, col, row)
                || checkAntidiagonal(placedQueen, col, row);
    }

    private boolean checkColumn(int placedQueen, int col) {
        return positions[placedQueen].col == col;
    }

    private boolean checkDiagonal(int placedQueen, int col, int row) {
        return positions[placedQueen].row - positions[placedQueen].col == row - col;
    }

    private boolean checkAntidiagonal(int placedQueen, int col, int row) {
        return positions[placedQueen].row + positions[placedQueen].col == row + col;
    }

}