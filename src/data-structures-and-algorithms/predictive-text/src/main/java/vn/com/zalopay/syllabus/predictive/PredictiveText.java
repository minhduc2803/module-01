package vn.com.zalopay.syllabus.predictive;

import vn.com.zalopay.syllabus.predictive.service.Dictionary;
import vn.com.zalopay.syllabus.predictive.service.HashDictionary;
import vn.com.zalopay.syllabus.predictive.service.SampleDictionary;
import vn.com.zalopay.syllabus.predictive.service.TrieDictionary;
import vn.com.zalopay.syllabus.predictive.utils.ReadFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.Scanner;

public class PredictiveText {
  public static void main(String[] args) {
    Set<String> dataset = ReadFile.importData("/home/ubuntu/blogs/");
    TrieDictionary TDict = new TrieDictionary(dataset);
    HashDictionary HDict = new HashDictionary(dataset);

    Scanner myObj = new Scanner(System.in);


    String choose = "0";
    while(!choose.equals("4")){
      System.out.println("1: Check word in the set with Trie");
      System.out.println("2: Check word in the set with Hash Table");
      System.out.println("3: Word suggestion");
      System.out.println("4: Quit");
      choose = myObj.next();

      switch(choose){
        case "1": {
          System.out.println("Enter a word");
          String word = myObj.next();
          if(TDict.contains(word))
            System.out.println("Trie Dictionary has this word");
          else
            System.out.println("Trie Dictionary does not has this word");
          break;
        }
        case "2":{
          System.out.println("Enter a word");
          String word = myObj.next();
          if(HDict.contains(word))
            System.out.println("Hash Dictionary has this word");
          else
            System.out.println("Hash Dictionary does not has this word");
          break;
        }
        case "3": {
          System.out.println("Enter a word");
          String word = myObj.next();
          List a = TDict.autocomplete(word);
          int len = a.size() > 10 ? 10 : a.size();
          for (int i = 0; i < len; i++) {
            System.out.println(a.get(i));
          }
          break;
        }
        default:
          System.out.println("You entered a wrong instruction");
      }
    }
  }


}
