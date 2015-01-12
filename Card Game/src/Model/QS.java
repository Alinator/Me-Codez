package Model;

import java.util.Collections;
import java.util.List;

public class QS {
	
	public static void sort(List<Card> a) {
		sort(a, 0, a.size() - 1);
    }
    private static void sort(List<Card> a, int lo, int hi) { 
        if (hi <= lo) {
            return;
        }
        int lt = lo, i = lo + 1, gt = hi;
        int v = a.get(lo).getNumber();
        while (i <= gt) {
            int cmp = a.get(i).getNumber();
            if (cmp < v) {

                Collections.swap(a, lt++, i++);
            } else if (cmp > v) {

                Collections.swap(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }
}
