package sort;

/**
 *
 * 基本思想：两个数比较大小，较大的数下沉，较小的数冒起来。
 * 平均时间复杂度：O(n^2)
 *
 * 数据的顺序排好之后，冒泡算法仍然会继续进行下一轮的比较，直到arr.length-1次，后面的比较没有意义的。
 * @author : jiancongchen on 2019-10-09
 **/
public class BubbleSort {

    public static void sort(int [] arr){

        int temp;//临时变量
        boolean flag;//是否交换的标志

        //表示趟数，一共 arr.length-1 次
        for(int i=0; i<arr.length-1; i++){
            // 每次遍历标志位都要先置为false，才能判断后面的元素是否发生了交换
            flag = false;
            //选出该趟排序的最大值往后移动
            for(int j=arr.length-1; j>i; j--){
                if(arr[j] < arr[j-1]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    //只要有发生了交换，flag就置为true
                    flag = true;
                }
            }
            for(int index : arr){
                System.out.print(index + " ");
            }
            System.out.println();
            // 判断标志位是否为false，如果为false，说明后面的元素已经有序，就直接return
            if(!flag)  {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{12,23,45,7,87,33,94,26,88,34,76};
        sort(arr);
    }
}
