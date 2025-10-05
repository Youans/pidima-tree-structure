package com.pidima.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Trie (Prefix Tree) implementation supporting insert, lookup, update, and delete operations.
 */
public class Trie {
    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord = false;
    }

    private final TrieNode root = new TrieNode();

    /**
     * Inserts a word into the trie.
     * @param word non-null, non-empty string
     */
    public void insert(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current = current.children.computeIfAbsent(c, k -> new TrieNode());
        }
        current.isEndOfWord = true;
    }

    /**
     * Looks up a word in the trie.
     * @param word the word to search (null returns false)
     * @return true if the exact word exists in the trie
     */
    public boolean lookup(String word) {
        if (word == null) return false;
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current = current.children.get(c);
            if (current == null) return false;
        }
        return current.isEndOfWord;
    }

    /**
     * Updates an existing word to a new word. This is implemented as delete(old) then insert(new).
     * @param oldWord existing word
     * @param newWord replacement word
     * @return true if oldWord existed and was replaced by newWord, false otherwise
     */
    public boolean update(String oldWord, String newWord) {
        if (oldWord == null || newWord == null || oldWord.isEmpty() || newWord.isEmpty()) {
            throw new IllegalArgumentException("Words cannot be null or empty");
        }
        if (delete(oldWord)) {
            insert(newWord);
            return true;
        }
        return false;
    }

    /**
     * Deletes a word from the trie.
     * @param word non-null, non-empty string
     * @return true if the word existed and was removed; false if the word was not present
     */
    public boolean delete(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
        AtomicBoolean removed = new AtomicBoolean(false);
        delete(root, word, 0, removed);
        return removed.get();
    }

    private boolean delete(TrieNode current, String word, int index, AtomicBoolean removed) {
        if (index == word.length()) {
            if (!current.isEndOfWord) return false; // word not present
            current.isEndOfWord = false;
            removed.set(true);
            return current.children.isEmpty(); // if empty, signal to prune this node
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) return false; // path doesn't exist

        boolean shouldPruneChild = delete(node, word, index + 1, removed);
        if (shouldPruneChild) {
            current.children.remove(ch);
        }
        // Prune current only if a deletion occurred in this subtree and current became redundant
        return removed.get() && current.children.isEmpty() && !current.isEndOfWord;
    }
}
