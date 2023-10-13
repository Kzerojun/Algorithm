import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static char[][] board;

    static boolean[][][][] visit;

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    public static void main(String[]args)throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visit = new boolean[N][M][N][M];

        int rx = 0, ry = 0, bx = 0, by = 0;

        for(int i = 0; i<N; i++){
            String string = br.readLine();
            for(int j = 0; j<M; j++){
                char ch = string.charAt(j);
                board[i][j] = ch;

                if(ch == 'R'){
                    rx = i;
                    ry = j;
                }
                if(ch =='B'){
                    bx = i;
                    by = j;
                }

            }
        }
        int result = bfs(rx,ry,bx,by);
        System.out.println(result);


    }
    static int bfs(int rx, int ry, int bx, int by){
        Queue<Point13460> q = new LinkedList<>();
        q.offer(new Point13460(rx,ry,bx,by,0));
        visit[rx][ry][bx][by] = true;

        while(!q.isEmpty()){
            Point13460 current = q.poll();
            if(current.count>=10){
                break;
            }

            for(int i = 0; i<4; i++){
                int nrx = current.rx, nry = current.ry;
                int nbx = current.bx, nby = current.by;

                // 빨간 구슬 이동
                while (board[nrx + dx[i]][nry + dy[i]] != '#' && board[nrx][nry] != 'O') {
                    nrx += dx[i];
                    nry += dy[i];
                }

                // 파란 구슬 이동
                while (board[nbx + dx[i]][nby + dy[i]] != '#' && board[nbx][nby] != 'O') {
                    nbx += dx[i];
                    nby += dy[i];
                }

                // 파란 구슬이 구멍에 빠진 경우는 제외
                if (board[nbx][nby] == 'O') {
                    continue;
                }

                // 빨간 구슬이 구멍에 빠진 경우
                if (board[nrx][nry] == 'O') {
                    return current.count + 1;
                }

                // 빨간 구슬과 파란 구슬이 같은 위치에 있으면 위치 조정
                if (nrx == nbx && nry == nby) {
                    int rDist = Math.abs(nrx - current.rx) + Math.abs(nry - current.ry);
                    int bDist = Math.abs(nbx - current.bx) + Math.abs(nby - current.by);

                    if (rDist > bDist) {
                        nrx -= dx[i];
                        nry -= dy[i];
                    } else {
                        nbx -= dx[i];
                        nby -= dy[i];
                    }
                }

                // 새로운 상태를 큐에 추가
                if (!visit[nrx][nry][nbx][nby]) {
                    q.offer(new Point13460(nrx, nry, nbx, nby, current.count + 1));
                    visit[nrx][nry][nbx][nby] = true;
                }

            }
        }

        return -1;


    }


}

class Point13460{
    int rx;
    int ry;
    int bx;
    int by;
    int count;
    public Point13460(int rx, int ry, int bx, int by, int count) {
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        this.count = count;
    }
}