package org.example;

import java.util.*;
import java.util.concurrent.locks.*;

public class ExplorationMap {
    private final List<List<List<Token>>> matrix;
    private boolean[][] visited;

    public ExplorationMap(int n) {
        matrix = new ArrayList<>(n);
        visited = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            List<List<Token>> row = new ArrayList<>(n);
            for(int k = 0; k < n; k++) {
                visited[i][k] = false;
                List<Token> cell = new ArrayList<>();
                row.add(cell);
            }
            matrix.add(row);
        }
    }
    public synchronized boolean visit(int row, int col, Robot robot) {
        List<Token> cell = matrix.get(row).get(col);
        synchronized (cell) {
            ReadWriteLock lock = new ReentrantReadWriteLock();
            lock.writeLock().lock();
            try {
                if (!visited[row][col]) {
                    cell.addAll(robot.extractTokens(matrix.size()));
                    visited[row][col] = true;
                    return true;
                } else {
                    return false;
                }
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i=0, j=0;
        for (List<List<Token>> row : matrix) {
            for (List<Token> cell : row) {
                if(visited[i][j]) {
                    sb.append("[");
                    for (Token token : cell) {
                        sb.append(token);
                    }
                    sb.append("]");
                }
                else {
                    sb.append(" - ");
                }
                j++;
            }
            sb.append("\n");
            i++;
        }
        return sb.toString();
    }

    public boolean isVisited(int row, int col) {
        return visited[row][col];
    }
}
