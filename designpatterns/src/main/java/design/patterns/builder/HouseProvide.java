package design.patterns.builder;

/**
 * @author : jiancongchen on 2019-08-26
 **/
public class HouseProvide {

    private HouseProvide(){}

    public static House getOneHouse(){
        BuildHouse buildHouse = new BuildHouse();
        return buildHouse.planningStudy("超大书房").planningBalcony("阳台").planningBedRoom("卧室总是要的").build();
    }

    public static House getTwoHouse(){
        BuildHouse buildHouse = new BuildHouse();
        return buildHouse.planningDoor("门").planningKitchen("我要做饭").planningBathroom("洗澡的").planningLivingRoom("客厅").build();
    }

    public static void main(String[] args) {
        System.out.println(HouseProvide.getOneHouse());
        System.out.println(HouseProvide.getTwoHouse());
    }
}
