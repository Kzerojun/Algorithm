import javax.sound.sampled.Line;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
        public static void main(String[]args)throws IOException{

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int N = Integer.parseInt(br.readLine());
            Queue<Integer> card = new LinkedList<>();
            for(int i = 1; i<=N; i++){
                card.offer(i);
            }
            int count = 1;
            while(card.size()!=1){
                if(count%2!=0){
                    card.poll();
                    count++;
                }
                else {
                    card.offer(card.peek());
                    card.poll();
                    count++;
                }

            }


            bw.write(card.poll()+"");
            br.close();
            bw.flush();
            bw.close();
        }
}
