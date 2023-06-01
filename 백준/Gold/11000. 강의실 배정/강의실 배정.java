import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int [][] arr = new int [N][2];
        for(int i = 0; i< N; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        PriorityQueue<Integer> pq =  new PriorityQueue();

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[]o1, int[]o2){
                if(o1[0]==o2[0]) return o2[1] -o1[1];
                else return o1[0] - o2[0];
            }
        });
        pq.add(arr[0][1]);
        for(int i  = 1; i<N; i++){
            if(pq.peek()<=arr[i][0]) pq.poll();

            pq.add(arr[i][1]);
        }
        System.out.println(pq.size());

    }
}