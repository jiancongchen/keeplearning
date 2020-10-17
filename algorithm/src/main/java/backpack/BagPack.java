package backpack;

/**
 * 0-1 背包问题
 * @author jiancongchen on 2020/10/17
 */
public class BagPack {

    public static void main(String[] args) {
        int[] values = {6,3,5,4,6};
        int[] weights = {2,2,6,5,4};
        int max = 10;
        System.out.println(maxValue(values, weights, max));
    }

    /**
     * 计算最大值
     * @param values
     * @param weights
     * @param max
     * @return
     */
    public static int maxValue(int[] values, int[] weights, int max){
        // 必要的简单校验
        if (values == null || values.length == 0) {
            return 0;
        }
        if (weights == null || weights.length == 0) {
            return 0;
        }
        if (values.length != weights.length || max < 0){
            return 0;
        }

        // +1 的原因是因为在为0的时候要有结果
        int[][] dp = new int[values.length + 1][max + 1];
        for (int i = 1; i <= values.length; i ++){
            for (int j = 1; j <= max; j++){
                // 背包的空间 小于 当前物品的重量，选择上一次的方案
                if(j < weights[i - 1]){
                    // 也就是没有这个物品的时候的结果
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 如果能装这个物品，那么存在两种选择，装这个物品，或者不装这个物品
                    // 不装这个物品，那么方案便和没有这个物品的结果一样
                    // 装这个物品，那么也就是剩下的物品，和背包剩下的空间，去找最优解
                    // 两者取最大值
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                }
            }
        }
        return dp[values.length][max];
    }
}
