package com.liulu.java8;

import com.google.common.collect.Collections2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @Copyright (c) by HomeFax.
 * @All right reserved.
 * @Create Date: 2018/10/11 14:34
 * @Create Author: nevermore
 * @File Name: Demo
 * @Last version: 3.8.0
 */
public class Demo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        //List<Integer> list1 = Arrays.asList(1,2,3);
        list.add(1);
        list.add(2);
        list.add(3);
       /* list.add(4);
        list.add(5);*/
        String[] strings = new String[]{"a","b","c"};
        String is = Arrays.stream(strings).reduce("",String::concat);
        System.out.println(is);

        Integer integer = list.stream().map((i) -> i = i * 3)
                .reduce((sum, count) -> sum += count).get();

        System.out.println(integer);

      /*  list.stream().map((i) -> i * 3).forEach(System.out :: println);
        System.out.println(list);*/
       /* list.forEach(item ->{
            if (item>3){
                list.set(list.indexOf(item),item+3);
            }
        });*/
        //System.out.println(list);
        /*List newList = list.stream().map(item -> item>3?item*item:item).collect(Collectors.toList());

        IntSummaryStatistics summaryStatistics = list.stream().mapToInt((x) -> x).summaryStatistics();
        summaryStatistics.getSum();
        summaryStatistics.getAverage();*/

        //System.out.println(list.stream().filter(item -> item>3).count());




        /*Map<Integer,String> map = new HashMap<>();
        map.put(1,"a");
        map.put(2,"b");
        map.put(3,"c");
        map.put(4,"d");
        map.put(5,"e");
        map.forEach((key,value)->{
            System.out.println(key+value);
        });
*/
        /*List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));*/

    }
}
