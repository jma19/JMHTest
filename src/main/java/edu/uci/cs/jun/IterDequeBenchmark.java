package edu.uci.cs.jun; /**
 * Created by zhongzhuojian on 20/11/2017.
 */

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;


@State(Scope.Thread)
public class IterDequeBenchmark {

    @Param({"4", "40", "400", "4000", "20000", "100000"})
    int size;
    Deque<Integer> arrayDeque;

    @Setup(Level.Iteration)
    public void setup() {
        arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < size; i++) {
            arrayDeque.add(i);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void removeArrayDeque(Blackhole bh) {
        Iterator<Integer> it = arrayDeque.iterator();
        while (it.hasNext()) {
            bh.consume(it.next());
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(IterDequeBenchmark.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .result("arraydeque-iterate-result.csv")
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
