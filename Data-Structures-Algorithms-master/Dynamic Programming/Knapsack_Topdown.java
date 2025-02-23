public class Knapsack_Topdown{
    private static int knapsack(int[] weights, int[] values, int capacity, int index){

        int[][] dp = new int[index+1][capacity+1];

        // Build the DP table bottom-up
        for (int i = 1; i <= index; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    // Max of including or excluding the item
                    int include = values[i - 1] + dp[i - 1][w - weights[i - 1]];
                    int exclude = dp[i - 1][w];
                    dp[i][w] = Math.max(include, exclude);

                } else if (weights[i - 1] <= w) {
                    // If weight exceeds capacity, exclude the item
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[index][capacity];
    }



    public static void main(String[] args){

        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int capacity = 5;
        int n = weights.length;

        int maxProfit = knapsack(weights, values, capacity, n);
        System.out.println("Maximum Profit: " + maxProfit);
    }
}