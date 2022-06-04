package com.ly.jmh.sample;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

/**
 * 插入在beginning100基准
 *
 * @author whq46936
 * @date 2020/07/22
 */
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@Fork(1)
public class InsertAtBeginning100Benchmark {

    private static final int INITIAL_SIZE = 100;

    @State(Scope.Thread)
    public static class WastedCapacityArrayListState {

        public List<String> list;

        @Setup(Level.Invocation)
        public void doSetup() {
            list = new ArrayList<>(INITIAL_SIZE);
            for (int i = 0; i < INITIAL_SIZE; ++i) {
                list.add("string");
            }
        }
    }

    @State(Scope.Thread)
    public static class ReservedCapacityArrayListState {

        public List<String> list;

        @Setup(Level.Trial)
        public void doSetup() {
            list = new ArrayList<>(2 * INITIAL_SIZE);
            for (int i = 0; i < INITIAL_SIZE; ++i) {
                list.add("string");
            }
        }
    }

    @State(Scope.Thread)
    public static class LinkedListState {

        public List<String> list;

        @Setup(Level.Trial)
        public void doSetup() {
            list = new LinkedList<>();
            for (int i = 0; i < INITIAL_SIZE; ++i) {
                list.add("string");
            }
        }
    }

    @Benchmark
    public List<String> arrayListInsertAtBeginningWithoutInitialCapacity(WastedCapacityArrayListState state) {
        state.list.add(0, "string");
        return state.list;
    }

    @Benchmark
    public List<String> arrayListInsertAtBeginningWithInitialCapacity(ReservedCapacityArrayListState state) {
        state.list.add(0, "string");
        return state.list;
    }

    @Benchmark
    public List<String> linkedListInsertAtBeginning(LinkedListState state) {
        state.list.add(0, "string");
        return state.list;
    }
}