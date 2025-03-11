import java.util.*;

public class StockSpan {

    private static ArrayList<Integer> stockSpan(int[] arr) {
        int n = arr.length;
        Stack<Integer> stk = new Stack<>(); // Stack to store indices of elements
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // Remove smaller or equal elements from the stack
            while (!stk.isEmpty() && arr[stk.peek()] <= arr[i]) {
                stk.pop();
            }

            // If stack is empty, span is (i + 1), else (i - stk.peek())
            if (stk.isEmpty()) {
                result.add(i + 1);
            } else {
                result.add(i - stk.peek());
            }

            stk.push(i); // Push current index to stack
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {10, 4, 5, 90, 120, 80};

        ArrayList<Integer> result = stockSpan(arr);
        System.out.println(result); // Print the computed stock span values
    }
}
