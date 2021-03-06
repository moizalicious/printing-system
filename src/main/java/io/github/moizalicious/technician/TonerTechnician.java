package io.github.moizalicious.technician;

import io.github.moizalicious.printer.LaserPrinter;
import io.github.moizalicious.sleeper.ThreadSleeper;

/**
 * Thread class to represent a Toner Technician.
 */
public class TonerTechnician extends Thread {

    private String name;
    private LaserPrinter laserPrinter;
    private volatile boolean running;

    public TonerTechnician(String name, LaserPrinter laserPrinter, ThreadGroup threadGroup) {
        super(threadGroup, name);
        this.name = name;
        this.laserPrinter = laserPrinter;
        this.running = true;
    }

    /**
     * Method executed when the thread is started.
     */
    @Override
    public void run() {
        int c = 0;
        while (running && c < 3) {
            laserPrinter.replaceTonerCartridge();
            c++;
            if (c != 2) {
                ThreadSleeper.sleepRandom(1000, 4000);
            }
        }
        System.out.println(name + " has completed it's round(s) of toner replacements\n");
    }

    /**
     * Method to safely stop running the thread.
     */
    public void terminate() {
        running = false;
    }

}
