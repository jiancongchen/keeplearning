package sort;

import java.util.Arrays;

/**
 * @author : jiancongchen on 2019/10/13
 */

public class RadixSort implements Sort{

    @Override
    public void sort(int[] arr) {
        int n = 100;
        int d = 1;
        //二维数组存放，0-9，每一个存放对应的数字3，极端情况所有数字的个位一样
        //所以需要arr.length
        int[][] bucket = new int[10][arr.length];
        //存放每个桶中有多少个数字，极端情况，在同一个桶
        int[] count = new int[arr.length];

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
