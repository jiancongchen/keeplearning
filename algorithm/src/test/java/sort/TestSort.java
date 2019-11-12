package sort;

import org.junit.Test;

/**
 * @author : jiancongchen on 2019-10-09
 **/
public class TestSort {

    @Test
    public void runSort() {
        int[] arr = new int[]{12,23,49,7,81,35,94,26,88};
        Sort sort = new RadixSort();
        sort.sort(arr);
    }
}
