package com.evilcorp.benchmark.walk.legacy;

import com.evilcorp.list.FixedLengthArrayList;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Benchmark)
public class FixedArrayListWalkBenchmark {

    @Param({"40000"
//            , "1"
//            , "2"
    })
    private int arrayLength;

    private FixedLengthArrayList<Integer> arrayList;

    @Setup(Level.Iteration)
    public void setup() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        int size = arrayLength;

        arrayList = new FixedLengthArrayList<>(size);
        for (int i = 0; i < size; i++) {
            arrayList.put(i, random.nextInt());
        }
    }

    private static int sum(FixedLengthArrayList<Integer> arrayList) {
        int sum = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            sum += arrayList.get(i);
        }
        return sum;
    }

    //    @Benchmark
    public int sumArrayListIdx(Blackhole box) {
        final FixedLengthArrayList<Integer> arrl = arrayList;
        return sum(arrl);
    }

    @Benchmark
    public void walkArrayListIdx(Blackhole box) {
        final FixedLengthArrayList<Integer> arrl = arrayList;
        for (int i = 0; i < arrl.size(); i++) {
            final Integer __ = arrl.get(i);
            box.consume(__);
        }
    }

    @Benchmark
    public void walkArrayListObject(Blackhole box) {
        for (int i = 0; i < arrayList.size(); i++) {
            final Object __ = arrayList.get(i);
            box.consume(__);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(FixedArrayListWalkBenchmark.class.getCanonicalName())
            .jvmArgs(
//                        "-XX:+PrintCompilation"
//                         "-Xint"
//                        "-XX:TieredStopAtLevel=4"
            )
            .build();

        new Runner(opt).run();
    }

}
