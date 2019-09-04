package design.patterns.builder;

/**
 *
 *      JDK中建造者设计模式
 *
 *      关键代码：创建一个对应的构建器，构建器的一个方法只赋值一个属性，最后由一个build方法调用真正需要构建的类构造函数。
 *
 *      StringBuilder继承了AbstractStringBuilder，而AbstractStringBuilder实现了appendable。
 *      StringBuilder：指挥者类，持有具体建造者的引用，由于StringBuilder继承了AbstractStringBuilder，
 *  这里StringBuilder通过super来作为具体建造者的引用。
 *      传统的实现中，append的实现应该放在具体的StringBuilder中，但是重要的是思想，不要拘泥于形式。
 *      AbstractStringBuilder：具体建造者，它实现了appendable接口的append(Character c)方法。
 *      appendable：抽象建造者，定义了创建对象的接口。
 *
 *
 *      注意对比其他两种类似的模式：
 *      （1）重叠构造器模式，可以参考JDK中线程池的创建；
 *      （2）JavaBeans模式，现调用一个无参构造器来创建对象，然后再调用setter方法来设置每个必要的参数，以及每个相关的可选参数。
 *
 *
 *
  */

