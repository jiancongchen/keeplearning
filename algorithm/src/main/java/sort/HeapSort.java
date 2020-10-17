package sort;

/**
 * 堆排序
 *
 * 平均时间复杂度：O(NlogN)
 *
 * 参考：https://www.cnblogs.com/chengxiao/p/6129630.html
 *
 * 堆排序为啥最后一个非叶子节点是  arr.length/2 - 1 ？
 * 参考：https://blog.csdn.net/weixin_30553065/article/details/99537126?utm_medium=distribute.pc_relevant.none-task-blog-title-4&spm=1001.2101.3001.4242
 *
 *  总共有n个节点，存放在数组中
 * （1）堆的最后一个非叶子节点若只有左孩子  2i + 1 = n -1  ===> i = n/2 -1
 * （2）堆的最后一个非叶子节点有左右两个孩子  2i + 1 = n - 2 ===> i = ((n - 2) - 1)/2  ===> ((n - 1) -2)/2  ===> (n - 1)/ 2 - 1
 *  当堆有左右孩子的时候，n必定为奇数，程序中，向下取整，那么 (n - 1)/ 2 等价于 n / 2 -1
 *
 *  堆排序是一种选择排序，整体主要由构建初始堆+交换堆顶元素和末尾元素并重建堆两部分组成。其中构建初始堆经推导复杂度为O(n)，
 *  在交换并重建堆的过程中，需交换n-1次，而重建堆的过程中，根据完全二叉树的性质，[log2(n-1),log2(n-2)...1]逐步递减，
 *  近似为nlogn。所以堆排序时间复杂度一般认为就是O(nlogn)级。
 *
 *  构建二叉堆时间复杂度的证明：O(n)
 *  https://blog.csdn.net/LinuxTiger/article/details/7172258?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param
 *
 * @author : jiancongchen on 2019/10/13
 */

public class HeapSort implements Sort{

    @Override
    public void sort(int[] arr) {
        //遍历调整非叶子节点
        for(int i = arr.length/2 - 1; i >= 0; i--){
            adjustHeap(arr, i, arr.length);
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
            //调整除了最大元素之外的位置，最大的元素放在末尾
            adjustHeap(arr,0,j);
        }
    }

    /**
     * 调整构建大顶堆
     * @param arr
     * @param i
     * @param length
     */
    public void adjustHeap(int[] arr, int i,int length){
        int temp = arr[i];
        for(int k = 2*i + 1; k < length; k = k*2 + 1){
            //判断右节点是否比左节点要大
           if(k + 1 < length && arr[k + 1] > arr[k]){
               //切换到右节点，始终保证发生调整的节点是k，那么需要对K节点进行后续的调整
                k++;
           }
           //如果根节点大于左右节点则满足条件，这里要用temp不能用a[i]，
            //因为a[i]在二次调整中，会发生变化，不是原来的值
           if(temp > arr[k]){
               break;
           }
           arr[i] = arr[k];
           i = k;
        }
        //寻找的是第i个元素最合适的位置
        arr[i] = temp;
    }
}
