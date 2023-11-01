import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    static int N,M,K;
    static List<FireBall>[][] map;
    static int[] dx ={-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};

    public static void main(String[]args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N][N];

        for(int i = 0; i<N; i++){
            for(int j= 0; j<N; j++){
                map[i][j] = new ArrayList<>();
            }
        }

        List<FireBall> fireBalls = new ArrayList<>();

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int size = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            fireBalls.add(new FireBall(x,y,size,direction,speed));
        }

        for(int i = 0; i<K; i++){
            fireMove(fireBalls);
            fireDivide(fireBalls);
        }

        System.out.println(FireBallCount(fireBalls));
    }

    static void fireMove(List<FireBall> fireBalls){

        for(FireBall fireBall : fireBalls){
            int nx = (N+fireBall.x+dx[fireBall.direction]*(fireBall.speed%N))%N;
            int ny = (N+fireBall.y+dy[fireBall.direction]*(fireBall.speed%N))%N;
            fireBall.x = nx;
            fireBall.y = ny;
            map[nx][ny].add(fireBall);
        }
    }
    static void fireDivide(List<FireBall> fireBalls){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(map[i][j].size()<2){
                    map[i][j].clear();
                    continue;
                }

                int sumSize = 0;
                int sumSpeed = 0;
                int size = map[i][j].size();
                int odd = 0;
                int even = 0;

                for(FireBall fireBall : map[i][j]){
                    sumSize += fireBall.size;
                    sumSpeed += fireBall.speed;
                    if(fireBall.direction%2==1){
                        odd++;
                    }else even++;

                    fireBalls.remove(fireBall);

                }

                map[i][j].clear();
                sumSize = sumSize/5;
                if(sumSize==0) continue;

                sumSpeed = sumSpeed/(size);
                if(odd == size || even == size){
                    for(int direction=0;direction<8;direction+=2)	//{0, 2, 4, 6} 방향 분열
                        fireBalls.add(new FireBall(i,j,sumSize, direction, sumSpeed));
                }else{
                    for(int direction=1;direction<8;direction+=2)	//{1, 3, 5, 7} 방향 분열
                        fireBalls.add(new FireBall(i,j,sumSize, direction, sumSpeed));
                }
            }
        }
    }

    static int FireBallCount(List<FireBall>fireBalls){
        int result = 0;
        for(FireBall fireBall : fireBalls)
            result += fireBall.size;
        return result;
    }
}

class FireBall{
    int x;
    int y;
    int size;
    int direction;
    int speed;
    FireBall(int x, int y, int size, int direction, int speed){
        this.x = x;
        this.y = y;
        this.size = size;
        this.direction = direction;
        this.speed =speed;
    }
}
