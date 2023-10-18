import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] graph;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1}; //우,좌,하,상

    static int maxBlock = 0;
    public static void main(String[]args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        graph = new int[N][N];

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(maxBlock);
    }

    static void dfs(int count){
        if(count == 5){
            maxBlock = Math.max(maxBlock, getMaxBlock());
            return;
        }
        int[][] originGraph = new int[N][N];
        copyGraph(originGraph, graph);

        for(int i = 0; i < 4; i++){
            graphSum(i);
            dfs(count+1);
            copyGraph(graph, originGraph);
        }

    }

    static void graphSum(int direction) {

        boolean[][] merged = new boolean[N][N];

        if (direction == 0) { // Right
            for (int i = N - 1; i >= 0; i--) {
                for (int j = N - 2; j >= 0; j--) {
                    if (graph[i][j] == 0) continue;

                    int k = j + 1;
                    while (k < N && graph[i][k] == 0) {
                        k++;
                    }

                    if (k < N && !merged[i][k] && (graph[i][j] == graph[i][k])) {
                        graph[i][k] *= 2;
                        graph[i][j] = 0;
                        merged[i][k] = true;
                    } else if (graph[i][k - 1] == 0) {
                        graph[i][k - 1] = graph[i][j];
                        graph[i][j] = 0;
                    }
                }
            }
        } else if (direction == 1) { // Left
            for (int i = 0; i < N; i++) {
                for (int j = 1; j < N; j++) {
                    if (graph[i][j] == 0) continue;

                    int k = j - 1;
                    while (k >= 0 && graph[i][k] == 0) {
                        k--;
                    }

                    if (k >= 0 && !merged[i][k] && (graph[i][j] == graph[i][k])) {
                        graph[i][k] *= 2;
                        graph[i][j] = 0;
                        merged[i][k] = true;
                    } else if (graph[i][k + 1] == 0) {
                        graph[i][k + 1] = graph[i][j];
                        graph[i][j] = 0;
                    }
                }
            }
        } else if (direction == 2) { // Up
            for (int i = 1; i < N; i++) {
                for (int j = N - 1; j >= 0; j--) {
                    if (graph[i][j] == 0) continue;

                    int k = i - 1;
                    while (k >= 0 && graph[k][j] == 0) {
                        k--;
                    }

                    if (k >= 0 && !merged[k][j] && (graph[i][j] == graph[k][j])) {
                        graph[k][j] *= 2;
                        graph[i][j] = 0;
                        merged[k][j] = true;
                    } else if (graph[k + 1][j] == 0) {
                        graph[k + 1][j] = graph[i][j];
                        graph[i][j] = 0;
                    }
                }
            }
        } else if (direction == 3) { // Down
            for (int i = N - 2; i >= 0; i--) {
                for (int j = 0; j < N; j++) {
                    if (graph[i][j] == 0) continue;

                    int k = i + 1;
                    while (k < N && graph[k][j] == 0) {
                        k++;
                    }

                    if (k < N && !merged[k][j] && (graph[i][j] == graph[k][j])) {
                        graph[k][j] *= 2;
                        graph[i][j] = 0;
                        merged[k][j] = true;
                    } else if (graph[k - 1][j] == 0) {
                        graph[k - 1][j] = graph[i][j];
                        graph[i][j] = 0;
                    }
                }
            }
        }
    }


    static void copyGraph(int[][] destination, int[][] source) {
        for (int i = 0; i < N; i++) {
            System.arraycopy(source[i], 0, destination[i], 0, N);
        }
    }

    static int getMaxBlock() {
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, graph[i][j]);
            }
        }
        return max;
    }

   
}
