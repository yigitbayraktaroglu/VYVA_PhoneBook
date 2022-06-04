package vyva.vyva_phonebook;

import java.util.ArrayList;

public class BubbleSort {
    public static ArrayList sort(ArrayList arrayList) {
        int n = arrayList.size();
        String names[] = new String[100];
        // create string array called names
        for (int i = 0; i < n; i++) {
            names[i] = (String) arrayList.get(i);
        }
        String temp;
        Object tempA;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // to compare one string with other strings
                if (names[i].compareTo(names[j]) > 0) {
                    // swapping
                    temp = names[i];
                    tempA=arrayList.get(i);
                    names[i] = names[j];
                    arrayList.set(i,arrayList.get(j));
                    arrayList.set(j,tempA);
                    names[j] = temp;
                }
            }
        }
        return arrayList;
    }



}
