package sort;

/**
 *
 * 插入排序
 *
 * 基本思想：
 * 在要排序的一组数中，假定前n-1个数已经排好序，现在将第n个数插到前面的有序数列中，使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序。
 *
 * 平均时间复杂度：O(n^2)
 *
 * @author : jiancongchen on 2019/10/9
 */

public class InsertionSort implements Sort{

    @Override
    public void sort(int[] arr) {
        int temp;
        //因为j是i + 1，循环的次数数组长度减去1，最后一个数插入成功，排序就完成了
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = i + 1; j > 0; j--){
                //第i+1个和前面有序的进行比较，跟第i个比较
                if(arr[j] < arr[j-1] ){
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }else{
                    //不能交换，说明已经找到合适的位置插入
                    break;
                }
            }
            for(int index : arr){
                System.out.print(index + " ");
            }
            System.out.println();
        }
    }
}
