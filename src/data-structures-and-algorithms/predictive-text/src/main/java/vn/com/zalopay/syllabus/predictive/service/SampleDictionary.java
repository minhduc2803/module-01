package vn.com.zalopay.syllabus.predictive.service;

import java.util.Set;

public class SampleDictionary implements Dictionary {
  private final Set<String> words;

  public SampleDictionary(Set<String> words) {
    this.words = words;
  }

  @Override
  public boolean contains(String word) {
    return words.contains(word);
  }
}
