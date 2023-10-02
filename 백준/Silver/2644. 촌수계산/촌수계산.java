import java.io.*;
import java.util.*;
public class Main {

    static int n;
    static int m;
    static int[][] family;
    static boolean[] visit;
    static int sol = -1;
    public static void main(String[]args)throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());

        family = new int[n+1][n+1];
        visit = new boolean[n+1];

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            family[x][y] = family[y][x] = 1;

        }

        dfs(start,end,0);
        System.out.println(sol);
    }

    static void dfs(int start, int end, int count){

        if(start == end){
            sol = count;
            return;
        }


        visit[start] = true;
        for(int i = 1; i<=n; i++){
            if(family[start][i]==1&&!visit[i]){

                dfs(i,end,count+1);

            }
        }
    }
}
