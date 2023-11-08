import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, Q;
    static int[][] graph;
    static int size;
    static int remainsIce = 0;
    static int iceDump = 0;
    static int[] dx = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    static int[] dy = {0, 1, 0, -1}; // 상, 우, 하, 좌
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        size = (int) Math.pow(2, N);

        visit = new boolean[size][size];
        graph = new int[size][size];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Integer> L = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            L.add(Integer.parseInt(st.nextToken()));
        }

        areaDivide(L);


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j ++) {
                if(!visit[i][j]&&graph[i][j]>0){
                    int size = iceDumpCount(new PointIce(i,j));
                    iceDump = Math.max(iceDump, size);
                }
            }
        }
        remainIceCount();
        System.out.println(remainsIce);
        System.out.println(iceDump);

    }

    static void areaDivide(List<Integer> L) {
        for (int lSize : L) {
            int divideSize = (int) Math.pow(2, lSize);

            for (int i = 0; i < size; i += divideSize) {
                for (int j = 0; j < size; j += divideSize) {
                    rotate(i, j, divideSize);
                }
            }

            // 회전 후 얼음의 양 조절
            int[][] tempGraph = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (nx < 0 || ny < 0 || nx >= size || ny >= size) continue;
                        if (graph[nx][ny] > 0) cnt++;
                    }


                    if (cnt < 3 && graph[i][j] > 0) tempGraph[i][j] = graph[i][j] - 1;
                    else tempGraph[i][j] = graph[i][j];
                }
            }

            graph = tempGraph;

        }
    }

    static void rotate(int i, int j, int divideSize) {
        int[][] replication = new int[divideSize][divideSize];

        for (int x = 0; x < divideSize; x++) {
            for (int y = 0; y < divideSize; y++) {
                replication[y][divideSize - 1 - x] = graph[i + x][j + y];
            }
        }

        for (int x = 0; x < divideSize; x++) {
            for (int y = 0; y < divideSize; y++) {
                graph[i + x][j + y] = replication[x][y];
            }
        }
    }

    static void remainIceCount() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                remainsIce += graph[i][j];
            }
        }
    }

    static int iceDumpCount(PointIce pointIce) {
        Queue<PointIce> q = new LinkedList<>();
        q.offer(pointIce);
        visit[pointIce.x][pointIce.y] = true;
        int count = 1;
        while (!q.isEmpty()) {
            PointIce current = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if(nx <0|| ny<0 ||nx>=size || ny>=size) continue;

                if(!visit[nx][ny]){
                    if(graph[nx][ny] !=0) {
                        q.offer(new PointIce(nx, ny));
                        visit[nx][ny] = true;
                        count += 1;
                    }
                }
            }
        }

        return count;
    }


}

class PointIce {
    int x;
    int y;

    PointIce(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
