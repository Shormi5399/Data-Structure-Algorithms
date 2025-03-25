import java.util.*;

public class ArraySort {

    // Recursive function to sort an array and return a sorted list
    private static List<Integer> sort(int[] arr, int n) {
        List<Integer> sortedArray = new ArrayList<>();

        // Base case: if n is 0, return an empty list
        if (n == 0) return sortedArray;

        // Base case: if only one element, return a list containing it
        if (n == 1) {
            sortedArray.add(arr[0]);
            return sortedArray;
        }

        // Recursively sort the first (n-1) elements
        sortedArray = sort(arr, n - 1);

        // Insert the last element into the sorted array at the correct position
        insert(sortedArray, arr[n - 1]);

        return sortedArray;
    }

    // Function to insert an element into the sorted list
    private static void insert(List<Integer> sortedArray, int value) {
        Stack<Integer> stk = new Stack<>();
        int n = sortedArray.size() - 1;

        // Shift larger elements to stack
        while (n >= 0 && sortedArray.get(n) > value) {
            stk.push(sortedArray.remove(n));
            n--;
        }

        // Insert the value at the correct position
        sortedArray.add(value);

        // Restore the remaining elements from stack
        while (!stk.isEmpty()) {
            sortedArray.add(stk.pop());
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 1, 7, 9, 4};
        int n = arr.length;

        List<Integer> result = sort(arr, n);

        System.out.println(result); // Output: [1, 2, 3, 4, 5, 7, 9]
    }
}
