package design.patterns.flyweight;

import java.util.HashMap;

/**
 * @author : jiancongchen on 2019-08-26
 **/
public class ShapeFactory {

    /**
     * 享元模式，即为共享可复用的对象
     */
    private static final HashMap<String, Shape> shapeMap = new HashMap<String, Shape>();

    public static Shape getShape(String color){
        Shape shape = shapeMap.get(color);
        //共享对象，如果存在不需要重新创建对象，没有则创建
        if(shape == null){
            Circle circle = new Circle();
            shapeMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
            shape = circle;
        }
        return shape;
    }
}
