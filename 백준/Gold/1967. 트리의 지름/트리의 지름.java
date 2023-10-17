import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<Node1967>[] list;
    static int solution;

    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];


        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            list[A].add(new Node1967(B, C));
            list[B].add(new Node1967(A, C));
        }

       solution = 0;

        for(int i = 1; i <= N; i++){
            visit = new boolean[N + 1];
            dfs(i,0);
        }

        System.out.println(solution);
    }

    static void dfs(int start, int weight) {
        visit[start] = true;

        for (Node1967 next : list[start]) {
            if (!visit[next.nextIdx]) {
                visit[next.nextIdx] = true;
                dfs(next.nextIdx, weight+ next.weight);
            }
        }

        solution = Math.max(solution,weight);
    }
}

class Node1967 {
    int nextIdx;
    int weight;

    Node1967(int nextIdx, int weight) {
        this.nextIdx = nextIdx;
        this.weight = weight;
    }
}
