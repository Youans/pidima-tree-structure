package com.pidima.trie;

public class Demo {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("cat");
        trie.insert("car");
        trie.insert("care");

        System.out.println("lookup(cat): " + trie.lookup("cat"));
        System.out.println("lookup(care): " + trie.lookup("care"));
        System.out.println("lookup(can): " + trie.lookup("can"));

        System.out.println("update(cat -> dog): " + trie.update("cat", "dog"));
        System.out.println("lookup(cat): " + trie.lookup("cat"));
        System.out.println("lookup(dog): " + trie.lookup("dog"));

        System.out.println("delete(car): " + trie.delete("car"));
        System.out.println("lookup(car): " + trie.lookup("car"));
        System.out.println("lookup(care): " + trie.lookup("care"));
    }
}
