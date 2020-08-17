package vn.com.zalopay.syllabus.predictive.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class DictionaryTest {

  @Test
  void shouldReturnIsContain() {
    //        GIVEN
    Set<String> words = new HashSet<>();
    words.add("test");
    Dictionary dictionary = new SampleDictionary(words);

    //        WHEN
    boolean isContain = dictionary.contains("test");

    //        THEN
    Assertions.assertTrue(isContain);
  }
}
