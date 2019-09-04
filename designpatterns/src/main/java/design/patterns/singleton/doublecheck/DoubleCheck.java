package design.patterns.singleton.doublecheck;

/**
 * @author : jiancongchen on 2019/8/25
 */

public class DoubleCheck {

    private DoubleCheck(){}

    /**
     * volatile 保证可见性
     */
    private volatile DoubleCheck doubleCheck;

    public DoubleCheck getInstance(){
        //不为空直接返回
        if(doubleCheck == null){
            synchronized (DoubleCheck.class){
                //加锁之后重新判断，避免在加锁期间对象完成初始化
                if (doubleCheck == null){
                    doubleCheck = new DoubleCheck();
                }
            }
        }
        return doubleCheck;
    }
}
