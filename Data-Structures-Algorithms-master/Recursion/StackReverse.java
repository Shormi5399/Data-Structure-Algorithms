import java.util.Stack;

public class StackReverse {

    // Function to reverse the stack using recursion
    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) return;

        // Remove the top element
        int top = stack.pop();

        // Recursively reverse the remaining stack
        reverseStack(stack);

        // Insert the top element at the bottom of the stack
        insertAtBottom(stack, top);
    }

    // Function to insert an element at the bottom of the stack
    private static void insertAtBottom(Stack<Integer> stack, int value) {
        if (stack.isEmpty()) {
            stack.push(value);
            return;
        }

        // Remove the top element
        int top = stack.pop();

        // Recursively insert value at the bottom
        insertAtBottom(stack, value);

        // Push the top element back after insertion
        stack.push(top);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println("Original Stack: " + stack); // Output: [1, 2, 3, 4, 5]

        reverseStack(stack);

        System.out.println("Reversed Stack: " + stack); // Output: [5, 4, 3, 2, 1]
    }
}
