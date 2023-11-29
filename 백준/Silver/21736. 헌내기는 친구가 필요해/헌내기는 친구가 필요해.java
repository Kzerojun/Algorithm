import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visit;
    static int N,M;
    static char[][] campus;
    static int sol = 0;

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        campus = new char[N][M];
        visit = new boolean[N][M];
        Point start = new Point(-1,-1);
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                campus[i][j] = input.charAt(j);
                if(campus[i][j]=='I'){
                    start = new Point(i, j);
                }
            }
        }
        move(start);
        if(sol==0){
            System.out.println("TT");
            return;
        }
        System.out.println(sol);
    }

    static void move(Point start){
        Queue<Point> q = new LinkedList<>();
        q.offer(start);
        visit[start.x][start.y] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (campus[now.x][now.y] == 'P') {
                sol++;
            }
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx <0 || ny <0 || nx>=N || ny>=M) continue;

                if(!visit[nx][ny] && campus[nx][ny]!='X'){
                    q.offer(new Point(nx, ny));
                    visit[nx][ny] = true;
                }
            }
        }
    }
}
class Point{
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
