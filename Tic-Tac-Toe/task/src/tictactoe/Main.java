package tictactoe;

import java.util.Scanner;

public class Main {

    private static final char[][] grid = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '},
    };
    private static final int N = 3;
    private static final char Empty = ' ';

    private static char currentPlayer = 'X';

    public static void main(String[] args) {

        boolean done = false;
        int[] move;

        printGrid();
        while (!done) {
            move = getUserMove();
            printGrid();
            done = checkMove(move);
            switchPlayers();
        }
//        printGrid();
    }

    private static void switchPlayers() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static int[] getUserMove() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the coordinates: ");
            int row;
            int col;
            try {
                String rowStr = scanner.next();
                String colStr = scanner.next();
                row = Integer.parseInt(rowStr);
                col = Integer.parseInt(colStr);

                if (row < 1 || row > 3 || col < 1 || col > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
                if (grid[row - 1][col - 1] != Empty) {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }
                grid[row - 1][col - 1] = currentPlayer;
                return new int[] {row - 1, col - 1};
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    private static boolean checkMove(int[] move) {
        boolean hasRow = hasRowFor(move);

        int numFreeCells = numberOf(Empty);

        if (!hasRow) {
            if (numFreeCells == 0) {
                System.out.println("Draw");
                return true;
            }
        } else {
            System.out.printf("%c wins\n", currentPlayer);
            return true;
        }
        return false;
    }

    private static boolean hasRowFor(int[] move) {

        boolean fullRow = true;
        for (int col = 0; col < N; col++) {
            if (grid[move[0]][col] != currentPlayer)  {
                fullRow = false;
                break;
            }
        }
        if (fullRow) return true;

        fullRow = true;
        for (int row = 0; row < N; row++) {
            if (grid[row][move[1]] != currentPlayer)  {
                fullRow = false;
                break;
            }
        }
        if (fullRow) return true;


        if (move[0] == move[1]) {
            fullRow = true;
            for (int i = 0; i < N; i++) {
                if (grid[i][i] != currentPlayer)  {
                    fullRow = false;
                    break;
                }
            }
            if (fullRow) return true;
        }

        if (move[0] + move[1] == N - 1) {
            fullRow = true;
            for (int i = 0; i < N; i++) {
                if (grid[i][N - 1 -i] != currentPlayer)  {
                    fullRow = false;
                    break;
                }
            }
            if (fullRow) return true;
        }

        return false;
    }

    private static int numberOf(char x) {
        int count = 0;
        for (char[] vec : grid) {
            for (char c : vec) {
                if (c == x) count++;
            }
        }
        return count;
    }

    private static void readConfiguration() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cells: ");
        String str = scanner.next();
        for(int i = 0; i < str.length(); i++) {
            grid[i / 3][ i % 3] = str.charAt(i);
        }
    }

    private static void printGrid() {
        System.out.println("---------");
        for(char[] row : grid) {
            System.out.print("| ");
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
