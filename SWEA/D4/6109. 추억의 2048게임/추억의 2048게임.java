import java.util.*;
import java.io.*;

public class Solution {
    static BufferedReader br;
    static int T;
    static int N;
    static String command;
    static int[][] graph;

    // 좌, 우, 상, 하
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        init();
        start();
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
    }

    private static void start() throws IOException {
        for (int t = 1; t <= T; t++) {
            initGame();
            calculate();
            printResult(t);
        }
    }

    private static void initGame() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        command = st.nextToken();
        graph = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void calculate() {
        if (command.equals("left")) {
            move(0);
        } else if (command.equals("right")) {
            move(1);
        } else if (command.equals("up")) {
            move(2);
        } else if (command.equals("down")) {
            move(3);
        }
    }

    private static void move(int direction) {
        boolean[][] isMerge = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int x = i, y = j;
                if (direction == 1) y = N - 1 - j; // 우
                if (direction == 3) x = N - 1 - i; // 아래

                if (graph[x][y] == 0) continue;

                int nx = x + dx[direction];
                int ny = y + dy[direction];

                while (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (graph[nx][ny] == 0) {
                        graph[nx][ny] = graph[x][y];
                        graph[x][y] = 0;
                        x = nx;
                        y = ny;
                        nx += dx[direction];
                        ny += dy[direction];
                    } else if (graph[nx][ny] == graph[x][y] && !isMerge[nx][ny]) {
                        graph[nx][ny] *= 2;
                        graph[x][y] = 0;
                        isMerge[nx][ny] = true;
                        break;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private static void printResult(int t) {
        System.out.println("#" + t);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
}
