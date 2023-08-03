import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Integer> stack = new Stack<>();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int order = 1;

        for(int i = 0; i< N; i++){
            int current = Integer.parseInt(st.nextToken());
            stack.push(current);
            while (!stack.isEmpty() && stack.peek() == order) {
                stack.pop();
                order++;
            }
        }

        if (stack.isEmpty()) {
            bw.write("Nice");
        } else {
            bw.write("Sad");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}