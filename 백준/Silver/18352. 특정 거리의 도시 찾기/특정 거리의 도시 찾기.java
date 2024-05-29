import java.util.*;
import java.lang.Comparable;
import java.io.*;
public class Main {
    static int N, M, K, X;
    static ArrayList<Node>[] arr;
    static int[] dist;

    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      X = Integer.parseInt(st.nextToken());
      arr = new ArrayList[N+1];
      for (int i = 0; i <= N; i++){
          arr[i] = new ArrayList<>();
      }
      dist = new int[N+1];
      Arrays.fill(dist, Integer.MAX_VALUE);

      for (int i = 0; i < M; i++) {
          st = new StringTokenizer(br.readLine());
          int x = Integer.parseInt(st.nextToken());
          int y = Integer.parseInt(st.nextToken());
          arr[x].add(new Node(y, 1)); // 기본 가중치.
      }
      BFS(X);

      boolean ck = false;
      for (int i = 1; i <= N; i++) {
          if (dist[i] == K) {
              System.out.println(i);
              ck = true;
          }
      }
      if (!ck)
          System.out.println(-1);

    }
    public static void BFS(int v) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(v, 0));
        dist[v] = 0;
        boolean[] visited = new boolean[N+1];

        while(!q.isEmpty()) {
            Node now = q.poll();
            if (visited[now.index])
                continue;
            visited[now.index] = true;
            for (Node next: arr[now.index]) {
                if (!visited[next.index] && dist[next.index] > (next.dist + dist[now.index])) {
                    dist[next.index] = dist[now.index] + next.dist;
                    q.add(new Node(next.index, dist[next.index]));
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int index, dist;
    Node (int index, int dist) {
        this.index = index;
        this.dist = dist;
    }
    @Override public int compareTo(Node n) {
        return dist - n.dist;
    }
}