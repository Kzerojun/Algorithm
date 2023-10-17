import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[][] iceLand;
    static boolean[][] visit;
    static int year = 0;
    static int count;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        iceLand = new int[N][M];
        visit = new boolean[N][M];
        count = 0;

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                iceLand[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            IceMelting();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(!visit[i][j]&&iceLand[i][j]>=1){
                        IceCount(new Node2573(i,j));
                        count++; //빙하 덩어리 개수
                    }

                }
            }
            if(count>=2){
                break;
            }
            else if(count ==0){
                year = 0;
                break;
            }
            count = 0;
            for(int i =0; i<N;i++){
                Arrays.fill(visit[i],false);
            }
        }

        System.out.println(year);
    }

    static void IceMelting(){
        Queue<Node2573> q = new LinkedList<>();
        int[][] iceReplication = new int[N][M];


        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                iceReplication[i][j] = iceLand[i][j];
                if(iceReplication[i][j]>=1){
                    q.offer(new Node2573(i,j));
                }
            }
        }

        while(!q.isEmpty()){
            Node2573 current = q.poll();
            int ice = 0;
            for(int i = 0; i<4; i++){
                int ddx = current.x + dx[i];
                int ddy = current.y + dy[i];
                if(ddx>=0 && ddy>=0 && ddx<N && ddy<M){
                    if(iceReplication[ddx][ddy]==0){
                        ice +=1;
                    }
                }
            }
            iceLand[current.x][current.y] -=ice;
            if(iceLand[current.x][current.y]<0){
                iceLand[current.x][current.y] = 0;
            }

        }


        year +=1;



    }

    static void IceCount(Node2573 start){
        Queue<Node2573> q = new LinkedList<>();
        q.offer(start);
        visit[start.x][start.y] = true;

        while(!q.isEmpty()){
            Node2573 current = q.poll();
            for(int i = 0; i<4; i++){
                int ddx = current.x + dx[i];
                int ddy = current.y + dy[i];
                if(ddx>=0 && ddy>=0 && ddx<N && ddy<M){
                    if(!visit[ddx][ddy]&&iceLand[ddx][ddy]>=1){
                        visit[ddx][ddy] = true;
                        q.offer(new Node2573(ddx,ddy));
                    }
                }
            }


        }


    }

}

class Node2573{
    int x;
    int y;

    Node2573(int x, int y){
        this.x = x;
        this.y = y;
    }

}
