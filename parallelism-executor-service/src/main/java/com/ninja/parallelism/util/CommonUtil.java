package com.ninja.parallelism.util;

/**
 * @author Subhrajit Sadhukhan
 */
public class CommonUtil {
    public static void delay(int delayMillis){
        try {
            Thread.sleep(delayMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
