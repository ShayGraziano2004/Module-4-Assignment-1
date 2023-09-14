import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class GroupingSymbolChecker {

    public static boolean checkGroupingSymbols(String fileName) throws IOException {
        Stack<Character> stack = new Stack<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        
        while ((line = reader.readLine()) != null) {
            for (char c : line.toCharArray()) {
                if (c == '(' || c == '{' || c == '[') {
                    stack.push(c);
                } else if (c == ')' || c == '}' || c == ']') {
                    if (stack.isEmpty()) {
                        reader.close();
                        return false; // Unmatched closing symbol
                    }
                    
                    char top = stack.pop();
                    if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')) {
                        reader.close();
                        return false; // Mismatched opening and closing symbols
                    }
                }
            }
        }
        
        reader.close();
        return stack.isEmpty(); // Check if all symbols were correctly paired
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java GroupingSymbolChecker <file_name>");
            return;
        }
        
        String fileName = args[0];
        
        try {
            boolean isCorrect = checkGroupingSymbols(fileName);
            if (isCorrect) {
                System.out.println("The file has correct grouping symbols.");
            } else {
                System.out.println("The file has incorrect or unmatched grouping symbols.");
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
