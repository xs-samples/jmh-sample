package com.ly.jmh.sample;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

/**
 * @author whq46936
 * @version Id: LinkedListIterationBenchMark, v 0.1 2020/7/20 09:57 whq46936 Exp $
 */
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(1)
@Warmup(iterations = 2)
@Measurement(iterations = 2)
@Threads(Threads.MAX)
@BenchmarkMode(Mode.Throughput)
public class LinkedListIterationBenchMark {

    private static final int SIZE = 10000;

    private List<String>     list = new LinkedList<>();

    @Setup
    public void setup() {
        for (int i = 0; i < SIZE; i++) {
            list.add(String.valueOf(i));
        }
    }

    @Benchmark
    @OperationsPerInvocation(100)
    public void forIndexIterate(Blackhole bh) {
        for (int i = 0; i < list.size(); i++) {
            bh.consume(list.get(i));
        }
    }

    @Benchmark
    @OperationsPerInvocation(100)
    public void forEachIterate(Blackhole bh) {
        for (String s : list) {
            bh.consume(s);
        }
    }
}