package sort;

/**
 * 堆排序
 *
 * 平均时间复杂度：O(NlogN)
 *
 * 参考：https://www.cnblogs.com/chengxiao/p/6129630.html
 *
 * @author : jiancongchen on 2019/10/13
 */

public class HeapSort implements Sort{

    @Override
    public void sort(int[] arr) {
        //遍历调整非叶子节点
        for(int i = arr.length/2 - 1; i >= 0; i--){
            adjustHead(arr,i,arr.length);
        }
        //逐个调整
        for(int j = arr.length -1; j >= 0; j--){
            //将最大的放到最后一个，下一个放到j-1的位置
            int temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            for(int index : arr){
                System.out.print(index + " ");
            }
            System.out.println();
            //调整除了最大元素之外的位置
            adjustHead(arr,0,j);
        }
    }


    /**
     * 调整构建大顶堆
     * @param arr
     * @param i
     * @param length
     */
    public void adjustHead(int[] arr, int i,int length){
        int temp = arr[i];
        for(int k = 2*i + 1; k < length; k = k*2 + 1){
            //判断右节点是否比左节点要大
           if(k + 1 < length && arr[k + 1] > arr[k]){
               //切换到右节点
                k++;
           }
           //如果根节点大于左右节点则满足条件，这里要用temp不能用a[i]，
            //因为a[i]在二次调整中，会发生变化，不是原来的值
           if(temp > arr[k]){
               break;
           }
           arr[i] = arr[k];
           //发生调整的节点是k，
           i = k;
        }
        //寻找的是第i个元素最合适的位置
        arr[i] = temp;
    }
}
