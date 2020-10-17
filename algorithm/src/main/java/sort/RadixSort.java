package sort;

import java.util.Arrays;

/**
 * 基数排序
 *
 * 每次循环遍历数组将元素放在指定位置O(n)，在从桶中取出数据O(n)，循环d次（d是位数），
 * 时间复杂度就是O((n+r)*d),r是基数，比如：10
 *
 * 参考：https://www.cnblogs.com/developerY/p/3172379.html
 *
 * @author : jiancongchen on 2019/10/13
 */

public class RadixSort implements Sort{

    @Override
    public void sort(int[] arr) {
        // 找到最大的数，根据最大数据的位数作为循环的结束
        int max = 0;
        for (int i = 0; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        int n = 1;
        for (int j = max; j > 0; j = j /10){
            n = n * 10;
        }

        int d = 1;
        //二维数组存放，0-9，每一个存放对应的数字，极端情况所有数字的个位一样
        //所以需要arr.length
        int[][] bucket = new int[10][arr.length];
        // 存放每个桶中有多少个数字，总共是10个桶
        //（极端情况，在同一个桶）
        int[] count = new int[10];

        while(d < n){
            int k = 0;
            Arrays.fill(count,0);
            for(int temp : arr){
                int index = (temp/d)%10;
                //bucket不需要重置为0，因为输出根据count数组的统计，
                //统计到的数据，必定是覆盖后的最新数据
                bucket[index][count[index]] = temp;
                count[index] ++;
            }
            for(int i = 0; i < 10; i++){
                if(count[i] != 0){
                    for(int j = 0; j < count[i]; j ++){
                        arr[k] = bucket[i][j];
                        k++;
                    }
                }
            }
            d = d * 10;
            for(int index : arr){
                System.out.print(index + " ");
            }
            System.out.println();
        }
    }

}
