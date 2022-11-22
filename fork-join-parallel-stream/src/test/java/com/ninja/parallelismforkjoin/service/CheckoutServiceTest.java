package com.ninja.parallelismforkjoin.service;

import com.ninja.parallelismforkjoin.data.DataSet;
import com.ninja.parallelismforkjoin.domain.Cart;
import com.ninja.parallelismforkjoin.domain.CheckoutResponse;
import com.ninja.parallelismforkjoin.domain.CheckoutStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Subhrajit Sadhukhan
 */
class CheckoutServiceTest {

    PriceValidatorService priceValidatorService = new PriceValidatorService();
    CheckoutService checkoutService = new CheckoutService(priceValidatorService);
    @Test
    void no_Of_cores() {
        //given

        //when
        System.out.println("no of cores :" + Runtime.getRuntime().availableProcessors());

        //then
    }
    @Test
    void checkoutTest_6_items() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Cart cart = DataSet.createCart(6);
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        stopWatch.stop();
        double executionTime = stopWatch.getTotalTimeSeconds();
        Assertions.assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());

    }

    @Test
    void checkoutTest_13_items() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Cart cart = DataSet.createCart(13);
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        stopWatch.stop();
        double executionTime = stopWatch.getTotalTimeSeconds();
        Assertions.assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }
}