import java.io.*;
import java.util.*;

public class Main{
    static final int INF = 100000000;
    static int N,M,W;
    static int[][] road, wormhole;
    static int[] dist;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for(int tc=0;tc<TC;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            road = new int[M][3];
            wormhole = new int[W][3];
            dist = new int[N+1];
            Arrays.fill(dist, INF);
            dist[1] = 0;

            for(int i=0;i<M;i++){
                st = new StringTokenizer(br.readLine());
                road[i][0] = Integer.parseInt(st.nextToken());
                road[i][1] = Integer.parseInt(st.nextToken());
                road[i][2] = Integer.parseInt(st.nextToken());
            }

            for(int i=0;i<W;i++){
                st = new StringTokenizer(br.readLine());
                wormhole[i][0] = Integer.parseInt(st.nextToken());
                wormhole[i][1] = Integer.parseInt(st.nextToken());
                wormhole[i][2] = Integer.parseInt(st.nextToken());
            }

            if(bellmanFord()) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    static boolean bellmanFord(){
        for(int i=1;i<=N;i++){
            for(int j=0;j<M;j++){
                if(dist[road[j][1]] > dist[road[j][0]] + road[j][2]){
                    dist[road[j][1]] = dist[road[j][0]] + road[j][2];
                    if(i==N) return true;
                }
                if(dist[road[j][0]] > dist[road[j][1]] + road[j][2]){
                    dist[road[j][0]] = dist[road[j][1]] + road[j][2];
                    if(i==N) return true;
                }
            }

            for(int j=0;j<W;j++){
                if(dist[wormhole[j][1]] > dist[wormhole[j][0]] - wormhole[j][2]){
                    dist[wormhole[j][1]] = dist[wormhole[j][0]] - wormhole[j][2];
                    if(i==N) return true;
                }
            }
        }
        return false;
    }
}
