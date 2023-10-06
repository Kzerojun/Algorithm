import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> graph;
    static int[] colors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        for (int t = 0; t < K; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            colors = new int[V + 1];

            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            if (isBipartiteGraph(V)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static boolean isBipartiteGraph(int V) {
        for (int i = 1; i <= V; i++) {
            if (colors[i] == 0) {
                if (!bfs(i, 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean bfs(int start, int color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        colors[start] = color;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph.get(current)) {
                if (colors[next] == 0) {
                    colors[next] = -colors[current];
                    queue.offer(next);
                } else if (colors[next] == colors[current]) {
                    return false;
                }
            }
        }
        return true;
    }
}



