import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> stack = new Stack<>();
        String input;

        while (!(input = br.readLine()).equals(".")) {
            stack.clear();
            boolean isValid = true;

            for (char ch : input.toCharArray()) {
                if (!isValid) break;

                switch (ch) {
                    case '(':
                    case '[':
                        stack.push(ch);
                        break;
                    case ')':
                        if (stack.isEmpty() || stack.peek() != '(') {
                            isValid = false;
                            break;
                        }
                        stack.pop();
                        break;
                    case ']':
                        if (stack.isEmpty() || stack.peek() != '[') {
                            isValid = false;
                            break;
                        }
                        stack.pop();
                        break;
                }
            }
            if (stack.isEmpty() && isValid) {
                bw.write("yes\n");
            } else {
                bw.write("no\n");
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }
}