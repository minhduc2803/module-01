package vn.com.zalopay.syllabus.predictive.service;

import org.openjdk.jmh.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TrieDictionary implements Dictionary{
    class TrieNode {
        char data;
        ArrayList<TrieNode> children;
        TrieNode parent;
        boolean isEnd;

        public TrieNode(char c) {
            data = c;
            children = new ArrayList<TrieNode>();
            isEnd = false;
        }

        public TrieNode getChild(char c) {
            if (children != null)
                for (TrieNode eachChild : children)
                    if (eachChild.data == c)
                        return eachChild;
            return null;
        }

        protected List getWords() {
            List list = new ArrayList();
            if (isEnd) {
                list.add(toString());
            }

            if (children != null) {
                for (int i=0; i< children.size(); i++) {
                    if (children.get(i) != null) {
                        list.addAll(children.get(i).getWords());
                    }
                }
            }
            return list;
        }

        public String toString() {
            if (parent == null) {
                return "";
            } else {
                return parent.toString() + new String(new char[] {data});
            }
        }
    }

    private TrieNode root;

    public TrieDictionary() {
        root = new TrieNode(' ');
    }
    public TrieDictionary(Set<String> dataset){
        root = new TrieNode(' ');
        if(dataset == null)
            return;
        for(String s: dataset){
            insert(s);
        }
    }

    public void insert(String word) {
        if (contains(word) == true)
            return;

        TrieNode current = root;
        TrieNode pre ;
        for (char ch : word.toCharArray()) {
            pre = current;
            TrieNode child = current.getChild(ch);
            if (child != null) {
                current = child;
                child.parent = pre;
            } else {
                current.children.add(new TrieNode(ch));
                current = current.getChild(ch);
                current.parent = pre;
            }
        }
        current.isEnd = true;
    }

    @Override
    public boolean contains(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (current.getChild(ch) == null)
                return false;
            else {
                current = current.getChild(ch);
            }
        }
        if (current.isEnd == true) {
            return true;
        }
        return false;
    }

    public List autocomplete(String prefix) {
        TrieNode lastNode = root;
        for (int i = 0; i< prefix.length(); i++) {
            lastNode = lastNode.getChild(prefix.charAt(i));
            if (lastNode == null)
                return new ArrayList();
        }

        return lastNode.getWords();
    }

}
