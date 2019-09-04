package design.patterns.flyweight;

import lombok.Data;

/**
 * @author : jiancongchen on 2019-08-26
 **/
@Data
public class Circle implements Shape {

    private String color;
    private int x;
    private int y;
    private int radius;

    public void draw() {
        System.out.println("Circle: Draw() [Color : " + color
                +", x : " + x +", y :" + y +", radius :" + radius);
    }
}
