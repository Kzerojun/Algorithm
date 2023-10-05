import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int K;
    static boolean[] visit;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visit = new boolean[100_001];
        bfs(new Node13549(N,0));
        System.out.println(min);
    }

    static void bfs(Node13549 start) {
        Queue<Node13549> q = new LinkedList<>();
        q.offer(start);
        visit[start.x]= true;

        while(!q.isEmpty()){
            Node13549 current = q.poll();

            if(current.x == K){
                min = Math.min(min,current.time);
            }

            int[] next = {current.x*2,current.x-1,current.x+1};
            for(int i = 0; i<3; i++){
                if(next[i]>=0 && next[i]<=100_000 && !visit[next[i]]){
                    if(i==0) {
                        q.offer(new Node13549(next[i], current.time));
                        visit[next[i]] = true;
                    }
                    else{
                        q.offer(new Node13549(next[i],current.time+1));
                        visit[next[i]]=true;
                    }
                }
            }
        }
    }
}

class Node13549{
    int x;
    int time;
    Node13549(int x, int time){
        this.x = x;
        this.time = time;
    }

}