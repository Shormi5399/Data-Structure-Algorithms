import java.util.Stack;
public class MinStackExtraSpace{

    // functions of a stack
    //push
    //pop
    //peek
    //minValue

    public static Stack<Integer> stk = new Stack<>();
    public static Stack<Integer> minStk = new Stack<>();

    public static void push(int x){
        if(stk.isEmpty()){
            stk.push(x);
            System.out.println("Push stk size: "+stk.size());
            minStk.push(x);
            System.out.println("Push min stk size: "+minStk.size());
        }

        else if(x < minStk.peek()){
            minStk.push(x);
            stk.push(x);
            System.out.println("Push min stk size: "+minStk.size());
            System.out.println("Push stk size: "+stk.size());
        }

        else stk.push(x);
    }

    public static int pop(){
        if(stk.isEmpty()) return -1;

        System.out.println(stk.size());

        if(stk.peek() == minStk.peek()){
            System.out.println("min stk pop: "+minStk.pop());
            System.out.println("min stk size: "+minStk.size());
            int k = stk.pop();
            System.out.println("Stk pop: "+ k);
            System.out.println("Stk size: "+stk.size());
            return k;
        }

        return stk.pop();
    }

    public static int peek(){
        if(stk.isEmpty()) return -1;

        return minStk.peek();
    }

    public static int minValue(){
        if(minStk.isEmpty()) return -1;

        return minStk.peek();
    }

     public static void main(String[] args){

         System.out.println("Min Stack");

         minValue();
         push(7);
         push(5);
         push(3);
         System.out.println(minValue());
         System.out.println(pop());
         System.out.println(minValue());
         System.out.println(peek());
         System.out.println(minValue());
         System.out.println(pop());
         System.out.println(minValue());
         System.out.println(pop());


     }
}