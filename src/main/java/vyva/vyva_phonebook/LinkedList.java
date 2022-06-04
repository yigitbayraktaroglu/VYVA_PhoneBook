package vyva.vyva_phonebook;

import java.util.ArrayList;
import java.util.Collections;

public class LinkedList {
    Node head; // listenin başlangıcı

    /* Linked list Node*/
    class Node {
        Users data;
        Node next;

        Node(Users d) {
            data = d;
            next = null;
        }
    }


    //Linked listin başına yeni node ekler
    public void push(Users new_data) {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }


    //linkedlisti arrayliste çevirir.
    public ArrayList getList() {
        ArrayList<String> arrayList = new ArrayList<>();
        Node tnode = head;
        while (tnode != null) {
            arrayList.add(tnode.data.getIsim() + " " + tnode.data.getSoyisim() + " " + tnode.data.getTelNo());
            tnode = tnode.next;
        }
        Collections.reverse(arrayList);
        return arrayList;
    }

    public int getCount() {
        Node temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    void deleteList() {
        head = null;
    }


}
