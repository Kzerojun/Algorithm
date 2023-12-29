import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
    static int N,M;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int sol = 0;
    static int[][] cheese;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());

        cheese = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        while(true){
            divide();
            if(!removeCheese()){
                break;
            }

            sol++;
        }

        System.out.println(sol);
    }



    //1번 조건 내부공기 외부 공기 구별
    //외부 공기는 2로 설정
    static void divide(){
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0)); //가장 외부 끝자리는 치즈가 없기에 설정
        boolean[][] visited = new boolean[N][M];

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx<0 || ny<0 ||nx>=N ||ny>=M) continue;

                if (cheese[nx][ny] !=1 && !visited[nx][ny]) {
                    cheese[nx][ny] = 2;
                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny));
                }
            }
        }



    }

    static boolean removeCheese() {
        Queue<Point> q = new LinkedList<>();
        int[][] copyCheese = new int[N][M];
        boolean check = false;

        for (int i = 0; i < N; i++) {
            copyCheese[i] = cheese[i].clone();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cheese[i][j] == 1) {
                    q.offer(new Point(i, j));
                    check = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Point now = q.poll();
            int count = 0 ;
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx<0 || ny<0 ||nx>=N ||ny>=M) continue;

                if (cheese[nx][ny] == 2) {
                    count++;
                }
            }

            if (count >= 2) {
                copyCheese[now.x][now.y] = 0;
            }
        }

        cheese = copyCheese;

        return check;
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
