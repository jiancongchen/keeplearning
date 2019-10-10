package sort;

/**
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
