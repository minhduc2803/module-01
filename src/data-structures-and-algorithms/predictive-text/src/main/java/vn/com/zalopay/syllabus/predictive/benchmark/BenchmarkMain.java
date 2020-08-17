package vn.com.zalopay.syllabus.predictive.benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkMain {
    public static void main(String[] args) throws RunnerException {
        Options opt =
                new OptionsBuilder()
                        .include(DictionaryBenchmark.class.getSimpleName())
                        .warmupIterations(1)
                        .measurementIterations(5)
                        .forks(1)
                        .build();

        new Runner(opt).run();
    }
}
