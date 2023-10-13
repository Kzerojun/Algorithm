import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, X;
    static List<Node1238>[] list;
    static int[] result;

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        result = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        Arrays.fill(result, INF);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            list[A].add(new Node1238(B, T));
        }

        int maxTime = 0;

        for (int i = 1; i <= N; i++) {
            if (i == X) continue;

            int[] timeToX = Dijkstra(i);
            int Xtotime = Dijkstra(X)[i];
            result[i] = timeToX[X] + Xtotime;

            maxTime = Math.max(maxTime,result[i]);
        }




        System.out.println(maxTime);
    }

    static int[] Dijkstra(int start) {
        PriorityQueue<Node1238> pq = new PriorityQueue<>(((o1, o2) -> o1.time - o2.time));

        pq.offer(new Node1238(start, 0));
        int[] count = new int[N + 1];
        Arrays.fill(count, INF);
        count[start] = 0;
        boolean[] visit = new boolean[N+1];

        while (!pq.isEmpty()) {
            Node1238 current = pq.poll();

            if (visit[current.location]) continue;

            visit[current.location] = true;

            for (Node1238 next : list[current.location]) {
                int newtime = count[current.location] + next.time;

                if (newtime < count[next.location]) {
                    count[next.location] = newtime;
                    pq.offer(new Node1238(next.location, newtime));
                }
            }
        }
        return count;
    }
}

class Node1238 {
    int location;
    int time;

    Node1238(int location, int time) {
        this.location = location;
        this.time = time;
    }
}
