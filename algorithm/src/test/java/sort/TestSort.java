package sort;

import org.junit.Test;

/**
 * @author : jiancongchen on 2019-10-09
 **/
public class TestSort {

    @Test
    public void runSort() {
        int[] arr = new int[]{12,23,45,7,87,33,94,26,88,34,76};
        Sort sort = new ShellSort();
        sort.sort(arr);
    }
}
