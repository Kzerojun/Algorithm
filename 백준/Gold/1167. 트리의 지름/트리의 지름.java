import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static List<Node1167>[] list;

    static boolean[] visit;

    static int sol = 0;

    static int fastestnode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());

            while (true) {
                int B = Integer.parseInt(st.nextToken());
                if (B == -1) break;
                int C = Integer.parseInt(st.nextToken());
                list[A].add(new Node1167(B, C));
                list[B].add(new Node1167(A,C));
            }
        }
        
        visit = new boolean[N+1];
        dfs(1,0);
        visit = new boolean[N+1];
        dfs(fastestnode,0);

        System.out.println(sol);
    }

    static void dfs(int start, int distance) {
        visit[start] = true;

        if (distance > sol) {
            sol = distance;
            fastestnode = start;
        }
        for (Node1167 next : list[start]) {
            if (!visit[next.index]) {
                visit[next.index] = true;
                dfs(next.index, distance + next.distance);
            }
        }
    }
}

class Node1167 {
    int index;
    int distance;

    Node1167(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }
}
