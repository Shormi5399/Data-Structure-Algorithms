// https://leetcode.com/problems/coin-change/description/
import java.util.Arrays;
public class CoinChangeMinCoins{

    private static int coinChangeMinNoofCoins(int[] coins, int amount){

        int n = coins.length;

        int[][]dp = new int[n+1][amount+1];


        // Fill the dp array with a large value (Integer.MAX_VALUE - 1)
        // why -1, becasue we are adding 1 in the end, MAX_VALUE+1 is a -ve no;
        for(int i=0;i<n+1;i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE-1);
        }


        // Base case: To make amount 0, we need 0 coins.
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 0;
        }


        for(int i=1;i<n+1;i++) {
            for (int j = 1; j < amount + 1; j++) {
                // can't take
                if (coins[i - 1] > j) dp[i][j] = dp[i - 1][j];

                    // choices to include or exclude
                else {
                    int include = 1 + dp[i][j - coins[i - 1]];
                    int exclude = dp[i - 1][j];

                    dp[i][j] = Math.min(include, exclude);
                }
            }
        }

        return dp[n][amount];
    }



    public static void main(String[] args){

        int[] coins = {1, 2, 4, 5, 100};
        int amount = 40;

        int minMoOfCoins = coinChangeMinNoofCoins(coins, amount);
        System.out.println("Minimun No of Coins : " + minMoOfCoins);
    }
}