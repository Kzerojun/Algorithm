import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] A, map;
    static List<Integer>[][] tree;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        A = new int[N + 1][N + 1];
        tree = new ArrayList[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(map[i], 5);
            tree[i] = new ArrayList[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                tree[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            tree[x][y].add(age);
        }

        for (int k = 0; k < K; k++) {
            springAndSummer();
            fall();
            winter();
        }

        System.out.println(count());
    }

    static void springAndSummer() {
        int[][] temp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Collections.sort(tree[i][j]);

                List<Integer> alive = new ArrayList<>();
                int dead = 0;

                for (int age : tree[i][j]) {
                    if (age <= map[i][j]) {
                        map[i][j] -= age;
                        alive.add(age + 1);
                    } else {
                        dead += age / 2;
                    }
                }

                map[i][j] += dead;
                tree[i][j] = alive;
                temp[i][j] = tree[i][j].size();
            }
        }
    }

    static void fall() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int age : tree[i][j]) {
                    if (age % 5 == 0) {
                        for (int d = 0; d < 8; d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];
                            if (nx > 0 && ny > 0 && nx <= N && ny <= N)
                                tree[nx][ny].add(1);
                        }
                    }
                }
            }
        }
    }

    static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] += A[i][j];
            }
        }
    }

    static int count() {
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                cnt += tree[i][j].size();
            }
        }
        return cnt;
    }
}
