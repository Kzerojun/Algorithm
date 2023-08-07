import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Queue<Integer> queue = new LinkedList<>();
        int count = 1;
        int[] arr = new int[N];
        int number = 0;
        for(int i = 1; i<=N; i++){
            queue.offer(i);
        }

        while(N>0){

            if(count%K==0&&!queue.isEmpty()){
                arr[number] = queue.poll();
                number++;
                count++;
                N--;

            }
            else {
                queue.offer(queue.peek());
                queue.poll();
                count++;
            }
        }

        bw.write("<");
        for(int i = 0; i<arr.length-1; i++){
            bw.write(arr[i]+", ");
        }
        bw.write(arr[arr.length-1]+">");


        br.close();
        bw.flush();
        bw.close();
    }

}
