public class CountSubsetWithGivenSum{
    private static int countSubsetWithGivenSum(int[] arr, int n, int target){

        // base case:
        int[][] dp = new int[n+1][target+1];

        // Initialize base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1; // There is always one way to form sum 0 (empty subset)
        }

        for(int i=1;i<n+1;i++){
            for(int j=0;j<target+1;j++){
                if(arr[i-1]> j ) {
                    dp[i][j] = dp[i-1][j];
                }
                // choices: include to get teh target sum, exclude to get the target sum
                else if(arr[i-1]<=j){
                    int include = dp[i-1][j-arr[i-1]];
                    int exclude = dp[i-1][j];

                    dp[i][j] = include + exclude;
                }

            }
        }

        return dp[n][target];
    }
   public static void main(String[] args){
       int[] arr = {1, 2, 3 ,4, 5};

       int n = arr.length;
       int target = 7;

       int count = countSubsetWithGivenSum(arr, n,  target);

       System.out.println("Total count of subset with value: "+ target + " is "+ count);

   }
}