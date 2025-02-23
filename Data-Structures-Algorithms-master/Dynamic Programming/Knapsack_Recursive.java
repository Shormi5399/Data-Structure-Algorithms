public class Knapsack_Recursive {
    public static int knapsack(int[] weights, int[] values, int capacity, int index) {
        // Base case: If no items left or capacity is zero
        if (index == 0 || capacity == 0) {
            return 0;
        }

        // If the weight of the current item is greater than the remaining capacity, skip it
        if (weights[index - 1] > capacity) {
            return knapsack(weights, values, capacity, index - 1);
        }

        // Two choices: Include or Exclude the current item
        int include = values[index - 1] + knapsack(weights, values, capacity - weights[index - 1], index - 1);
        int exclude = knapsack(weights, values, capacity, index - 1);

        // Return the maximum value obtained
        return Math.max(include, exclude);
    }

    public static void main(String[] args) {
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int capacity = 5;
        int n = weights.length;

        int maxProfit = knapsack(weights, values, capacity, n);
        System.out.println("Maximum profit: " + maxProfit);
    }
}
