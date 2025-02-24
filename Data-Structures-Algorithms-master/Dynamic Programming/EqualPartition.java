import java.util.Arrays;
public class EqualPartition{
    public static void main(String[] args){

        int[] arr = {1, 2,  4, 6};
        boolean result = equalSumPartition(arr);

        System.out.println("Is equal sum partition possible: "+ result);
    }

    private static boolean equalSumPartition(int[] arr){
        int sum = Arrays.stream(arr).sum();
        if(sum %2 != 0) return false;
        else return subsetProblem(arr, arr.length, sum/2);
    }

    private static boolean subsetProblem(int[] arr,int n, int target){

        boolean[][] dp = new boolean[n+1][target+1];

        for(int i=0;i<n+1;i++){
            dp[i][0] = false;
        }
        for(int i=0;i<n+1;i++){
            dp[i][0] = true;
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=target;j++){
                if(arr[i-1]>j) dp[i][j] = dp[i-1][j];

                else{
                    boolean include = dp[i-1][j-arr[i-1]];
                    boolean exclude = dp[i-1][j];

                    dp[i][j] = include || exclude;
                }
            }
        }


        return dp[n][target];

    }
}