import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static int[][] sol;
    public static void main(String[]args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        graph = new int[N][N];
        sol = new int[N][N];
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        //k는 거쳐가는 노드
        for(int k = 0; k<N; k++){
            //i는 출발노드
            for(int i = 0; i<N; i++){
                //j는 도착노드
                for(int j = 0; j<N; j++){
                    if(graph[i][k]==1 &&graph[k][j]==1){
                        graph[i][j] = 1;
                    }
                }
            }
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                System.out.print(graph[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

    }

}
