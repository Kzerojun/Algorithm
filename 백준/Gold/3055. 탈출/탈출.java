import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static char[][] map;
    static int R, C;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<Node3055> wq = new LinkedList<>();
    static Queue<Node3055> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String string = br.readLine();
            for (int j = 0; j < C; j++) {
                char ch = string.charAt(j);
                map[i][j] = ch;
                if (ch == 'S') {
                    q.offer(new Node3055(i, j, 0));
                } else if (ch == '*') {
                    wq.offer(new Node3055(i, j, 0));
                }
            }
        }
        int sol = SMove();

        if (sol == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(sol);
        }
    }

    static int SMove() {
        while (true) {
            WMove();
            int size = q.size();
            if (size == 0) break;
            
            for (int i = 0; i < size; i++) {
                Node3055 current = q.poll();

                for (int j = 0; j < 4; j++) {
                    int ddx = current.x + dx[j];
                    int ddy = current.y + dy[j];

                    if (ddx >= 0 && ddx < R && ddy >= 0 && ddy < C) {
                        if (map[ddx][ddy] == 'D') {
                            return current.count + 1;
                        }

                        if (map[ddx][ddy] == '.') {
                            map[ddx][ddy] = 'S';
                            q.offer(new Node3055(ddx, ddy, current.count + 1));
                        }
                    }
                }
            }
        }

        return -1;
    }

    static void WMove() {
        int size = wq.size();

        for (int i = 0; i < size; i++) {
            Node3055 current = wq.poll();

            for (int j = 0; j < 4; j++) {
                int ddx = current.x + dx[j];
                int ddy = current.y + dy[j];

                if (ddx >= 0 && ddx < R && ddy >= 0 && ddy < C) {
                    if (map[ddx][ddy] == '.') {
                        map[ddx][ddy] = '*';
                        wq.offer(new Node3055(ddx, ddy, current.count));
                    }
                }
            }
        }
    }
}

class Node3055 {
    int x;
    int y;
    int count;

    Node3055(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}
