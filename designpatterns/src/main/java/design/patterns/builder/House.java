package design.patterns.builder;

import lombok.Data;

/**
 * @author : jiancongchen on 2019-08-26
 **/
@Data
public class House {

    private String kitchen;
    private String bathroom;
    private String livingRoom;
    private String study;
    private String bedroom;
    private String balcony;
    private String door;

    /**
     * 为了确保这些变量免受攻击，复制完对象域，应该
     * 检查这些属性的合法性
     * @param buildHouse
     */
    public House(BuildHouse buildHouse){
        this.kitchen = buildHouse.getKitchen();
        this.bathroom = buildHouse.getBathroom();
        this.livingRoom = buildHouse.getLivingRoom();
        this.study = buildHouse.getStudy();
        this.bedroom = buildHouse.getBedroom();
        this.balcony = buildHouse.getBalcony();
        this.door = buildHouse.getDoor();
    }

    @Override
    public String toString() {
        return "House{" +
                "kitchen='" + kitchen + '\'' +
                ", bathroom='" + bathroom + '\'' +
                ", livingRoom='" + livingRoom + '\'' +
                ", study='" + study + '\'' +
                ", bedroom='" + bedroom + '\'' +
                ", balcony='" + balcony + '\'' +
                ", door='" + door + '\'' +
                '}';
    }
}
