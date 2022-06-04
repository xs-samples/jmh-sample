//package com.ly.jmh.sample;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.concurrent.TimeUnit;
//
//import org.openjdk.jmh.annotations.Benchmark;
//import org.openjdk.jmh.annotations.BenchmarkMode;
//import org.openjdk.jmh.annotations.Fork;
//import org.openjdk.jmh.annotations.Measurement;
//import org.openjdk.jmh.annotations.Mode;
//import org.openjdk.jmh.annotations.OutputTimeUnit;
//import org.openjdk.jmh.annotations.Scope;
//import org.openjdk.jmh.annotations.State;
//import org.openjdk.jmh.annotations.TearDown;
//import org.openjdk.jmh.annotations.Warmup;
//import org.openjdk.jmh.infra.Blackhole;
//
///**
// * @author whq46936
// * @version Id: ListBenchmark, v 0.1 2020/7/19 21:00 whq46936 Exp $
// */
//@BenchmarkMode(Mode.Throughput)
//@State(Scope.Thread)
//@OutputTimeUnit(TimeUnit.SECONDS)
//@Warmup(iterations = 1, time = 1)
//@Measurement(iterations = 1, time = 1)
//@Fork(1)
//public class ListBenchmark1 {
//    public ArrayList<String> array  = new ArrayList<>();
//    public LinkedList<String> linked = new LinkedList<>();
//
//    @TearDown
//    public void tearDown() {
//        System.out.println("arrayList size:" + array.size());
//        System.out.println("linkedList size:" + linked.size());
//    }
//
//    @Benchmark
//    public void testArray(Blackhole bh) {
//        array.add("1");
//        bh.consume(array);
//    }
//
//    @Benchmark
//    public void testLinked(Blackhole bh) {
//        linked.add("1");
//        bh.consume(linked);
//    }
//}