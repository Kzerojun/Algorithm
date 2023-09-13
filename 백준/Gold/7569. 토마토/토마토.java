import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H;
    static int[][][] tomato;
    static boolean[][][] visit;
    static int dx[] = {0, 0, -1, 1, 0, 0}; // 상, 하, 좌, 우, 위, 아래
    static int dy[] = {-1, 1, 0, 0, 0, 0};
    static int dz[] = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tomato = new int[H][N][M];
        visit = new boolean[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    tomato[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int result = bfs();

        System.out.println(result);
    }

    static int bfs() {
        Queue<WAY3> queue = new LinkedList<>();
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tomato[k][i][j] == 1) {
                        queue.offer(new WAY3(i, j, k, 0));
                    }
                }
            }
        }

        int days = 0;

        while (!queue.isEmpty()) {
            WAY3 tmp = queue.poll();

            for (int j = 0; j < 6; j++) {
                int ddx = tmp.x + dx[j];
                int ddy = tmp.y + dy[j];
                int ddz = tmp.z + dz[j];
                if (ddx >= 0 && ddy >= 0 && ddx < N && ddy < M && ddz >= 0 && ddz < H) {
                    if (!visit[ddz][ddx][ddy] && tomato[ddz][ddx][ddy] == 0) {
                        visit[ddz][ddx][ddy] = true;
                        queue.offer(new WAY3(ddx, ddy, ddz, tmp.days + 1));
                        days = tmp.days + 1; // 이동한 일수를 업데이트
                    }
                }
            }
        }

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tomato[k][i][j] == 0 && !visit[k][i][j]) {
                        return -1;
                    }
                }
            }
        }

        return days;
    }
}

class WAY3 {
    int x;
    int y;
    int z;
    int days;

    WAY3(int x, int y, int z, int days) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.days = days;
    }
}