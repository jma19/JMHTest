package edu.uci.cs.jun;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by junm5 on 11/27/17.
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StringConcatenation {

    @Param({"10", "50", "100", "200", "250", "300", "350", "400"})
    private int size;

    private final static String app = "abc";


    @Benchmark
    public String testPlusString() {
        String res = "";
        for (int i = 0; i < size; i++) {
            res += app;
        }
        return res;
    }

    @Benchmark
    public String testStringBuilder() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(app);
        }
        return builder.toString();
    }

    @Benchmark
    public String testStringBuffer() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            buffer.append(app);
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StringConcatenation.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .result("stringContactResult.csv")
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(opt).run();
    }


}

