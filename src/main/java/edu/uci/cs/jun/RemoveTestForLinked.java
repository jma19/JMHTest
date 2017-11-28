//package edu.uci.cs.jun; /**
// * Created by zhongzhuojian on 20/11/2017.
// */
//
//import org.openjdk.jmh.annotations.*;
//import org.openjdk.jmh.infra.Blackhole;
//import org.openjdk.jmh.results.format.ResultFormatType;
//import org.openjdk.jmh.runner.Runner;
//import org.openjdk.jmh.runner.RunnerException;
//import org.openjdk.jmh.runner.options.Options;
//import org.openjdk.jmh.runner.options.OptionsBuilder;
//
//import java.util.Deque;
//import java.util.LinkedList;
//import java.util.concurrent.TimeUnit;
//
//
//@State(Scope.Thread)
//public class RemoveTestForLinked {
//
//    @Param({"4", "40", "400", "4000", "20000", "100000", "500000", "1000000", "10000000", "100000000"})
//    int size;
//    Deque<Integer> linkedList;
//
//    @Setup(Level.Iteration)
//    public void setup() {
//        linkedList = new LinkedList<>();
//        for (int i = 0; i < size; i++) {
//            linkedList.add(i);
//        }
//    }
//
//
//    @Benchmark
//    @BenchmarkMode(Mode.AverageTime)
//    @OutputTimeUnit(TimeUnit.MICROSECONDS)
//    public void removeLinkedList(Blackhole bh) {
//        while (!linkedList.isEmpty()) {
//            bh.consume(linkedList.poll());
//            //System.out.println("sdf");
//        }
//    }
//
//
//    public static void main(String[] args) throws RunnerException {
//        Options opt = new OptionsBuilder()
//                .include(RemoveTestForLinked.class.getSimpleName())
//                .resultFormat(ResultFormatType.CSV)
//                .result("linkedlist-remove-result.csv")
//                .warmupIterations(5)
//                .measurementIterations(5)
//                .forks(1)
//                .build();
//
//        new Runner(opt).run();
//    }
//
//}
