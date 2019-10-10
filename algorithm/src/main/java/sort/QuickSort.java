package sort;

/**
 *
 * 快速排序
 *
 * 基本思想：（分治）
 * 先从数列中取出一个数作为key值；
 * 将比这个数小的数全部放在它的左边，大于或等于它的数全部放在它的右边；
 * 对左右两个小数列重复第二步，直至各区间只有1个数。
 *
 * 平均算法复杂度：O(N*logN)
 *
 * 参考网址：https://blog.csdn.net/nrsc272420199/article/details/82587933
 *
 * @author : jiancongchen on 2019-10-10
 **/
public class QuickSort implements Sort{

    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public void quickSort(int[] arr, int low, int high){
        if(low < high){
            int index = getIndex(arr, low, high);
            quickSort(arr, low, index - 1);
            quickSort(arr, index + 1, high);
        }
    }

    public int getIndex(int[] arr, int low, int high){
        int tmp = arr[low];
        while(low < high){
            while(low < high && arr[high] > tmp){
                high --;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] < tmp){
                low ++;
            }
            arr[high] = arr[low];
        }
        //循环借宿之后，low=high，并且这个位置是最初确定的基数的位置
        arr[low] = tmp;
        for(int index : arr){
            System.out.print(index + " ");
        }
        System.out.println();
        return low;
    }
}
