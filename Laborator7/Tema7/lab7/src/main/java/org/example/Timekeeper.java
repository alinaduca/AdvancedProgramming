package org.example;

public class Timekeeper implements Runnable {
    private final Exploration exploration;
    private final long timeLimitMs;
    private volatile boolean stopped = false;

    public Timekeeper(Exploration exploration, long timeLimitMs) {
        this.exploration = exploration;
        this.timeLimitMs = timeLimitMs;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (!stopped) {
            long elapsedMs = System.currentTimeMillis() - startTime;
//            System.out.println("Elapsed time: " + elapsedMs + " ms");

            if (elapsedMs >= timeLimitMs) {
                System.out.println("Time limit exceeded, stopping exploration.");
                exploration.stop();
                break;
            }

            try {
                Thread.sleep(1000); // sleep for 1 second
            } catch (InterruptedException e) {
                System.out.println("Timekeeper interrupted, stopping exploration.");
                exploration.stop();
                break;
            }
            boolean allStopped = true;
            for(Robot robot : exploration.getRobots()) {
                if(robot.isRunning()) {
                    allStopped = false;
                    break;
                }
            }
            if(allStopped) {
                stopped = true;
                long elapsed = System.currentTimeMillis() - startTime;
                System.out.println("Final elapsed time: " + elapsed + " ms");
                exploration.getRobotsInfo();
            }
        }
    }
}