package sort;

/**
 *
 * 归并排序
 *
 * 基本思想：归并排序将待排序的元素序列分成两个长度相等的子序列，为每一个子序列排序，然后再将他们合并成一个子序列。
 * 合并两个子序列的过程也就是两路归并。
 *
 * 平均算法复杂度：O(N*logN)
 *
 * @author : jiancongchen on 2019-10-10
 **/
public class MergeSort implements Sort{

    @Override
    public void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }


    public void mergeSort(int[] a, int start, int end){
        if(start < end){
            int  mid = (end + start) / 2;
            mergeSort(a, start, mid);
            mergeSort(a, mid + 1, end);
            merge(a, start, mid, end);
        }
    }

    public void merge(int[] a, int left, int mid, int right){
        int[] temp = new int[a.length];
        //归并排序的时候，数组内的left-mid，以及 mid+1-right，进行合并
        //p1和p2分别指向两个需要合并的位置，K表示对应的数据存在的位置，便于后续放回到数组a中
        int p1 = left, p2 = mid + 1, k = left;
        while(p1 < mid && p2 < right){
            if(a[p1] < a[p2]){
                temp[k++] = a[p1++];
            }else{
                temp[k++] = a[p2++];
            }
        }
        //注意等号
        while (p1 <= mid){
            temp[k++] = a[p1++];
        }
        while(p2 <= right){
            temp[k++] = a[p2++];
        }
        //注意等号,把临时数组中排好序的部分，放回元原数组中
        for(int i = left; i <= right; i++){
            a[i] = temp[i];
        }
        for(int index : a){
            System.out.print(index + " ");
        }
        System.out.println();
    }
}
