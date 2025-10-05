# pidima-tree-structure (Trie)

A simple, production-ready implementation of a Trie (prefix tree) in Java with the following operations:

- insert
- lookup
- update
- delete

Includes JUnit 5 unit tests and a small console demo.

## Project Structure

```
pidima-tree-structure/
├── pom.xml
├── README.md
├── .gitignore
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── pidima/
    │               └── trie/
    │                   ├── Demo.java
    │                   └── Trie.java
    └── test/
        └── java/
            └── com/
                └── pidima/
                    └── trie/
                        └── TrieTest.java
```

## Build and Test

Requires Java 11+ and Maven.

- Run the full test suite:

```bash
mvn clean test
```

- Run the demo application:

```bash
mvn -q -DskipTests package
java -cp target/trie-1.0-SNAPSHOT.jar com.pidima.trie.Demo
```

## API Overview

Class: `com.pidima.trie.Trie`

- `void insert(String word)`
  - Inserts a non-null, non-empty word into the trie.
  - Throws `IllegalArgumentException` if `word` is null or empty.

- `boolean lookup(String word)`
  - Returns `true` if the exact word exists in the trie; `false` otherwise.
  - Returns `false` when `word` is `null`.

- `boolean update(String oldWord, String newWord)`
  - Updates an existing `oldWord` to `newWord`.
  - Implemented as `delete(oldWord)` followed by `insert(newWord)`.
  - Returns `true` if `oldWord` existed; `false` otherwise.
  - Throws `IllegalArgumentException` if either argument is null or empty.

- `boolean delete(String word)`
  - Removes `word` from the trie if present.
  - Returns `true` if removed; `false` if not found.
  - Throws `IllegalArgumentException` if `word` is null or empty.

## Complexity

Let `L` be the length of the word:

- Insert: `O(L)`
- Lookup: `O(L)`
- Update: `O(L)` to delete + `O(L)` to insert → `O(L)`
- Delete: `O(L)`

## GitHub Delivery

To deliver this in GitHub:

1. Initialize a git repo and make the first commit

```bash
git init
git add .
git commit -m "feat: initial Trie implementation with tests and demo"
```

2. Create a new GitHub repository (via the GitHub UI or CLI) and note its URL, e.g. `git@github.com:<your-user>/pidima-tree-structure.git` or `https://github.com/<your-user>/pidima-tree-structure.git`.

3. Add the remote and push

```bash
git branch -M main
git remote add origin <YOUR_REPO_URL>
git push -u origin main
```

If you prefer, you can use `gh` (GitHub CLI):

```bash
gh repo create pidima-tree-structure --public --source . --remote origin --push
```

## Notes

- The project targets Java 11 and uses JUnit 5 with the Maven Surefire Plugin.
- The demo class `com.pidima.trie.Demo` provides a quick console showcase of functionality.
