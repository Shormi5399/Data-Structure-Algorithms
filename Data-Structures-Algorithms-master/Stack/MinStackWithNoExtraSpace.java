import java.util.Stack;

class MinSatckWithNoExtraSpace {
    private Stack<Long> stack;
    private long minElement;


    //Push: O(1)
    //Pop: O(1)
    //Top: O(1)
    //GetMin: O(1)
    //IsEmpty: O(1)
    public MinSatckWithNoExtraSpace() {
        stack = new Stack<>();
    }

    public void push(int value) {
        if (stack.isEmpty()) {
            minElement = value;
            stack.push((long) value);
        } else if (value < minElement) {
            stack.push(2L * value - minElement);  // Encoded value
            minElement = value; // Update new minimum
        } else {
            stack.push((long) value);
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;

        long top = stack.pop();
        if (top < minElement) {
            // Restore previous minElement before update
            minElement = 2 * minElement - top;
        }
    }

    public int top() {
        if (stack.isEmpty()) return -1;

        long top = stack.peek();
        return (top < minElement) ? (int) minElement : (int) top;
    }

    public int getMin() {
        return (int) minElement;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}

public class MinStackTest {
    public static void main(String[] args) {
        MinSatckWithNoExtraSpace ms = new MinSatckWithNoExtraSpace();
        ms.push(5);
        ms.push(3);
        ms.push(7);
        ms.push(2);
        ms.push(8);

        System.out.println("Min: " + ms.getMin()); // 2
        ms.pop();
        System.out.println("Min: " + ms.getMin()); // 2
        ms.pop();
        System.out.println("Min: " + ms.getMin()); // 3
        ms.pop();
        System.out.println("Min: " + ms.getMin()); // 3
    }
}
