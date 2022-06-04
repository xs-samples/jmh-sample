package com.ly.jmh.sample;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

/**
 * 🏳️🏳️🏳️ List Becnchmark exp
 * @author whq46936
 * @version Id: ListBenchmark, v 0.1 2020/7/19 21:00 whq46936 Exp $
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 1, time = 1)
@Fork(1)
public class ListBenchmark {

    @State(Scope.Thread)
    public static class ArrayClass {
        public boolean           flag = true;
        public ArrayList<String> list;

        @Setup(Level.Trial)
        public void setup() {
            list = new ArrayList<>();
            System.out.println("➡️ array setup.");
        }

        @TearDown
        public void tearDown() {
            System.out.println("✅ array tearDown.size:" + list.size());
        }
    }

    @State(Scope.Thread)
    public static class LinkedClass {
        public boolean            flag = true;
        public LinkedList<String> list;

        @Setup(Level.Trial)
        public void setup() {
            list = new LinkedList<>();
            System.out.println("➡️ linked setup.");
        }

        @TearDown
        public void tearDown() {
            System.out.println("✅ linked tearDown.size:" + list.size());
        }
    }

    @Benchmark
    public List<String> testArray(ArrayClass arrayClass) {
        arrayClass.list.add("1");
        if (arrayClass.flag) {
            arrayClass.flag = false;
            System.out.println("🚹 array add.");
        }
        return arrayClass.list;
    }

    @Benchmark
    public List<String> testLinked(LinkedClass linkedClass) {
        linkedClass.list.add("1");
        if (linkedClass.flag) {
            linkedClass.flag = false;
            System.out.println("🚺 array add.");
        }

        return linkedClass.list;
    }
}