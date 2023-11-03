import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] graph;
    static int totalPeople = 0;
    static int min = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        graph = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                totalPeople += graph[i][j];
            }
        }

        SetNumber();
        System.out.println(min);
    }

    static void SetNumber() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (x + d1 + d2 >= N) {
                            continue;
                        }
                        if (y - d1 < 0 || y + d2 >= N) {
                            continue;
                        }
                        solution(x, y, d1, d2);

                    }
                }
            }
        }

    }

    static void solution(int x , int y, int d1, int d2){
        boolean[][] board = new boolean[N][N];

        for(int i = 0; i<=d1; i++){
            board[x+i][y-i] =true;
            board[x+d2+i][y+d2-i]  = true;
        }

        for(int i = 0; i<=d2; i++){
            board[x+i][y+i] = true;
            board[x+d1+i][y-d1+i] = true;
        }

        int[] peopleSum = new int[5];

        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (board[i][j]) break;
                peopleSum[0] += graph[i][j];
            }
        }

        for (int i = 0; i <= x + d2; i++) {
            for (int j = N - 1; j > y; j--) {
                if (board[i][j]) break;
                peopleSum[1] += graph[i][j];
            }
        }

        for(int i = x+ d1; i<N; i++){
            for(int j = 0; j<y-d1+d2; j++){
                if(board[i][j]) break;
                peopleSum[2] += graph[i][j];
            }
        }

        for (int i = x + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= y - d1 + d2; j--) {
                if (board[i][j]) break;
                peopleSum[3] += graph[i][j];
            }
        }

        peopleSum[4] = totalPeople;

        for (int i = 0; i < 4; i++) {
            peopleSum[4] -= peopleSum[i];
        }

        Arrays.sort(peopleSum);

        min= Math.min(min,peopleSum[4] - peopleSum[0]);


    }
}
