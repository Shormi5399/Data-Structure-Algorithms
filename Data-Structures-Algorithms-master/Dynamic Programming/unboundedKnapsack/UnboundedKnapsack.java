public class UnboundedKnapsack{
    // Recurision
    private static int unboundedKnapsack_Recursion(int[] values,int[] weights, int capacity, int index){
        // Base case:
        if(capacity == 0 || index == 0) return 0;

        //weight is greater than capacity
        if(weights[index]> capacity) return unboundedKnapsack_Recursion(values, weights, capacity, index-1);

        // choices: include and exclude
        else if (weights[index] <=capacity){
            int include = unboundedKnapsack_Recursion(values, weights, capacity-weights[index], index);
            int exclude = unboundedKnapsack_Recursion(values, weights, capacity, index-1);

            return Math.max(include, exclude);
        }

        return 0;

    }


    // Memorisation
    private static int unboundedKnapsack_Memorisation(int[] values,int[] weights, int capacity, int index){
         return 0;
    }

    //Top Dowm Approch
    private static int unboundedKnapsack_TopDown(int[] values,int[] weights, int capacity, int index){

        //Base-case:
        int[][] dp = new int[index+1][capacity+1];


        // Build the DP table bottom-up
        for (int i = 1; i <= index; i++) {
            for (int w = 0; w <= capacity; w++) {

                if (weights[i - 1] <= w) {
                    // Max of including or excluding the item
                    int include = values[i - 1] + dp[i][w - weights[i - 1]];
                    int exclude = dp[i - 1][w];

                    dp[i][w] = Math.max(include, exclude);

                } else if (weights[i - 1] > w) {
                    // If weight exceeds capacity, exclude the item
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[index][capacity];

    }

    public static void main(String[] args){

        // no need to sort the array, we are checking just the element
        int[] values = {1, 2, 3, 4, 5};
        int[] weights = {2, 3, 5, 6, 1};

        int capacity = 10;
        int index = values.length;

        int maxProfit = unboundedKnapsack_TopDown(values, weights, capacity, index);

        System.out.println("Maximum Profit: " +maxProfit);

    }
}