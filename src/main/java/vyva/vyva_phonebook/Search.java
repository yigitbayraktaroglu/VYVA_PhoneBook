package vyva.vyva_phonebook;

// Java Program to Implement a Phone
// Directory Using Trie Data Structure

import javafx.scene.control.TextArea;

import java.util.*;

class TrieNode {
    HashMap<Character, TrieNode> child;
    boolean isLast;


    public TrieNode() {
        child = new HashMap<Character, TrieNode>();

        // Tüm Trie nodelarını null yapar.
        for (char i = 'a'; i <= 'z'; i++)
            child.put(i, null);

        isLast = false;
    }
}

class Trie {
    TrieNode root;


    public void insertIntoTrie(String contacts[]) {
        root = new TrieNode();
        int n = contacts.length;
        for (int i = 0; i < n; i++) {
            insert(contacts[i]);
        }
    }


    public void insert(String s) {
        int len = s.length();

        // 'itr' Trie Node'u tekrarlamak için kullanılır
        TrieNode itr = root;
        for (int i = 0; i < len; i++) {
            //s[i]'nin Trie'de zaten mevcut olup olmadığını kontrol eder
            TrieNode nextNode = itr.child.get(s.charAt(i));
            if (nextNode == null) {
                // Eğer yoksa yeni bir trienode oluştur.
                nextNode = new TrieNode();

                // Hashmap'e ekler
                itr.child.put(s.charAt(i), nextNode);
            }

            // tekrarlayıcıyı bir sonraki node'a götürür.
            itr = nextNode;

            if (i == len - 1)
                itr.isLast = true;
        }
    }


    public void displayContactsUtil(TrieNode curNode,
                                    String prefix, TextArea bulunan) {
        //Prefix Trienin sonundaysa yazdırır.
        if (curNode.isLast) {
            bulunan.appendText("\n" + prefix);

        }

        for (char i = 'a'; i <= 'z'; i++) {
            TrieNode nextNode = curNode.child.get(i);
            if (nextNode != null) {
                displayContactsUtil(nextNode, prefix + i, bulunan);
            }
        }
    }


    void displayContacts(String str, TextArea bulunan) {
        TrieNode prevNode = root;


        String prefix = "";
        int len = str.length();

        int i;
        for (i = 0; i < len; i++) {

            prefix += str.charAt(i);


            char lastChar = prefix.charAt(i);


            TrieNode curNode = prevNode.child.get(lastChar);

            if (curNode == null) {
                bulunan.appendText("\nNo Results Found for "
                        + prefix + "");
                System.out.println("\nNo Results Found for "
                        + prefix + "");
                i++;
                break;
            }


            bulunan.appendText("\n" + prefix + " icin bulunan sonuclar: ");


            displayContactsUtil(curNode, prefix, bulunan);


            prevNode = curNode;
        }

        for (; i < len; i++) {

            prefix += str.charAt(i);
            bulunan.appendText("\n" + prefix + "icin sonuc bulunamadi."
            );

        }
    }
}




