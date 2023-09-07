import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class Main {

    static int[][] estate;
    static boolean[][] visit;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int N;
    static int[] count;
    static int number = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        estate = new int[N][N];
        count = new int[N*N];
        visit = new boolean[N][N];


        for(int i = 0; i< N; i++){
            String input = br.readLine();
            for(int j = 0; j<input.length(); j++){
                estate[i][j] = Character.getNumericValue(input.charAt(j));
            }
        }

        for(int i = 0; i<N; i++){
            for(int j =0; j< N; j++){
                if(!visit[i][j] && estate[i][j]==1){
                    number++;
                    bfs(i,j);

                }

            }
        }

        Arrays.sort(count);

        System.out.println(number);

        for(int i=0; i<count.length; i++){
            if(count[i] == 0){
            }else{
                System.out.println(count[i]);
            }
        }
    }

    static void bfs(int x,int y){
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(x,y));
        visit[x][y] = true;
        count[number]++;

        while(!queue.isEmpty()){
            Pair tmp = queue.poll();


            for(int i =0; i< 4; i++){
                int nextx = tmp.x +dx[i];
                int nexty = tmp.y +dy[i];

                if(nextx >=0 && nexty >=0 && nextx<N && nexty<N){
                    if(!visit[nextx][nexty] && estate[nextx][nexty]==1){
                        visit[nextx][nexty] = true;
                        queue.offer(new Pair(nextx,nexty));
                        count[number]++;
                    }

                }
            }

        }
    }


}
class Pair{
    int x;
    int y;

    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}