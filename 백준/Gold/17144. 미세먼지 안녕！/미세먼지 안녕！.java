import java.io.*;
import java.util.*;

class Main {
    static int N, M, T;
    static int[][] graph;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<Location> airCleaner;
    static int[][] tmp;  // 미세먼지 확산을 위한 임시 배열

    public static void main(String[] args) throws IOException {
        init();
        play();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // R, C, T 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        airCleaner = new LinkedList<>();

        // 그래프 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());

                // 공기청정기 위치 기억하기
                if (graph[i][j] == -1) {
                    airCleaner.add(new Location(i, j));
                }
            }
        }
    }

    private static void play() {
        for (int i = 0; i < T; i++) {
            spreadDust();
            airClean();
        }

        printSol();
    }

    // 미세먼지 확산 기능
    private static void spreadDust() {
        tmp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] > 0) {  // 미세먼지가 있는 곳에서만 확산
                    spread(i, j);
                }
            }
        }

        // 임시 배열 tmp에 저장된 확산된 미세먼지 양을 원래 배열 graph에 반영
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                graph[i][j] += tmp[i][j];
            }
        }

        // 공기청정기 위치는 -1로 다시 설정
        for (Location location : airCleaner) {
            graph[location.x][location.y] = -1;
        }
    }

    // 미세먼지 확산 로직
    private static void spread(int x, int y) {
        int spreadAmount = graph[x][y] / 5;
        int spreadCount = 0;

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            // 범위 밖이거나 공기청정기 위치면 넘어감
            if (nx < 0 || ny < 0 || nx >= N || ny >= M || graph[nx][ny] == -1) continue;

            // 확산될 수 있는 곳에 미세먼지 양을 더해줌
            tmp[nx][ny] += spreadAmount;
            spreadCount++;
        }

        // 현재 위치의 미세먼지 양 감소
        tmp[x][y] -= spreadAmount * spreadCount;
    }

    // 공기청정기 작동
    private static void airClean() {
        // 위쪽 공기 청정기
        int upperX = airCleaner.get(0).x;

        // 시계 반대 방향 순환
        for (int i = upperX - 1; i > 0; i--) graph[i][0] = graph[i - 1][0];  // 왼쪽 세로 줄
        for (int i = 0; i < M - 1; i++) graph[0][i] = graph[0][i + 1];        // 위쪽 가로 줄
        for (int i = 0; i < upperX; i++) graph[i][M - 1] = graph[i + 1][M - 1];  // 오른쪽 세로 줄
        for (int i = M - 1; i > 1; i--) graph[upperX][i] = graph[upperX][i - 1];  // 아래쪽 가로 줄
        graph[upperX][1] = 0;  // 공기청정기에서 나오는 공기는 0으로 설정

        // 아래쪽 공기 청정기
        int lowerX = airCleaner.get(1).x;

        // 시계 방향 순환
        for (int i = lowerX + 1; i < N - 1; i++) graph[i][0] = graph[i + 1][0];  // 왼쪽 세로 줄
        for (int i = 0; i < M - 1; i++) graph[N - 1][i] = graph[N - 1][i + 1];  // 아래쪽 가로 줄
        for (int i = N - 1; i > lowerX; i--) graph[i][M - 1] = graph[i - 1][M - 1];  // 오른쪽 세로 줄
        for (int i = M - 1; i > 1; i--) graph[lowerX][i] = graph[lowerX][i - 1];  // 위쪽 가로 줄
        graph[lowerX][1] = 0;  // 공기청정기에서 나오는 공기는 0으로 설정
    }

    // 결과 출력
    private static void printSol() {
        int sol = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] != -1) {
                    sol += graph[i][j];
                }
            }
        }

        System.out.println(sol);
    }
}

class Location {
    int x, y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
