package design.patterns.singleton.enumforsingleton;

/**
 * @author : jiancongchen on 2019/8/25
 *
 * 默认枚举实例的创建是线程安全的，并且在任何情况下都是单例
 */

public enum EnumForSingleton {

    /**
     * 实例
     */
    INSTANCE;

    public void doSomething(){}

}
