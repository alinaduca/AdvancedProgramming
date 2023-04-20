package org.example;

import java.util.*;
import java.util.concurrent.locks.*;

public class Exploration {
    private final SharedMemory mem;
    private final ExplorationMap map;
    private final ReadWriteLock rlock = new ReentrantReadWriteLock();
    private final int matrixDimension;
    private final List<Robot> robots;
    private List<Thread> threads;
    private final Lock lock = new ReentrantLock();
    
    public Exploration(int n) {
        this.mem = new SharedMemory(n);
        this.map = new ExplorationMap(n);
        this.robots = new ArrayList<>();
        this.matrixDimension = n;
        this.threads = new ArrayList<>();
    }

    public int getMatrixDimension() {
        return this.matrixDimension;
    }

    public void start() {
        for (Robot robot : robots) {
            // Start a new Thread representing the robot;
            Thread thread = new Thread(robot);
            threads.add(thread);
            thread.start();
        }
        Timekeeper timekeeper = new Timekeeper(this, 60000); // time limit of 60 seconds
        Thread timekeeperThread = new Thread(timekeeper);
        timekeeperThread.setDaemon(true); // set as daemon thread so it won't prevent JVM from exiting
        timekeeperThread.start();
    }

    public void stop() {
        for (int i = 0; i < threads.size(); i++) {
            robots.get(i).stop();
        }
        getRobotsInfo();
    }

    public void getRobotsInfo() {
        for(Robot robot : robots) {
            System.out.println("Robotul " + robot.getName() + " a extras " + robot.getNumberOfTokens() + " token-uri.");
        }
        System.exit(0);
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

    public void handleCommand(String command) {
        if (command.equals("start all")) {
            for (Robot robot : robots) {
                robot.resume();
            }
        } else if (command.startsWith("start ")) {
            int index = Integer.parseInt(command.substring(6)) - 1;
            if(index >= robots.size()) {
                System.out.println("Invalid command");
                return;
            }
            Robot robot = robots.get(index);
            robot.resume();
        } else if (command.equals("pause all")) {
            for (Robot robot : robots) {
                robot.pause();
            }
            System.out.println("Robots are paused.");
        } else if (command.startsWith("pause all ")) {
            int time = Integer.parseInt(command.substring(10));
            for (Robot robot : robots) {
                robot.pause();
            }
            System.out.println("Robots are paused for " + time + "s.");
            try {
                Thread.sleep(1000L * time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (Robot robot : robots) {
                robot.resume();
            }
        } else if (command.startsWith("pause ")) {
            String[] words1 = command.split("\\s+");
            int index = Integer.parseInt(words1[1]) - 1;
            if(index >= robots.size()) {
                System.out.println("Invalid command");
                return;
            }
            if(words1.length == 3){
                int time = Integer.parseInt(words1[2]);
                Robot robot = robots.get(index);
                robot.pause();
                System.out.println("Robot " + robot.getName() + " is paused for " + time + "s.");
                try {
                    Thread.sleep(1000L * time);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                robot.resume();
            }
            else {
                Robot robot = robots.get(index);
                robot.pause();
                System.out.println("Robot " + robot.getName() + " is paused.");
            }
        } else {
            System.out.println("Invalid command");
        }
    }

    public List<Robot> getRobots() {
        return this.robots;
    }

    public static void main(String args[]) {
        int n = 10;
        var explore = new Exploration(n);
        explore.addRobot(new Robot("Wall-E"));
        explore.addRobot(new Robot("R2D2"));
        explore.addRobot(new Robot("Optimus Prime"));
        explore.start();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();
            if (command.equals("quit")) {
                System.exit(0);
            }
            explore.handleCommand(command);

        }
    }
}
