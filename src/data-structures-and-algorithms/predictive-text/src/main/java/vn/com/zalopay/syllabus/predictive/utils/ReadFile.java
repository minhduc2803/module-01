package vn.com.zalopay.syllabus.predictive.utils;

import org.openjdk.jmh.util.FileUtils;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ReadFile {
    public static Set<String> importData(String filename){
        Set<String> dataset = new HashSet<>();
        File folder = new File(filename);

        System.out.println(folder);
        File[] listOfFiles = folder.listFiles();

        if(listOfFiles == null)
            return null;
        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".xml")) {
                try {
                    Scanner scnr = new Scanner(file);
                    while(scnr.hasNext()){
                        String word = scnr.next();
                        dataset.add(word);
                    }
                }catch(Exception e){}
            }
        }
        return dataset;
    }
}
