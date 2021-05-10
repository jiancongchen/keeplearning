package sort;

/**
 *
 * 冒泡排序
 * 基本思想：两个数比较大小，较大的数下沉，较小的数冒起来。
 * 1. 一趟比较只能确定一个数的位置，所以需要比较 n-1 趟
 * 2. 每次比较从最后开始，小的数往前进行交换。由于每一趟确定一个最小的数，所以比较到趟数 i 就可以了；
 * 3. 由于在交换过程中，有可能提前完成排序，如果没有发生交换，说明排序已经完成
 *
 * 平均时间复杂度：O(n^2)
 *
 * 数据的顺序排好之后，冒泡算法仍然会继续进行下一轮的比较，直到arr.length-1次，后面的比较没有意义的。
 *
 * @author : jiancongchen on 2019-10-09
 **/
public class BubbleSort implements Sort{

    @Override
    public void sort(int[] arr){

        int temp;//临时变量
        boolean flag;//是否交换的标志

        //表示趟数，一共 arr.length-1 次
        for(int i=0; i<arr.length-1; i++){
            // 每次遍历标志位都要先置为false，才能判断后面的元素是否发生了交换
            flag = false;
            //选出该趟排序的最大值往后移动，每一次排序都可以确认一个元素，
            // 每次排序都可以减少一个元素的比较
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
}
