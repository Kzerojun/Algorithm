import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int sum = 0;
        Stack<Character> stack = new Stack<>();

        while(N>0){
            int remind = N%B;
            if(remind<10){
                stack.push((char)(remind+'0'));
            }
            else{
                stack.push((char)(remind-10+'A'));
            }
            N/=B;
        }
        StringBuilder sb = new StringBuilder();

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        System.out.println(sb);

    }
}