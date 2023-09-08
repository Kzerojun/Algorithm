import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int time;

    static int[] dist;

    static int K;
    public static void main(String[]args)throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[100001];

        dist[N] = 0;
        bfs(N);




    }

    static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);


        while(!queue.isEmpty()){
            int current = queue.poll();

            if(current == K) {
                System.out.println(dist[current]);
                break;
            }

            int[] next = {current -1 , current+1, current*2};
            for(int i = 0; i<3 ; i++){
                if(next[i]>=0&& next[i]<=100000 && dist[next[i]]==0){
                    queue.offer(next[i]);
                    dist[next[i]]= dist[current]+1;
                }

            }
        }
    }




}
