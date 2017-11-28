package edu.uci.cs.jun;
/**
 * Created by zhongzhuojian on 20/11/2017.
 */

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;


@State(Scope.Thread)
public class AddTestForLinkedAndArray {

    @Param({"4", "40", "400", "4000", "20000"})
    private int size;
    private ArrayList<Integer> arrayList;
    private LinkedList<Integer> linkedList;

    @Setup(Level.Iteration)
    public void setup() {
        arrayList = new ArrayList<Integer>();
        linkedList = new LinkedList<Integer>();
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void addToArrayList(Blackhole bh) {
        for (int i = 0; i < size; i++) {
            bh.consume(arrayList.add(i));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void addToLinkedList(Blackhole bh) {
        for (int i = 0; i < size; i++) {
            bh.consume(linkedList.add(i));
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(AddTestForLinkedAndArray.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .result("list-result.csv")
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
