import java.util.*;

public class Comparator{

    public static void main(String[] args){

        int[] firstArray = {3, 2, 1, 4, 5, 4};
        int[] secondArray = {30, 20, 10, 40, 50, 40};

        int n = firstArray.length;

        List<int[]> pairList = new ArrayList<>();

        // create a pair
        for(int i=0;i<n;i++){
            pairList.add(new int[]{firstArray[i], secondArray[i]});
        }

        // custom comarator to sort based on the 1st element of the array
        pairList.sort(java.util.Comparator.comparingInt(a -> a[0]));

        // expand the pair
        for(int i=0;i<n; i++){
            firstArray[i] = pairList.get(i)[0];
            secondArray[i] = pairList.get(i)[1];
        }

        System.out.println("Sorted Array 1: "+ Arrays.toString(firstArray));
        System.out.println("Sorted Array 2: " + Arrays.toString(secondArray));

    }
}