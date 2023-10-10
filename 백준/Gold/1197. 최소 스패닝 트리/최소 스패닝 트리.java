import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int V;
    static int E;
    static int[][] edges;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edges = new int[E][3];
        parent = new int[V + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        Arrays.sort(edges, Comparator.comparingInt(o -> o[2]));

        int total = 0;

        for (int i = 0; i < E; i++) {
            if (union(edges[i][0], edges[i][1])) {
                total += edges[i][2];
            }
        }

        System.out.println(total);
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return false;

        if (x <= y) parent[y] = x;
        else parent[x] = y;

        return true;
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}