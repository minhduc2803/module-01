package vn.com.zalopay.syllabus.predictive.benchmark;

import org.openjdk.jmh.annotations.*;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import vn.com.zalopay.syllabus.predictive.service.Dictionary;
import vn.com.zalopay.syllabus.predictive.service.SampleDictionary;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class DictionaryBenchmark {

  private Dictionary dictionary;

  @Setup(Level.Trial)
  public void setupDictionary() {
    Set<String> dataset = new HashSet<>();
    dataset.add("word");
    dictionary = new SampleDictionary(dataset);
  }


  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.MICROSECONDS)
  @Measurement(iterations = 5)
  @Warmup(iterations = 5)
  public void contain() {
    dictionary.contains("word");
  }
}
