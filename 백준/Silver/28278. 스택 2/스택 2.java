import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Integer> stack = new Stack<>();
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i<N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if(command == 1){
                int num = Integer.parseInt(st.nextToken());
                stack.push(num);
            }
            if(command == 2){
                if(stack.isEmpty()){
                    bw.write(-1+"\n");
                }
                else bw.write(stack.pop()+"\n");
            }
            if(command ==3){
                bw.write(stack.size()+"\n");
            }
            if(command == 4){
                if(stack.isEmpty()){
                    bw.write(1+"\n");
                }
                else bw.write(0+"\n");
            }
            if(command ==5){
                if(stack.isEmpty()){
                    bw.write(-1+"\n");
                }
                else bw.write(stack.peek()+"\n");
            }
        }

        br.close();
        bw.flush();
        bw.close();




    }


}
