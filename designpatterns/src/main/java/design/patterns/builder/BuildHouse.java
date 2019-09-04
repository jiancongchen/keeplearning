package design.patterns.builder;

import lombok.Data;

/**
 * @author : jiancongchen on 2019-08-26
 *
 * HouseProvide可以和BuildHouse合并成一个类，重要的是思想，而不是类的形式。
 * 在JDK中，StringBuilder和StringBuffer也应用了建造者模式
 **/
@Data
public class BuildHouse {

    /**
     * 可以通过构造器给属性设定默认值，客户端代码再调用过程中
     * 如果有需要则调用方法对属性进行覆盖，不调用，则使用默认值。
     */
    private String kitchen = "超大厨房";
    private String bathroom;
    private String livingRoom;
    private String study;
    private String bedroom;
    private String balcony;
    private String door;

    public House build(){
        return new House(this);
    }

    /**
     * 可以在这些方法中检查参数对有效性
     * @param kitchen
     * @return
     */
    public BuildHouse planningKitchen(String kitchen) {
        this.kitchen = kitchen;
        return this;
    }

    public BuildHouse planningBathroom(String bathroom) {
        this.bathroom = bathroom;
        return this;
    }

    public BuildHouse planningLivingRoom(String livingRoom) {
        this.livingRoom = livingRoom;
        return this;
    }

    public BuildHouse planningStudy(String study) {
        this.study = study;
        return this;
    }

    public BuildHouse planningBedRoom(String bedroom) {
        this.bedroom = bedroom;
        return this;
    }

    public BuildHouse planningBalcony(String balcony) {
        this.balcony = balcony;
        return this;
    }

    public BuildHouse planningDoor(String door) {
        this.door = door;
        return this;
    }


}
