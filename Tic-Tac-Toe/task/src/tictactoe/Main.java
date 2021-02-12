package tictactoe;

import java.util.Scanner;

public class Main {

    private static char[][] grid = {
            {'_', '_', '_'},
            {'_', '_', '_'},
            {'_', '_', '_'},
    };
    private static final int rows = 3;
    private static final int cols = 3;

    private static char currentUser = 'X';

    public static void main(String[] args) {

        while (true) {
            printGrid();
            getUserInput();
            
        }

//        readConfiguration();
//        checkGrid();

        getUserInput();
        printGrid();
    }

    private static void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the coordinates: ");
            int row;
            int col;
            try {
                row = Integer.parseInt(scanner.next());
                col = Integer.parseInt(scanner.next());

                if (row < 1 || row > 3 || col < 1 || col > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
                if (grid[row - 1][col - 1] != '_') {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }
                grid[row - 1][col - 1] = currentUser;
                currentUser = (currentUser == 'X') ? 'O' : 'X';
                break;
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    private static void checkGrid() {
        boolean hasXRow = hasRowFor('X');
        boolean hasORow = hasRowFor('O');

        int numFreeCells = numberOf('_');
        int numX = numberOf('X');
        int numO = numberOf('O');

        if ((hasXRow && hasORow) || (Math.abs(numX - numO) >= 2)) {
            System.out.println("Impossible");
            return;
        }

        if (!hasXRow && !hasORow) {
            if (numFreeCells == 0) {
                System.out.println("Draw");
            } else {
                System.out.println("Game not finished");
            }
        } else if (!hasXRow){
            System.out.println("O wins");
        } else {
            System.out.println("X wins");
        }
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

    private static boolean hasRowFor(char x) {

        boolean fullDiagMain = true;
        boolean fullDiagSecond = true;

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            boolean fullRow = true;
            boolean fullCol = true;

            for (int j = 0; j < 3; j++) {
                if (grid[i][j] != x) {
                    fullRow = false;
                    break;
                }
            }

            for (int j = 0; j < 3; j++) {
                if (grid[j][i] != x) {
                    fullCol = false;
                    break;
                }
            }

            if (fullRow || fullCol) return true;
        }

        // Check main diagonal
        for (int i = 0; i < 3; i++) {
            if(grid[i][i] != x) {
                fullDiagMain = false;
                break;
            }
        }
        if (fullDiagMain) return true;

        // Check second main diagonal
        for (int i = 0; i < 3; i++) {
            if(grid[i][2 - i] != x) {
                fullDiagSecond = false;
                break;
            }
        }
        if (fullDiagSecond) return true;

        return false;
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
