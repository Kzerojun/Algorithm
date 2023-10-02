import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] sol;
    static int[][] graph;
    public static void main(String[]args)throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        sol = new int[N+1];
        graph = new int[N+1][N+1];

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

           graph[x][y] = graph[y][x] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i != j && graph[i][j] == 0) {
                    graph[i][j] = 1000001;
                }
            }
        }


        for(int k = 1; k<=N; k++){
            for(int i = 1; i<=N; i++){
                for(int j = 1; j<=N; j++){
                    if(graph[i][k]+graph[k][j] <graph[i][j] ){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        int minKevinBacon = Integer.MAX_VALUE;
        int answer = 0;

        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                sum += graph[i][j];
            }

            if (minKevinBacon > sum) {
                minKevinBacon = sum;
                answer = i;
            }
        }
        System.out.println(answer);

    }




}
