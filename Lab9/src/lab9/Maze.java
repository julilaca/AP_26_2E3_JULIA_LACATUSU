package lab9;

import java.util.Random;

public class Maze {
    private int rows, cols;
    private Cell[][] cells;
    private String[][] board;
    private boolean finished = false;
    private Random random = new Random();

    Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = new Cell[rows][cols];
        board = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell(i, j);
                board[i][j] = ".";
            }
        }
    }

    public synchronized Position randomFreePosition() {
        while (true) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);

            if (board[row][col].equals(".")) {
                return new Position(row, col);
            }
        }
    }

    public synchronized void place(String symbol, Position p) {
        board[p.row][p.col] = symbol;
    }

    public synchronized boolean move(String symbol, Position p) {
        if (finished) return false;

        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int[] d = dir[random.nextInt(dir.length)];

        int newRow = p.row + d[0];
        int newCol = p.col + d[1];

        if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
            return false;
        }

        if (symbol.equals("B") && board[newRow][newCol].startsWith("R")) {
            finished = true;
            System.out.println("A robot caught the bunny");
            return false;
        }

        if (symbol.startsWith("R") && board[newRow][newCol].equals("B")) {
            finished = true;
            System.out.println(symbol + " caught the bunny");
            return false;
        }

        if (!board[newRow][newCol].equals(".")) {
            return false;
        }

        board[p.row][p.col] = ".";
        p.row = newRow;
        p.col = newCol;
        board[p.row][p.col] = symbol;

        if (symbol.equals("B") && p.row == rows - 1 && p.col == cols - 1) {
            finished = true;
            System.out.println("bunny found the exit");
        }

        printMaze();
        return true;
    }

    public synchronized boolean isFinished() {
        return finished;
    }
    public synchronized boolean systematicMove(String symbol, Position p, int direction) {
        int[][] dir = {
                {-1, 0},
                {0, 1},
                {1, 0},
                {0, -1}
        };

        for (int k = 0; k < 4; k++) {
            int index = (direction + k) % 4;
            int newRow = p.row + dir[index][0];
            int newCol = p.col + dir[index][1];

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                continue;
            }

            if (board[newRow][newCol].equals("B")) {
                finished = true;
                System.out.println(symbol + " caught the bunny!");
                return true;
            }

            if (board[newRow][newCol].equals(".")) {
                board[p.row][p.col] = ".";
                p.row = newRow;
                p.col = newCol;
                board[p.row][p.col] = symbol;
                return true;
            }
        }

        return false;
    }
    public synchronized void printMaze() {
        System.out.println();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}