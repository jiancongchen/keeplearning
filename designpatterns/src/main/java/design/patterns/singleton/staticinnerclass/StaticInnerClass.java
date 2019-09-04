package design.patterns.singleton.staticinnerclass;

/**
 * @author : jiancongchen on 2019/8/25
 *
 * 第一次加载StaticInnerClass类时并不会初始化StaticInnerClass，只有第一次调用getInstance方法时虚拟机加载StaticInnerClassHolder
 * 并初始化staticInnerClassHolder，这样不仅能确保线程安全也能保证StaticInnerClass类的唯一性，
 * 所以推荐使用静态内部类单例模式。
 */

public class StaticInnerClass {

    private StaticInnerClass(){}

    public StaticInnerClass getInstance(){
        return StaticInnerClassHolder.staticInnerClassHolder;
    }

    private static class StaticInnerClassHolder{
        private static final StaticInnerClass staticInnerClassHolder = new StaticInnerClass();
    }

    /**
     * 有一种情况下他们会重新创建对象，那就是反序列化，将一个单例实例对象写到磁盘再读回来，从而获得了一个实例。
     * 反序列化操作提供了readResolve方法，这个方法可以让开发人员控制对象的反序列化。在上述的几个方法示例中
     * 如果要杜绝单例对象被反序列化是重新生成对象，就必须加入如下方法：
     * @return
     */
    private Object readResolve(){
        return getInstance();
    }
}
