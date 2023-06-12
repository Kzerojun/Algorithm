import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Stack;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[] number = new char[N];
        number = br.readLine().toCharArray();
        Stack<Integer> stack = new Stack<>();
        int count = 0;

        for(int i = 0; i<N; i++){
                while(!stack.isEmpty() && stack.peek()< number[i] - '0' && count <K){
                    stack.pop();
                    count++;
                }
                stack.push(number[i]-'0');
        }
        while(count < K) {
            stack.pop();
            count++;
        }
        StringBuilder sb = new StringBuilder();
        for(int val : stack){
            sb.append(val);

        }
        System.out.println(sb);
    }
}