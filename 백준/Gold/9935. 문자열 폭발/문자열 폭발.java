import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String string = br.readLine();
        String bomb = br.readLine();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < string.length(); i++) {
            stack.push(string.charAt(i));

            if (stack.size() >= bomb.length()) {
                boolean isBomb = true;

                for (int j = 0; j < bomb.length(); j++) {
                    if (stack.get(stack.size() - bomb.length() + j) != bomb.charAt(j)) {
                        isBomb = false;
                        break;
                    }
                }

                if (isBomb) {
                    for (int j = 0; j < bomb.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (Character c : stack) {
            result.append(c);
        }

        if (result.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(result.toString());
        }
    }
}
