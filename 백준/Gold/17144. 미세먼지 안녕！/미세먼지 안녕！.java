import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import javax.print.attribute.standard.DialogTypeSelection;

public class Main {
    static int R,C,T;
    static int[][] map;

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static int[] upper = new int[2];
    static int[] lower = new int[2];


    public static void main(String[]args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken()); //몇초 후에

        map = new int[R][C];

        List<Point15684> vacumm = new ArrayList<>();

        for (int i = 0; i <R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==-1){
                    vacumm.add(new Point15684(i,j));
                }
            }
        }

        upper[0] = vacumm.get(0).x;
        upper[1] = vacumm.get(0).y;

        lower[0] = vacumm.get(1).x;
        lower[1] = vacumm.get(1).y;

        for(int i = 0; i< T; i++){
            dustMove();
            moveAirCleaner();
        }

        solutionCount();

    }

    static void dustMove(){ //미세 먼지 움직이는 함수
        Queue<Point15684> q = new LinkedList<>();
        for (int i = 0; i <R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j]>1) q.offer(new Point15684(i,j));
            }
        }

        int[][] replication = new int [R][C];

        for(int i =0; i<R; i++){
            replication[i] = map[i].clone();
        }

        while(!q.isEmpty()){
            Point15684 current = q.poll();
            int possible = 0;
            for(int i =0; i<4; i++){
                int nx = current.x+dx[i];
                int ny = current.y+dy[i];
                if(nx>=R || ny>=C || nx<0 || ny<0) continue;

                if(map[nx][ny] != -1){
                    replication[nx][ny] += map[current.x][current.y]/5;
                    possible++;
                }
            }
            replication[current.x][current.y] -= possible * (map[current.x][current.y] / 5);
        }

        for(int i = 0; i<R; i++){
            map[i] = replication[i].clone();
        }



    }

    static void moveAirCleaner() {
        // 위쪽 공기청정기
        for (int i = upper[0] - 1; i > 0; i--) map[i][0] = map[i - 1][0];
        for (int i = 0; i < C - 1; i++) map[0][i] = map[0][i + 1];
        for (int i = 0; i < upper[0]; i++) map[i][C - 1] = map[i + 1][C - 1];
        for (int i = C - 1; i > 1; i--) map[upper[0]][i] = map[upper[0]][i - 1];
        map[upper[0]][1] = 0;

        // 아래쪽 공기청정기
        for (int i = lower[0] + 1; i < R - 1; i++) map[i][0] = map[i + 1][0];
        for (int i = 0; i < C - 1; i++) map[R - 1][i] = map[R - 1][i + 1];
        for (int i = R - 1; i > lower[0]; i--) map[i][C - 1] = map[i - 1][C - 1];
        for (int i = C - 1; i > 1; i--) map[lower[0]][i] = map[lower[0]][i - 1];
        map[lower[0]][1] = 0;
    }

    static void solutionCount(){
        int solution = 0;
        for (int i = 0; i <R; i++) {
            for (int j = 0; j <C; j++) {
                if(map[i][j]>=1){
                    solution += map[i][j];
                }

            }

        }
        System.out.println(solution);
    }

}

class Point15684{
    int x;
    int y;
    Point15684(int x, int y){
        this.x = x;
        this.y = y;
    }
}
