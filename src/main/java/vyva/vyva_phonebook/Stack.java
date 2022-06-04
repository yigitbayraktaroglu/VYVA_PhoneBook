package vyva.vyva_phonebook;

import java.util.ArrayList;
import java.util.LinkedList;

class Stack {
private int size;
    private LinkedList l = new LinkedList();

    public void push(Object obj) {
        size++;
        l.addFirst(obj);
    }

    public Object top() {
        return l.getFirst();
    }
    public ArrayList getAll(){
        ArrayList<String> array = new ArrayList();
        for(int i=0;size>i;i++) {
            array.add((String) l.get(i));
        }
        return  array;
    }

    public Object pop() {
        if(size==0) return 0;
        else{
            size--;
            return l.removeFirst();
        }

    }


}