import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] graph = new int[N][N];

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++) {
                    char ch = input.charAt(j);
                    graph[i][j] = ch - '0';
                }
            }

            int solution = 0;
            int mid = N / 2;

            for (int i = 0; i <= mid; i++) {
                for (int j = mid - i; j <= mid + i; j++) {
                    solution += graph[i][j];
                    if (i != mid) {
                        solution += graph[N - 1 - i][j];
                    }
                }
            }

            System.out.printf("#%d %d\n", tc, solution);
        }
    }
}
