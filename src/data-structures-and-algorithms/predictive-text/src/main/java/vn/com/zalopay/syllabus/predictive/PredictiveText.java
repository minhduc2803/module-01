package vn.com.zalopay.syllabus.predictive;

import vn.com.zalopay.syllabus.predictive.service.Dictionary;
import vn.com.zalopay.syllabus.predictive.service.SampleDictionary;
import vn.com.zalopay.syllabus.predictive.service.TrieDictionary;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class PredictiveText {
  public static void main(String[] args) {

    TrieDictionary dictionary = new TrieDictionary();
    dictionary.insert("word");
    dictionary.insert("work");
    dictionary.insert("why");
    dictionary.insert("what");
    dictionary.insert("when");

    List a= dictionary.autocomplete("wh");
    for (int i = 0; i < a.size(); i++) {
      System.out.println(a.get(i));
    }
  }
}
