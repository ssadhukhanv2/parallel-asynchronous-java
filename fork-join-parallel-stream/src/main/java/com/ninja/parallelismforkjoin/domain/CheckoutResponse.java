package com.ninja.parallelismforkjoin.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Subhrajit Sadhukhan
 */
@Data
public class CheckoutResponse {
    CheckoutStatus checkoutStatus;
    List<CartItem> itemsWithExpiredPrice;

    public CheckoutResponse(CheckoutStatus checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    public CheckoutResponse(CheckoutStatus checkoutStatus, List<CartItem> itemsWithExpiredPrice) {
        this.checkoutStatus = checkoutStatus;
        this.itemsWithExpiredPrice = itemsWithExpiredPrice;
    }
}
