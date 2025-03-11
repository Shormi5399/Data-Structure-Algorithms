import java.util.Stack;
import java.util.Arrays;

public class MaxAreaHistogram {

    public static int maxHistogram(int[] heights) {
        int n = heights.length;
        int[] NSL = nearestSmallerLeft(heights);
        int[] NSR = nearestSmallerRight(heights);

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = NSR[i] - NSL[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    private static int[] nearestSmallerLeft(int[] heights) {
        int n = heights.length;
        int[] NSL = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            NSL[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return NSL;
    }

    private static int[] nearestSmallerRight(int[] heights) {
        int n = heights.length;
        int[] NSR = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            NSR[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        return NSR;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("Max Area in Histogram: " + maxHistogram(heights));
    }
}
