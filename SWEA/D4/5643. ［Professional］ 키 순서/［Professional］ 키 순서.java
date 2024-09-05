import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    static int N, M;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] reverseGraph;
    static int[] inDegree;
    static int[] outDegree;
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            init();
            int result = solve();
            System.out.println("#"+t+" "+result);
        }
        System.out.print(sb);
    }

    static void init() throws Exception {
        N = Integer.parseInt(br.readLine().trim());
        M = Integer.parseInt(br.readLine().trim());

        graph = new ArrayList[N + 1];
        reverseGraph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        inDegree = new int[N + 1];
        outDegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            reverseGraph[b].add(a);
            outDegree[a]++;
            inDegree[b]++;
        }
    }

    static int solve() {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (canDetermineRank(i)) {
                count++;
            }
        }
        return count;
    }

    static boolean canDetermineRank(int student) {
        boolean[] visited = new boolean[N + 1];
        int smallerCount = dfs(reverseGraph, student, visited) - 1;
        Arrays.fill(visited, false);
        int biggerCount = dfs(graph, student, visited) - 1;
        return smallerCount + biggerCount == N - 1;
    }

    static int dfs(ArrayList<Integer>[] g, int start, boolean[] visited) {
        visited[start] = true;
        int count = 1;
        for (int next : g[start]) {
            if (!visited[next]) {
                count += dfs(g, next, visited);
            }
        }
        return count;
    }
}