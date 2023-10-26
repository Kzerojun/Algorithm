import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static int R, C;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0}; // 좌 상 우 하
    static List<cctv> cctvList = new ArrayList<>();
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int number = Integer.parseInt(st.nextToken());
                graph[i][j] = number;
                if (number >= 1 && number <= 5) {
                    cctvList.add(new cctv(i, j, number));
                }
            }
        }

        recursion(0, graph);
        System.out.println(result);
    }

    static void recursion(int cctvIndex, int[][] prevGraph) {
        int[][] copiedGraph = new int[R][C];

        if (cctvIndex == cctvList.size()) {
            int count = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (prevGraph[i][j] == 0) {
                        count++;
                    }
                }
            }
            result = Math.min(result, count);
            return;
        }

        cctv temp = cctvList.get(cctvIndex);
        int[][] dirs = {{0}, {0, 2}, {0, 1}, {0, 1, 2}, {0, 1, 2, 3}};

        for (int dir = 0; dir < 4; dir++) {


            for (int i = 0; i < R; i++) {
                copiedGraph[i] = prevGraph[i].clone();
            }

            for (int d : dirs[temp.number - 1]) {
                int nx = temp.x;
                int ny = temp.y;

                while (true) {
                    nx += dx[(dir + d) % 4];
                    ny += dy[(dir + d) % 4];

                    if (nx < 0 || nx >= R || ny < 0 || ny >= C || copiedGraph[nx][ny] == 6) break;

                    if (copiedGraph[nx][ny] == 0) {
                        copiedGraph[nx][ny] = 7;
                    }
                }
            }
            recursion(cctvIndex + 1, copiedGraph);
        }
    }
}

class cctv {
    int x;
    int y;
    int number;

    cctv(int x, int y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
    }
}
