package com.ninja.parallelism.util;

/**
 * @author Subhrajit Sadhukhan
 */
public class LoggerUtil {
    public static void log(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "] - " + message);
    }
}
