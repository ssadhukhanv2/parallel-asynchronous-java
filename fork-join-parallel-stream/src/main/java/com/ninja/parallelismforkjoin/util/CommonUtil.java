package com.ninja.parallelismforkjoin.util;

/**
 * @author Subhrajit Sadhukhan
 */
public class CommonUtil {
    public static void delay(long delayMillis) {
        try {
            Thread.sleep(delayMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
