package com.ninja.parallelismforkjoin.data;


import com.ninja.parallelismforkjoin.domain.Cart;
import com.ninja.parallelismforkjoin.domain.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Subhrajit Sadhukhan
 */
public class DataSet {
    public static List<String> getNames() {
        return List.of("Tom", "Dick", "Harry", "Steven");
    }

    public static Cart createCart(int noOfItems) {
        Cart cart = new Cart();
        List<CartItem> cartItemList = IntStream.rangeClosed(1, noOfItems)
                .mapToObj(i -> new CartItem(i,
                        "Cart Item - ".concat(i + ""),
                        generateRandomPrice(),
                        i,
                        false)).collect(Collectors.toList());
        cart.setCartItemList(cartItemList);
        return cart;
    }

    public static double generateRandomPrice() {
        int min = 50;
        int max = 100;
        return Math.random() * (max - min + 1) + min;
    }
}
