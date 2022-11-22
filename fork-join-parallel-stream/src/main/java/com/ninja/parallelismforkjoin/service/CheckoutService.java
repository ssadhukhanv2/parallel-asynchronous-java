package com.ninja.parallelismforkjoin.service;

import com.ninja.parallelismforkjoin.domain.Cart;
import com.ninja.parallelismforkjoin.domain.CartItem;
import com.ninja.parallelismforkjoin.domain.CheckoutResponse;
import com.ninja.parallelismforkjoin.domain.CheckoutStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Subhrajit Sadhukhan
 */
public class CheckoutService {
    private PriceValidatorService priceValidatorService;

    public CheckoutService(PriceValidatorService priceValidatorService) {
        this.priceValidatorService = priceValidatorService;
    }


    public CheckoutResponse checkout(Cart cart) {
        List<CartItem> itemsWithExpiredPrice = cart.getCartItemList()
                .parallelStream()
                .map(cartItem -> {
                    cartItem.setExpired(priceValidatorService.isPriceExpired(cartItem));
                    return cartItem;
                }).filter(CartItem::isExpired)
                .collect(Collectors.toList());
        if (itemsWithExpiredPrice.size() > 0) {
            return new CheckoutResponse(CheckoutStatus.FAILURE, itemsWithExpiredPrice);
        }
        return new CheckoutResponse(CheckoutStatus.SUCCESS);
    }
}
