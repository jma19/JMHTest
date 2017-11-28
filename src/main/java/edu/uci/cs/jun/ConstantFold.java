package edu.uci.cs.jun;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ConstantFold {

    private double x = Math.PI;

    private final double wrongX = Math.PI;

    // simply return the value, this is a baseline
    @Benchmark
    public double baseline() {
        return Math.PI;
    }

    // This is wrong: the source is predictable, and computation is foldable.
    @Benchmark
    public double measureWrong() {
        return Math.log(100 * wrongX);
    }

    // This is correct: the source is not predictable.
    @Benchmark
    public double measureRight() {
        return Math.log(x);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ConstantFold.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .result("ConstantFolding.csv")
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}