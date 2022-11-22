package com.ninja.parallelismforkjoin.parallelStreams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Subhrajit Sadhukhan
 */
public class ListTransformer {
    public List<String> toLowerCase(List<String> stringList, boolean isParallel) {
        Stream<String> stringStream = stringList.stream();
        if (isParallel)
            stringStream.parallel();
        return stringStream.map(this::toLowerCaseWithDelay).collect(Collectors.toList());
    }

    private String toLowerCaseWithDelay(String str) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return str.toLowerCase();
    }

}
