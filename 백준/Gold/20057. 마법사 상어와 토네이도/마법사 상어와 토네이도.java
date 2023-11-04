import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] graph;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0}; // 좌 하 우 상
    static int count = 1;
    static int outSand = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        graph = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Tornado tornado = new Tornado(N / 2, N / 2, 0);

        tornadoMove(tornado);
        System.out.println(outSand);
    }

    static void tornadoMove(Tornado tornado) {
        int number = 0;
        while (true) {

            for(int i = 0; i< count; i++){
                tornado.x += dx[tornado.direction];
                tornado.y += dy[tornado.direction];
                dustMove(tornado);

                if (tornado.x == 0 && tornado.y == 0)
                    return;
            }

            tornado.direction = (tornado.direction + 1) % 4;

            number += 1;

            if (number == 2) {
                count += 1;
                number = 0;
            }



        }


    }


    static void dustMove(Tornado tornado) {
        int[][][] spread = {
                {{0, 0, 2, 0, 0}, {0, 10, 7, 1, 0}, {5, 0, 0, 0, 0}, {0, 10, 7, 1, 0}, {0, 0, 2, 0, 0}},
                {{0, 0, 0, 0, 0}, {0, 1, 0, 1, 0}, {2, 7, 0, 7, 2}, {0, 10, 0, 10, 0}, {0, 0, 5, 0, 0}},
                {{0, 0, 2, 0, 0}, {0, 1, 7, 10, 0}, {0, 0, 0, 0, 5}, {0, 1, 7, 10, 0}, {0, 0, 2, 0, 0}},
                {{0, 0, 5, 0, 0}, {0, 10, 0, 10, 0}, {2, 7, 0, 7, 2}, {0, 1, 0, 1, 0}, {0, 0, 0, 0, 0}}
        };

        int total = graph[tornado.x][tornado.y];
        int spreadSand = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int nx = tornado.x + i - 2;
                int ny = tornado.y + j - 2;

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    outSand += total * spread[tornado.direction][i][j] / 100;
                } else {
                    graph[nx][ny] += total * spread[tornado.direction][i][j] / 100;
                }

                spreadSand += total * spread[tornado.direction][i][j] / 100;
            }
        }

        int nx = tornado.x + dx[tornado.direction];
        int ny = tornado.y + dy[tornado.direction];

        if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
            outSand += total - spreadSand;
        } else {
            graph[nx][ny] += total - spreadSand;
        }

        graph[tornado.x][tornado.y] = 0;
    }
}

class Tornado {
    int x;
    int y;
    int direction;

    Tornado(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}
