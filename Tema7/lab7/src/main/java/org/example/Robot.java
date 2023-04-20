package org.example;

import java.util.*;
import java.util.concurrent.locks.*;

public class Robot implements Runnable {
    private String name;
    private boolean running;
    Exploration explore;
    private int row;
    private int numberOfTokens;
    private boolean paused = true;
    private int col;
    private final Lock lock = new ReentrantLock();

    public String getName() {
        return name;
    }

    public void setExplore(Exploration explore) {
        this.explore = explore;
    }

    public Robot(String name) {
        this.name = name;
        this.numberOfTokens = 0;
    }

    public int getNumberOfTokens() {
        return this.numberOfTokens;
    }

    public void stop() {
        this.running = false;
    }

    public boolean isRunning() {
        return this.running;
    }

    public void pause() {
        paused = true;
    }

    public synchronized void resume() {
        paused = false;
        notify();
    }

    public List<Token> extractTokens(int n) {
        List<Token> extractedTokens;
        lock.lock();
        try {
            extractedTokens = explore.getMem().extractTokens(n);
            numberOfTokens += extractedTokens.size();
        } finally {
            lock.unlock();
        }
        return extractedTokens;
    }

    @Override
    public void run() {
        Random rand = new Random();
        lock.lock();
        try {
            row = rand.nextInt(explore.getMatrixDimension());
            col = rand.nextInt(explore.getMatrixDimension());
            explore.getMap().visit(row, col, this);
            running = true;
        } finally {
            lock.unlock();
        }
        int[][] directions = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
        boolean allVisited;
        while (running) {
            // pick a new cell to explore
            synchronized (this) {
                while (paused && running) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            allVisited = true;
            for(int i = 0; i < directions.length; i++) {
                int newRow = row + directions[i][0];
                int newCol = col + directions[i][1];
                if (newRow >= 0 && newRow < explore.getMatrixDimension() && newCol >= 0 && newCol < explore.getMatrixDimension()
                        && !explore.isVisited(newRow, newCol)) {
                    allVisited = false;
                    lock.lock();
                    try {
                        row = newRow;
                        col = newCol;
                        explore.getMap().visit(row, col, this);
//                        System.out.printf("%s visited cell (%d,%d)%n", name, row, col);
                    } finally {
                        lock.unlock();
                    }
                    break;
                }
            }
            if(allVisited) {
                running = false;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}