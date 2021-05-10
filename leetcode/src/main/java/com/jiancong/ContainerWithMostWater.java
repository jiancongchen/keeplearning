package com.jiancong;

/**
 * @author jiancongchen on 2021/5/6
 */
public class ContainerWithMostWater {

    public static int maxArea(int[] height) {
        if(height.length < 2){
            return 0;
        }
        int width = height.length - 1;
        int i = 0, j = height.length - 1;
        int mostWater = 0;
        while (i < j && i < height.length && j < height.length){
            int high = Math.min(height[i], height[j]);
            mostWater = Math.max(mostWater, width * high);
            if(height[i] < height[j]){
                i++;
            } else {
                j--;
            }
            width--;
        }
        return mostWater;
    }

    public static void main(String[] args) {
        int[] height = new int[]{2,3,4,5,18,17,6};
        System.out.println(maxArea(height));
    }
}
