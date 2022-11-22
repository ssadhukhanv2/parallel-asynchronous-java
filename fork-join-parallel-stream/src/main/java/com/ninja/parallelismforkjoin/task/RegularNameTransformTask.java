package com.ninja.parallelismforkjoin.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ninja.parallelismforkjoin.util.CommonUtil.delay;

/**
 * @author Subhrajit Sadhukhan
 */
public class RegularNameTransformTask {
    private List<String> nameList;

    public RegularNameTransformTask(List<String> nameList) {
        this.nameList = nameList;
    }

    public List<String> transformAndGetResult() {
        return nameList.stream().map(name -> convertToUpperCaseAndAddLength(name)).collect(Collectors.toList());
    }

    public String convertToUpperCaseAndAddLength(String name) {
        delay(1000);
        return name.toUpperCase() + "-" + name.length();
    }
}
