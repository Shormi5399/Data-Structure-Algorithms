public class TowerOfHanoi{

    private static void solveHanoi(int n, char src, char auxi, char dest){
        if( n == 1) {
            System.out.println("Move 1 disk from " + src + " to " + dest);
            return;
        }

        solveHanoi(n-1, src, auxi, dest );
        System.out.println("Move disk " + n + " from " + src + " to " + dest);
        solveHanoi(n-1, auxi, dest, src );
    }
    public static void main(String[] args){
        int n = 3;
        solveHanoi(3, 'a', 'b', 'c');
    }
}