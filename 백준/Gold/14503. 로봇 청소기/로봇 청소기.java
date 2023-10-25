import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] room;
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1}; // 북 동 남 서
    static int x, y, d, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        room = new int[N][M];

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken()); //청소기 위치 r
        y = Integer.parseInt(st.nextToken()); //청소기 위치 c
        d = Integer.parseInt(st.nextToken()); //청소기 방향 0 북쪽/1 동쪽/2남쪽 /3 서쪽

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
        System.out.println(count);
    }

    static void solution(){
        while(true){
            if(room[x][y] == 0) {
                room[x][y] = 2;
                count++;
            }

            boolean check = false;
            for(int i =0; i<4; i++){
                int nd = (d + 3) % 4;
                int nx = x + dx[nd];
                int ny = y + dy[nd];

                if(nx<0 || ny <0 || nx >=N || ny >=M) continue;

                if(room[nx][ny] ==0) {
                    d = nd;
                    x = nx;
                    y = ny;
                    check = true;
                    break;
                } else {
                    d = nd;
                }
            }

            if(!check){
                int nx = x - dx[d];
                int ny = y - dy[d];
                if(room[nx][ny]==1){
                    break;
                }else{
                    x = nx;
                    y = ny;
                }
            }
        }
    }
}
