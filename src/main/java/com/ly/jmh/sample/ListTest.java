package com.ly.jmh.sample;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author whq46936
 * @version Id: ListTest, v 0.1 2020/7/21 08:37 whq46936 Exp $
 */
public class ListTest {
    public static void main(String[] args) {
        linkedTest();
        arrayTest();
    }

    private static void arrayTest() {
        ArrayList<String> array = new ArrayList<>(1);
        long              start = System.currentTimeMillis();
//        for (int i = 0; i < 1000_0000; i++) {
//            array.add(0, "1");
//        }
        for (int i = 0; i < 103808850; i++) {
            array.add("1"+i);
        }
        long end = System.currentTimeMillis();
        System.out.println("arrayList:" + (end - start) + ",size:" + array.size());
    }

    private static void linkedTest() {
        LinkedList<String> linked = new LinkedList<>();
        long               start  = System.currentTimeMillis();
//        for (int i = 0; i < 1000_0000; i++) {
//            linked.add("1");
//        }
        for (int i = 0; i < 103808850; i++) {
            linked.add("1"+i);
        }
        long end = System.currentTimeMillis();
        System.out.println("linkedList:" + (end - start) + ",size:" + linked.size());
    }
}