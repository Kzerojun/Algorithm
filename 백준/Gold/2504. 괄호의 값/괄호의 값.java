
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String formula = br.readLine();

        System.out.println(calculate(formula));
    }

    private static int calculate(String formula) {
        Stack<Integer> stack = new Stack<>();
        int currentValue = 0;
        int temp = 1;

        for (int i = 0; i < formula.length(); i++) {
            char ch = formula.charAt(i);

            if (ch == '(') {
                stack.push(-1);
                temp *= 2;
            } else if (ch == '[') {
                stack.push(-2);
                temp *= 3;
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.peek() != -1) {
                    return 0;
                }
                if (formula.charAt(i - 1) == '(') {
                    currentValue += temp;
                }
                stack.pop();
                temp /= 2;
            } else if (ch == ']') {
                if (stack.isEmpty() || stack.peek() != -2) {
                    return 0;
                }
                if (formula.charAt(i - 1) == '[') {
                    currentValue += temp;
                }
                stack.pop();
                temp /= 3;
            } else {
                return 0;
            }
        }

        if (!stack.isEmpty()) {
            return 0;
        }
        return currentValue;
    }
}
