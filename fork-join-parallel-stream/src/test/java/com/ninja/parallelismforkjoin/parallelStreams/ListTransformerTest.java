package com.ninja.parallelismforkjoin.parallelStreams;

import com.ninja.parallelismforkjoin.data.DataSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Subhrajit Sadhukhan
 */
class ListTransformerTest {

    @Test
    void toLowerCaseTest() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("sequential");
        ListTransformer listTransformer = new ListTransformer();
        listTransformer.toLowerCase(DataSet.getNames(), false); // doesn't use parallel streams
        stopWatch.stop();
        double timeSequential = stopWatch.getTotalTimeSeconds();


        stopWatch= new StopWatch();
        stopWatch.start("parallel");
        listTransformer.toLowerCase(DataSet.getNames(), true); // uses parallel streams
        stopWatch.stop();
        double timeParallel = stopWatch.getTotalTimeSeconds();
        org.assertj.core.api.Assertions.assertThat(timeSequential).isGreaterThan(timeParallel);
    }
}