import java.util.*;
import java.io.*;

public class Solution {
    static int[][] graph = new int[4][4];
    static HashSet<String> set = new HashSet<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[]args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc<=T; tc++) {
            set.clear();

            for(int i = 0; i<4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j<4; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    dfs(i, j, 0, "");
                }
            }

            System.out.println("#" + tc + " " + set.size());
        }
    }

    static void dfs(int x, int y, int depth, String s) {
        if(depth == 7) {
            set.add(s);
            return;
        }

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && ny >= 0 && nx < 4 && ny < 4) {
                dfs(nx, ny, depth+1, s+graph[nx][ny]);
            }
        }
    }
}
