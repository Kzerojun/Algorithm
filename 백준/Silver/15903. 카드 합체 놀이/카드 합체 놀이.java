import java.util.*;
import java.io.*;
public class Main {

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            pq.offer(Long.parseLong(st.nextToken()));
        }

        for(int i = 0; i<k; i++){
            long a = pq.poll();
            long b = pq.poll();
            long sum = a+b;
            pq.offer(sum);
            pq.offer(sum);
        }
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += pq.poll();
        }

        System.out.println(result);
        
    }
}
