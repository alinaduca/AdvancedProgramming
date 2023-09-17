package org.example;

public class Board {
    private int size;
    private char[][] board;
    private int currentPlayer;

    public Board(int size) {
        this.size = size;
        board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
            }
        }
        currentPlayer = 0;
    }

    public void printCells ()
    {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public void setCell(int row, int col, char value) {
        board[row][col] = value;
    }

    public char[][] getCells ()
    {
        return board;
    }

    public boolean isEmpty(int row, int col) {
        return board[row][col] == '-';
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        for (int i = 0; i < size; i++) {
            sb.append(i);
        }
        sb.append("\n");
        for (int i = 0; i < size; i++) {
            sb.append(i);
            for (int j = 0; j < size; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String toStringForResponse() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        for (int i = 0; i < size; i++) {
            sb.append(i);
        }
        sb.append("@");
        for (int i = 0; i < size; i++) {
            sb.append(i);
            for (int j = 0; j < size; j++) {
                sb.append(board[i][j]);
            }
            sb.append("@");
        }
        return sb.toString();
    }
}
