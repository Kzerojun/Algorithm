import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[][] visited;
    static int[][] walls;
    static int[] dx = {0, -1, 0, 1}; // 서, 북, 동, 남
    static int[] dy = {-1, 0, 1, 0};
    static int maxRoomSize = 0;
    static int breakWallMaxRoomSize = 0;

    public static void main(String[] args) throws IOException {
        init();
        simulate();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        walls = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                walls[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void simulate() {
        // 방의 개수와 최대 방 크기 계산
        int roomCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    roomCount++;
                    maxRoomSize = Math.max(maxRoomSize, move(i, j));
                }
            }
        }

        // 하나의 벽 제거 후 최대 방 크기 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 4; k++) { // 서, 북, 동, 남 순서로 벽 제거
                    if ((walls[i][j] & (1 << k)) != 0) { // 해당 방향에 벽이 있으면
                        walls[i][j] -= (1 << k); // 벽 제거
                        resetVisited();
                        breakWallMaxRoomSize = Math.max(breakWallMaxRoomSize, move(i, j));
                        walls[i][j] += (1 << k); // 원래 상태로 복구
                    }
                }
            }
        }

        System.out.println(roomCount);
        System.out.println(maxRoomSize);
        System.out.println(breakWallMaxRoomSize);
    }

    private static int move(int row, int col) {
        Queue<Location> q = new LinkedList<>();
        visited[row][col] = true;
        q.add(new Location(row, col));
        int roomSize = 0;

        while (!q.isEmpty()) {
            Location now = q.poll();
            roomSize++;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
                    continue;
                }

                if ((walls[now.x][now.y] & (1 << i)) == 0) { // 벽이 없으면 이동 가능
                    visited[nx][ny] = true;
                    q.add(new Location(nx, ny));
                }
            }
        }

        return roomSize;
    }

    private static void resetVisited() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = false;
            }
        }
    }
}

class Location {
    int x, y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
