package org.example;

import java.util.*;
import java.util.concurrent.locks.*;

public class Exploration {
    private final SharedMemory mem;
    private final ExplorationMap map;
    private final ReadWriteLock rlock = new ReentrantReadWriteLock();
    private final int matrixDimension;
    private final List<Robot> robots;
    private final Lock lock = new ReentrantLock();
    public Exploration(int n) {
        this.mem = new SharedMemory(n);
        this.map = new ExplorationMap(n);
        this.robots = new ArrayList<>();
        this.matrixDimension = n;
    }

    public int getMatrixDimension() {
        return this.matrixDimension;
    }

    public void start() {
        for (Robot robot : robots) {
//            start a new Thread representing the robot;
            Thread thread = new Thread(robot);
            thread.start();
        }
    }

    public void addRobot(Robot robot) {
        if(robot != null) {
            robot.setExplore(this);
            robots.add(robot);
        }
    }

    public ExplorationMap getMap() {
        return this.map;
    }

    public SharedMemory getMem() {
        return mem;
    }

    public boolean isVisited(int row, int col) {
        rlock.readLock().lock();
        try {
            return map.isVisited(row, col);
        } finally {
            rlock.readLock().unlock();
        }
    }

    public static void main(String args[]) {
        int n = 10;
        var explore = new Exploration(n);
        explore.addRobot(new Robot("Wall-E"));
        explore.addRobot(new Robot("R2D2"));
        explore.addRobot(new Robot("Optimus Prime"));
        explore.start();
    }
}
