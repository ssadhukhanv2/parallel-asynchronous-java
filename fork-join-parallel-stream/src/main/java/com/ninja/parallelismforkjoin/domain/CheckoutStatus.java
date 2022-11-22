package com.ninja.parallelismforkjoin.domain;

/**
 * @author Subhrajit Sadhukhan
 */
public enum CheckoutStatus {
    SUCCESS("Success"), FAILURE("Failure");
    private final String statusMeaning;

    private CheckoutStatus(String statusMeaning) {
        this.statusMeaning = statusMeaning;
    }

    private String getStatusMeaning() {
        return this.statusMeaning;
    }


}
