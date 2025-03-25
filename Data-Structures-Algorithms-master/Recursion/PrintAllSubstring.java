import java.util.ArrayList;
import java.util.List;

public class PrintAllSubstring {

    public static void generateSubstrings(String str, int start, int end, List<String> result) {
        // base case, start reach end
        if (start == str.length()) {
            return;
        }
        if (end > str.length()) {
            // move start to next char
            generateSubstrings(str, start + 1, start + 1, result);
        } else {
            // add subsstirng start to end-1
            result.add(str.substring(start, end));
            // expand the substring range
            generateSubstrings(str, start, end + 1, result);
        }
    }

    // wrapper method to initailise the list and start recursion
    public static List<String> getAllSubstrings(String str) {
        List<String> result = new ArrayList<>();
        generateSubstrings(str, 0, 1, result);
        return result;
    }

    public static void main(String[] args) {
        String input = "hello";
        List<String> substrings = getAllSubstrings(input);
        System.out.println(substrings);  // i can directly print a collection
    }
}
