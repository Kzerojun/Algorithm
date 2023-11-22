import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] graph;
    static int[][] result;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        result = new int[n][m];

        Point start =  new Point(0,0,0);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                graph[i][j] = num;
                if(num == 2){
                    start = new Point(i,j,0);
                }
            }
        }

        move(start);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(graph[i][j]==1 && result[i][j]==0){
                    System.out.print(-1+" ");
                }else{
                    System.out.print(result[i][j] +" ");
                }
            }
            System.out.println();
        }
    }

    static void move(Point start){
        Queue<Point> q = new LinkedList<>();
        boolean[][] visit = new boolean[n][m];
        visit[start.x][start.y] = true;
        q.offer(start);
        while(!q.isEmpty()){
            Point now = q.poll();
            for(int i = 0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx<0 ||ny<0 ||nx>=n ||ny>=m) continue;

                if(!visit[nx][ny]){
                    if(graph[nx][ny]==1){
                        visit[nx][ny] = true;
                        result[nx][ny] = now.count+1;
                        q.offer(new Point(nx,ny,now.count+1));
                    }
                }
            }
        }
    }

}
class Point{
    int x;
    int y;
    int count;

    Point(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}
