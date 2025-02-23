import java.util.Arrays;

public class Knapsack_Memorisation{

    public static int knapsack(int[] weights, int[] values, int capacity, int index, int[][] dp ){
        // Bse case
        if(index ==0  || capacity == 0){
            return 0;
        }

        if(dp[capacity][index] != -1){
            return dp[capacity][index];
        }


        // if weight of n-1 is greater than the knapsack weight
        if(weights[index-1]> capacity){
            return dp[capacity][index] = knapsack(weights, values,  capacity, index-1, dp);
        }

        // if we include or exclude the object
        int include = values[index-1] + knapsack(weights, values, capacity- weights[index-1], index-1, dp);
        int exclude = knapsack(weights, values, capacity,  index-1, dp);

        return dp[capacity][index] = Math.max(include, exclude);
    }

    public static void main(String[] args){
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};

        int capacity = 5;
        int n = weights.length;

        int[][] dp= new int[capacity+1][n+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        int maxProfit = knapsack(weights, values, capacity, n, dp);

        System.out.println("Maximum Profit: "+ maxProfit);
    }
}