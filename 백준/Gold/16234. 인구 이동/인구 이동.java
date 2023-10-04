import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Country {
    int x, y;

    public Country(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, L, R;
    static int[][] population;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        population = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                population[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int days = 0;

        // 인구 이동이 더 이상 일어나지 않을 때까지 반복
        while (movePopulation()) {
            days++;
            visited = new boolean[N][N];
        }

        System.out.println(days);
    }

    // 인구 이동이 일어나는지 확인하고 이동이 있으면 실행
    static boolean movePopulation() {
        boolean moved = false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    if (bfs(new Country(i, j))) {
                        moved = true;
                    }
                }
            }
        }

        return moved;
    }

    // BFS로 연합 형성
    static boolean bfs(Country start) {
        Queue<Country> queue = new LinkedList<>();
        Queue<Country> union = new LinkedList<>();

        queue.offer(start);
        union.offer(start);
        visited[start.x][start.y] = true;

        int totalPopulation = population[start.x][start.y];
        int count = 1;

        while (!queue.isEmpty()) {
            Country current = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nx = current.x + dx[k];
                int ny = current.y + dy[k];

                if (isValid(nx, ny) && !visited[nx][ny] && isMovePossible(current, new Country(nx, ny))) {
                    visited[nx][ny] = true;
                    queue.offer(new Country(nx, ny));
                    union.offer(new Country(nx, ny));
                    totalPopulation += population[nx][ny];
                    count++;
                }
            }
        }

        // 연합 형성이 가능하면 인구 이동 실행
        if (count > 1) {
            int avgPopulation = totalPopulation / count;
            while (!union.isEmpty()) {
                Country country = union.poll();
                population[country.x][country.y] = avgPopulation;
            }
            return true;
        }

        return false;
    }

    // 주어진 좌표가 유효한지 확인
    static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    // 두 나라 간에 인구 이동이 가능한지 확인
    static boolean isMovePossible(Country c1, Country c2) {
        int diff = Math.abs(population[c1.x][c1.y] - population[c2.x][c2.y]);
        return diff >= L && diff <= R;
    }
}