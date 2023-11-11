import java.io.*;
import java.util.*;

public class Solution {
    static int T;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};  //우 하 좌 상

    public static void main(String[]args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for(int i =1; i<=T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[][] graph = new int[N][N];
            snailMove(graph,N);
            printSolution(graph, i);

        }

    }

    static void snailMove(int[][] graph, int N) {
        Queue<Snail> q = new LinkedList<>();
        q.offer(new Snail(0,0,1,0));
        graph[0][0] = 1;

        while(!q.isEmpty()) {
            Snail now = q.poll();
            if (now.count == N*N) break; // 수정된 부분

            int nx = now.x + dx[now.direction];
            int ny = now.y + dy[now.direction];

            if(nx<0 || ny<0 || ny>=N || nx >=N ||graph[nx][ny]!=0) {
                q.offer(new Snail(now.x, now.y, now.count, (now.direction+1)%4));
                continue;
            }

            graph[nx][ny] = now.count+1;
            q.offer(new Snail(nx,ny,now.count+1,now.direction));
        }
    }

    static void printSolution(int[][] graph, int t) {
        System.out.println("#" + t);
        for(int i = 0; i<graph.length; i++) {
            for(int j = 0; j<graph.length; j++) {
                System.out.print(graph[i][j]+" ");
            }
            System.out.println();
        }
    }
}
class Snail{
    int x;
    int y;
    int count;
    int direction;
    Snail(int x, int y, int count, int direction){
        this.x = x;
        this.y = y;
        this.count = count;
        this.direction = direction;
    }
}
