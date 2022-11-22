package com.ninja.parallelismforkjoin.service;

import com.ninja.parallelismforkjoin.domain.CartItem;
import com.ninja.parallelismforkjoin.util.CommonUtil;

/**
 * @author Subhrajit Sadhukhan
 */
public class PriceValidatorService {
    public boolean isPriceExpired(CartItem cartItem) {
        int cartItemItemId = cartItem.getItemId();
        CommonUtil.delay(1000);
        if (cartItemItemId == 7 || cartItemItemId == 9 || cartItemItemId == 11) {
            return true;
        }
        return false;
    }
}
