public class SubsetProblem{
    public static void main(String[] args){

        int[] arr = {1, 2, 3, 4, 5, 7};
        int n = arr.length;
        int target = 17;

        boolean result = subset(arr, n, target);

        System.out.println("Is subset with given target possible: "+ result );
    }

    private static boolean subset(int[] arr, int n, int target){

        boolean[][] dp = new boolean[n+1][target+1];

        // Base case:
        // 1. 0 elements give, but need a target
        // unnessary since java initailised will 0 by default
        for(int i=0;i<=target;i++){
            dp[0][i] = false;
        }
        // 2. elements given but need 0 target
        for(int i=0;i<=n;i++){
            dp[i][0] = true;
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=target;j++){
                // knapsack: cannot be inluded
                if(arr[i-1] > j) dp[i][j] = dp[i-1][j];

                else {
                    // knapack: choice to inlcude or exclude
                    boolean include = dp[i - 1][j] ;
                    boolean exclude  = dp[i - 1][j - arr[i - 1]];
                    dp[i][j] = include || exclude ;
                }
            }
        }


        return dp[n][target];
    }
}