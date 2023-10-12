import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static List<Node1916>[] list;

    static int[] result;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        result = new int[N + 1];
        Arrays.fill(result, INF);

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            list[A].add(new Node1916(B, C));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Dijkstra(start);

        System.out.println(result[end]);
    }

    static void Dijkstra(int start) {
        PriorityQueue<Node1916> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.offer(new Node1916(start, 0));
        result[start] = 0;

        while (!pq.isEmpty()) {
            Node1916 current = pq.poll();

            if (current.weight > result[current.index]) {
                continue;
            }

            for (Node1916 next : list[current.index]) {
                int newCost = result[current.index] + next.weight;

                if (newCost < result[next.index]) {
                    result[next.index] = newCost;
                    pq.offer(new Node1916(next.index, newCost));
                }
            }
        }
    }
}

class Node1916 {
    int index;
    int weight;

    Node1916(int index, int weight) {
        this.index = index;
        this.weight = weight;
    }
}
