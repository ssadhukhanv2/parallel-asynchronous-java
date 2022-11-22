package com.ninja.parallelismforkjoin.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

import static com.ninja.parallelismforkjoin.util.CommonUtil.delay;

/**
 * @author Subhrajit Sadhukhan
 */
public class ForkJoinNameTransformTask extends RecursiveTask<List<String>> {
    private List<String> nameList;

    public ForkJoinNameTransformTask(List<String> nameList) {
        this.nameList = nameList;
    }

    public List<String> transformAndGetResult() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinNameTransformTask forkJoinNameTransformTask = new ForkJoinNameTransformTask(nameList);
        List<String> resultList=forkJoinPool.invoke(forkJoinNameTransformTask);
        return resultList;
    }

    @Override
    protected List<String> compute() {
        if (nameList.size() <= 1) {
            //return nameList.stream().map(name -> convertToUpperCaseAndAddLength(name)).collect(Collectors.toList());
            List<String> resultList = new ArrayList<>();
            nameList.forEach(name-> resultList.add(convertToUpperCaseAndAddLength(name)));
            return resultList;
        }
        int mid = nameList.size() / 2;
        List<String> leftList=nameList.subList(0, mid);
        ForkJoinTask<List<String>> leftPartForkJoinTask = new ForkJoinNameTransformTask(leftList).fork(); //The computation of the left part of the list has been forked
        nameList = nameList.subList(mid, nameList.size()); // The right part of the nameList is stored in the nameList itself
        List<String> rightResult = compute();
        List<String> leftResult = leftPartForkJoinTask.join();
        leftResult.addAll(rightResult);
        return leftResult;
    }

    public String convertToUpperCaseAndAddLength(String name) {
        delay(1000);
        return name.toUpperCase() + "-" + name.length();
    }

}
