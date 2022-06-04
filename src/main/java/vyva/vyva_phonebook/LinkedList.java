package vyva.vyva_phonebook;

import java.util.ArrayList;

public class LinkedList {
    Node head; // head of list

    /* Linked list Node*/
    class Node {
        Users data;
        Node next;
        Node(Users d)
        {
            data = d;
            next = null;
        }
    }

    /* Given a key, deletes the first
    occurrence of key in
    * linked list */
    void deleteNode(Users key)
    {
        // Store head node
        Node temp = head, prev = null;

        // If head node itself holds the key to be deleted
        if (temp != null && temp.data == key) {
            head = temp.next; // Changed head
            return;
        }

        // Search for the key to be deleted, keep track of
        // the previous node as we need to change temp.next
        while (temp != null && temp.data != key) {
            prev = temp;
            temp = temp.next;
        }

        // If key was not present in linked list
        if (temp == null)
            return;

        // Unlink the node from linked list
        prev.next = temp.next;
    }

    /* Inserts a new Node at front of the list. */
    public void push(Users new_data)
    {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }

    /* This function prints contents of linked list starting
    from the given node */
    public void printList()
    {
        Node tnode = head;
        while (tnode != null) {
            System.out.print(tnode.data.getIsim() + " "+tnode.data.getSoyisim()+" "+tnode.data.getTelNo()+"\n");
            tnode = tnode.next;
        }
    }
    public ArrayList getList()
    {
        ArrayList<String> arrayList = new ArrayList<>();
        Node tnode = head;
        while (tnode != null) {
            arrayList.add(tnode.data.getIsim() + " "+tnode.data.getSoyisim()+" "+tnode.data.getTelNo());
            tnode = tnode.next;
        }
        return arrayList;
    }

    public int getCount()
    {
        Node temp = head;
        int count = 0;
        while (temp != null)
        {
            count++;
            temp = temp.next;
        }
        return count;
    }
    void deleteList()
    {
        head = null;
    }
    /* Driver program to test above functions. Ideally this
    function should be in a separate user class. It is kept
    here to keep code compact */

}
