package com.pidima.trie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrieTest {
    private Trie trie;

    @BeforeEach
    void setUp() {
        trie = new Trie();
    }

    @Test
    void testInsertAndLookup() {
        trie.insert("hello");
        assertTrue(trie.lookup("hello"));
        assertFalse(trie.lookup("hell"));
        assertFalse(trie.lookup("helloo"));
    }

    @Test
    void testUpdate() {
        trie.insert("hello");
        assertTrue(trie.lookup("hello"));

        assertTrue(trie.update("hello", "world"));
        assertFalse(trie.lookup("hello"));
        assertTrue(trie.lookup("world"));

        assertFalse(trie.update("nonexistent", "new"));
    }

    @Test
    void testDelete() {
        trie.insert("hello");
        trie.insert("hell");
        trie.insert("he");

        assertTrue(trie.delete("hello"));
        assertFalse(trie.lookup("hello"));
        assertTrue(trie.lookup("hell"));
        assertTrue(trie.lookup("he"));

        assertFalse(trie.delete("nonexistent"));
    }

    @Test
    void testEmptyTrieAndNulls() {
        assertFalse(trie.lookup("any"));
        assertThrows(IllegalArgumentException.class, () -> trie.delete(""));
        assertThrows(IllegalArgumentException.class, () -> trie.insert(""));
        assertFalse(trie.lookup(null));

        assertThrows(IllegalArgumentException.class, () -> trie.update(null, "x"));
        assertThrows(IllegalArgumentException.class, () -> trie.update("x", null));
        assertThrows(IllegalArgumentException.class, () -> trie.update("", "x"));
        assertThrows(IllegalArgumentException.class, () -> trie.update("x", ""));
        assertThrows(IllegalArgumentException.class, () -> trie.delete(null));
    }
}
