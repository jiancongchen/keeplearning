package design.patterns.singleton.hungrymode;

/**
 * @author : jiancongchen on 2019-08-22
 *
 * 这种方式，类加载较慢，但获取对象的速度快。因为基于类加载机制避免了多线程的同步问题，
 * 但是饿汉式的创建方式在一些场景中将无法使用：譬如 Singleton 实例的创建是依赖参数或者配置文件的，在 getInstance() 之前必须
 * 调用某个方法设置参数给它，那样这种单例写法就无法使用了。
 **/
public class HungryMode {

    private static final HungryMode hungryMode = new HungryMode();

    private HungryMode(){}

    public static HungryMode getInstance(){
        return hungryMode;
    }
}
