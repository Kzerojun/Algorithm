import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] dx = {1, 0, -1, 0}; // 우 상 좌 하
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] map;
    static int solution;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        List<DragonLine> dragonLines = new ArrayList<>();

        map = new boolean[101][101];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int generation = Integer.parseInt(st.nextToken());
            dragonLines.add(new DragonLine(x, y, direction, generation));
        }

        dragonMove(dragonLines);
        solution();

        System.out.println(solution);

    }

    static void dragonMove(List<DragonLine> dragonLines) {

        for (DragonLine dragonLine : dragonLines) {
            List<Integer> directions = new ArrayList<>();
            directions.add(dragonLine.direction);

            for (int i = 0; i < dragonLine.generation; i++) { // 수정된 부분
                for (int j = directions.size() - 1; j >= 0; j--) {
                    directions.add((directions.get(j) + 1) % 4);
                }
            }

            map[dragonLine.x][dragonLine.y] = true;

            for (int dir : directions) {
                dragonLine.x += dx[dir];
                dragonLine.y += dy[dir];
                map[dragonLine.x][dragonLine.y] = true;
            }

        }
    }

    static void solution() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    solution++;
                }
            }
        }
    }
}

class DragonLine {
    int x;
    int y;
    int direction;
    int generation;

    DragonLine(int x, int y, int direction, int generation) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.generation = generation;
    }
}
