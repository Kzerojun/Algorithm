import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] check;
    static boolean[] checked;
    static int N;
    static int M;
    static int V;

    public static void main(String[] args)throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        check  = new int[1001][1001];
        checked = new boolean[1001];

        for(int i = 0; i< M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            check[x][y] = check[y][x] = 1;
        }

        dfs(V);

        System.out.print("\n");
        Arrays.fill(checked,false);
        bfs(V);
    }


    public static void dfs(int start){
        checked[start]= true;
        System.out.print(start + " ");

        for(int end = 1; end<=N; end++){
            if(check[start][end] == 1 && !checked[end]){
                dfs(end);
            }
        }
    }

    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(start);
        checked[start] = true;
        System.out.print(start+ " ");
        while(!q.isEmpty()){
            int temp = q.poll();

            for(int i =1; i<check.length; i++){
                if(check[temp][i]==1 && !checked[i]){
                    q.offer(i);
                    checked[i] = true;
                    System.out.print(i + " ");
                }

            }
        }
    }

}
