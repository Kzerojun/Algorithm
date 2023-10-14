import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, E;
    static List<Node1504>[] list;
    static final int INF = Integer.MAX_VALUE / 2; 
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Node1504(b, c));
            list[b].add(new Node1504(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int sol1 = 0;
        int sol2 = 0;

        if (E > 0) {
            sol1 += Dijkstra(1, v1);
            sol1 += Dijkstra(v1, v2);
            sol1 += Dijkstra(v2, N);

            sol2 += Dijkstra(1, v2);
            sol2 += Dijkstra(v2, v1);
            sol2 += Dijkstra(v1, N);
        } else {
            // No edges, set result to -1
            sol1 = sol2 = -1;
        }

        int ans = Math.min(sol1, sol2);
        if (ans >= INF) System.out.print(-1);
        else System.out.print(ans);
    }

    static int Dijkstra(int start, int end) {
        PriorityQueue<Node1504> pq = new PriorityQueue<>((o1, o2) -> o1.distance - o2.distance);

        Arrays.fill(result, INF);

        pq.offer(new Node1504(start, 0));
        result[start] = 0;

        while (!pq.isEmpty()) {
            Node1504 current = pq.poll();

            if (current.distance > result[current.index]) continue;

            for (Node1504 next : list[current.index]) {
                int newDistance = next.distance + current.distance;

                if (result[next.index] > newDistance) {
                    result[next.index] = newDistance;
                    pq.offer(new Node1504(next.index, newDistance));
                }
            }
        }

        return result[end];
    }
}

class Node1504 {
    int index;
    int distance;

    Node1504(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }
}
