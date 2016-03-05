/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lleps.jsamp.server;

import java.time.Duration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that represents a task that runs in SA:MP thread over time a specific (maybe infinite) number of times, called cycles.
 *
 * @author spell
 */
public class Timer {
    private static List<Timer> timers = new LinkedList<>();

    private static Runnable timerExecutorFunction = () -> {
        Iterator<Timer> iterator = timers.iterator();
        while (iterator.hasNext()) {
            Timer timer = iterator.next();
            if (timer.isTimeToCall()) {
                timer.call();
                if (!timer.isRunning()) {
                    iterator.remove();
                }
            }
        }
    };

    static {
        new Thread(() -> {
            try {
                Thread.sleep(20);
            } catch (InterruptedException ignored) {
            }

            SAMPServer.runLater(timerExecutorFunction);
        }).start();
    }

    public static final int INFINITE_CYCLES = -1;

    private Runnable task;
    private long maxCycles;
    private Duration cycleDelay;

    private volatile long lastCall;
    private long cycleCount;
    private boolean running;

    public Timer(Runnable task) {
        this(task, Duration.ofSeconds(1)); // Default magic-value delay. too bad, but uh.
    }

    public Timer(Runnable task, Duration cycleDelay) {
        this(task, cycleDelay, INFINITE_CYCLES);
    }

    public Timer(Runnable task, Duration cycleDelayInMS, long maxCycles) {
        this.task = task;
        this.cycleDelay = cycleDelayInMS;
        this.maxCycles = maxCycles;
    }

    /**
     * Starts the timer. Note that cycle count remains intact.
     */
    public void start() {
        running = true;
        timers.add(this);
    }

    /**
     * Stops the timer.
     */
    public void stop() {
        running = false;
    }

    /**
     * Restart the timer. Cycle count is set to 0, and timer is started again.
     */
    public void restart() {
        cycleCount = 0;
        start();
    }

    private boolean isTimeToCall() {
        return (System.currentTimeMillis() - lastCall) >= cycleDelay.toMillis();
    }

    private void call() {
        if (++cycleCount <= maxCycles || maxCycles == INFINITE_CYCLES) {
            task.run();
            lastCall = System.currentTimeMillis();
        } else {
            running = false;
        }
    }

    /**
     * Returns true if this thread is running.
     * @return true if running, false otherwise.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Gets the cycle count.
     * @return Cycle count.
     */
    public long getCycleCount() {
        return cycleCount;
    }

    /**
     * Get timer max cycles (how much times the timer will perform the task before if stops).
     * @return timer max cycles, or INFINITE_CYCLES by default.
     */
    public long getMaxCycles() {
        return maxCycles;
    }

    /**
     * Set timer max cycles (how much times the timer will perform the task before if stops).
     * @param maxCycles timer max cycles, or INFINITE_CYCLES.
     */
    public void setMaxCycles(long maxCycles) {
        this.maxCycles = maxCycles;
    }

    /**
     * Get the delay between cycles.
     * @return The delay between timer cycles (in MS)
     */
    public Duration getCycleDelay() {
        return cycleDelay;
    }

    /**
     * Set delay between timer cycles.
     * @param cycleDelay delay.
     */
    public void setCycleDelay(Duration cycleDelay) {
        this.cycleDelay = cycleDelay;
    }
}
