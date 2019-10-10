package sort;

/**
 *
 * 希尔排序
 *
 * 基本思想：
 * 在要排序的一组数中，根据某一增量分为若干子序列，并对子序列分别进行插入排序。
 * 然后逐渐将增量减小,并重复上述过程。直至增量为1,此时数据序列基本有序,最后进行插入排序。
 *
 * 平均算法复杂度：O(n^1.5)
 *
 * @author : jiancongchen on 2019/10/9
 */

public class ShellSort implements Sort{

    @Override
    public void sort(int array[]){
        int temp = 0;
        int incre = array.length;

        while(true){
            incre = incre/2;
            //根据增量分为若干子序列
            for(int k = 0; k < incre; k++){
                for(int i = k + incre; i < array.length; i += incre){
                    for(int j = i; j > k; j -= incre){
                        if(array[j] < array[j-incre]){
                            temp = array[j-incre];
                            array[j-incre] = array[j];
                            array[j] = temp;
                        }else{
                            break;
                        }
                    }
                }
            }
            for(int index : array){
                System.out.print(index + " ");
            }
            System.out.println();
            if(incre == 1){
                break;
            }
        }
    }
}
