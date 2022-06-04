package com.ly.jmh.sample;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * 数组优化测试
 *
 * @author whq46936
 * @date 2020/07/28
 */
// 测试完成时间
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
// 预热次数和时间
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
// 测试次数和时间
@Measurement(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
// fork 1 个JVM进程
@Fork(1)
@State(Scope.Thread)
public class ArrayOptimizeTest {

    // 测试循环次数
    private static final int           maxSize       = 1000;
    // 操作次数
    private static final int           operationSize = 100;

    private static ArrayList<Integer>  arrayList;
    private static LinkedList<Integer> linkedList;

    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        // 要导入的测试类
        Options opt = new OptionsBuilder().include(ArrayOptimizeTest.class.getSimpleName())
                                          .build();
        // 执行测试
        new Runner(opt).run();
    }

    @Setup
    public void init() {
        // 启动执行事件
        arrayList  = new ArrayList<Integer>();
        linkedList = new LinkedList<Integer>();
        for (int i = 0; i < maxSize; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
    }

    @Benchmark
    public void addArrayByFirst(Blackhole blackhole) {
        for (int i = 0; i < +operationSize; i++) {
            arrayList.add(i, i);
        }
        // 为了避免 JIT 忽略未被使用的结果计算
        blackhole.consume(arrayList);
    }

    @Benchmark
    public void addLinkedByFirst(Blackhole blackhole) {
        for (int i = 0; i < +operationSize; i++) {
            linkedList.add(i, i);
        }
        // 为了避免 JIT 忽略未被使用的结果计算
        blackhole.consume(linkedList);
    }
}