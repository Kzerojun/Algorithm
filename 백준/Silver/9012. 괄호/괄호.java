import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        

        for(int i=0; i<N; i++) {
            String vps = br.readLine();
            Stack<Character> stack = new Stack<>();

            for(int j=0; j<vps.length(); j++) {
                char ch = vps.charAt(j);

                if(ch == '(') {
                    stack.push(ch);
                }
                else {
                    int size = stack.size();
                    if(size == 0) {
                        stack.push(ch);
                        break;
                    }
                    else {
                        stack.pop();
                    }
                }
            }

            if(stack.isEmpty()) {
                bw.write("YES"+"\n");
            }
            else {
                bw.write("NO"+"\n");
            }

            
        }

        br.close();
        bw.flush();
        bw.close();
    }
}