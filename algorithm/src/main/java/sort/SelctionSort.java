package sort;

/**
 *
 * 选择排序
 *
 * 基本思想：
 * 在长度为N的无序数组中，第一次遍历n-1个数，找到最小的数值与第一个元素交换；
 * 第二次遍历n-2个数，找到最小的数值与第二个元素交换；
 * 。。。
 * 第n-1次遍历，找到最小的数值与第n-1个元素交换，排序完成。
 *
 * 平均时间复杂度：O(n^2)
 * \
 * @author : jiancongchen on 2019-10-09
 **/
public class SelctionSort implements Sort{

    @Override
    public void sort(int[] arr){
        for(int i = 0; i < arr.length; i ++){
            int min = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            if(min != i){
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
            for(int k : arr){
                System.out.print(k + " ");
            }
            System.out.println();
        }
    }
}
