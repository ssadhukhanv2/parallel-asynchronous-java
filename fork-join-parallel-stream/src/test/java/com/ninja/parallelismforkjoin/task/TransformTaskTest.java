package com.ninja.parallelismforkjoin.task;

import com.ninja.parallelismforkjoin.data.DataSet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import static com.ninja.parallelismforkjoin.util.LoggerUtil.log;

import java.util.List;

/**
 * @author Subhrajit Sadhukhan
 */
public class TransformTaskTest {

    @Test
    @DisplayName("Test the Regular/Sequential Name Transformation Task")
    public void testRegularNameTransformTask() {
        RegularNameTransformTask regularNameTransformTask = new RegularNameTransformTask(DataSet.getNames());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<String> resultList = regularNameTransformTask.transformAndGetResult();
        stopWatch.stop();
        log("Regular/Sequential Name Transformation Task: " + stopWatch.getTotalTimeSeconds());
        Assertions.assertThat(resultList).contains("TOM-3", "DICK-4", "HARRY-5", "STEVEN-6");
        Assertions.assertThat(stopWatch.getTotalTimeSeconds()).isLessThan(5);
    }

    @Test
    @DisplayName("Test the Parallel Name Transformation Task using ForkJoin")
    public void testForkJoinNameTransformTask(){
        ForkJoinNameTransformTask forkJoinNameTransformTask=new ForkJoinNameTransformTask(DataSet.getNames());
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        List<String> resultList=forkJoinNameTransformTask.transformAndGetResult();
        stopWatch.stop();
        log("Parallel Name Transformation Task: " + stopWatch.getTotalTimeSeconds());
        Assertions.assertThat(resultList).contains("TOM-3", "DICK-4", "HARRY-5", "STEVEN-6");
        Assertions.assertThat(stopWatch.getTotalTimeSeconds()).isLessThan(2);
    }
}
