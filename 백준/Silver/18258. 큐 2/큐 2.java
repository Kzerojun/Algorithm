import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Deque<Integer> queue = new LinkedList<>();
        for(int i = 0; i<n; i ++){
            String[] words = br.readLine().split(" ");

            switch(words[0]){

                case "push" : {
                    int x = Integer.parseInt(words[1]);
                    queue.offer(x);
                    break;
                }
                case "pop" :{
                    if(queue.isEmpty()){
                        bw.write(-1 +"\n");
                    }
                    else bw.write(queue.poll()+"\n");
                    break;
                }
                case "size" : {
                    bw.write(queue.size()+"\n");
                    break;
                }
                case "empty":{
                    if(queue.isEmpty()) bw.write(1+"\n");
                    else bw.write(0+"\n");
                    break;
                }
                case "front" :{
                    if(queue.isEmpty()){
                        bw.write(-1+"\n");
                    }
                    else bw.write(queue.peek()+"\n");
                    break;
                }
                case "back":{ //Deque 기능
                    if(queue.isEmpty()){
                        bw.write(-1+"\n");
                    }
                    else bw.write(queue.peekLast()+"\n");
                    break;
                }

            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
