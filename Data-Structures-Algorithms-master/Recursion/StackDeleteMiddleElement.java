import java.util.*;
public class StackDeleteMiddleElement{

    private static Stack<Integer>  deleteMidElement(Stack<Integer> stk){
        int k = stk.size()/2;

        Stack<Integer> temp = new Stack<>();

        while( k>0 && !stk.isEmpty()){
            temp.push(stk.pop()); k--;
        }

        stk.pop();

        while(!temp.isEmpty()){
            stk.push(temp.pop());
        }
        return stk;
    }


    public static void main(String[] args){
        System.out.println("Stack: Delete middle element");

        Stack<Integer> stk = new Stack<>();
        stk.push(1);
        stk.push(2);
        stk.push(3);
        stk.push(4);
        stk.push(5);

        Stack<Integer> result = deleteMidElement(stk);

        System.out.println("Stack size "+ result.size());
        while(!result.isEmpty()){
            System.out.println(result.pop());
        }
    }
}