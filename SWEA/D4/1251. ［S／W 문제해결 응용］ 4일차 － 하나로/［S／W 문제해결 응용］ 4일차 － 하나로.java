import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static BufferedReader br;
    static int T;
    static double E;
    static List<Location> islands;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        init();
        startEachCase();
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
    }

    private static void startEachCase() throws IOException {
        for (int t = 1; t <= T; t++) {
            initEachSimul();
            double result = calculate();

            System.out.println("#" + t + " " + Math.round(result));
        }
    }

    private static void initEachSimul() throws IOException {
        int N = Integer.parseInt(br.readLine());
        islands = new ArrayList<>();

        int[] xArr = new int[N];
        int[] yArr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            xArr[i] = x;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            yArr[i] = x;
        }

        for (int i = 0; i < N; i++) {
            islands.add(new Location(xArr[i], yArr[i]));
        }

        E = Double.parseDouble(br.readLine());
        visited = new boolean[N];
    }

    private static double calculate() {
        PriorityQueue<Line> pq = new PriorityQueue<>((o1, o2) -> {
            return Double.compare(o1.cost, o2.cost);
        });

        // Prim 알고리즘 시작: 0번 정점을 시작점으로 설정
        visited[0] = true;
        addLinesToPQ(0, pq);

        double result = 0;
        int edgesUsed = 0;

        while (!pq.isEmpty() && edgesUsed < islands.size() - 1) {
            Line line = pq.poll();

            if (visited[line.to]) continue;

            visited[line.to] = true;
            result += line.cost;
            edgesUsed++;

            addLinesToPQ(line.to, pq);
        }

        return result;
    }

    private static void addLinesToPQ(int from, PriorityQueue<Line> pq) {
        for (int to = 0; to < islands.size(); to++) {
            if (!visited[to]) {
                double cost = E * Math.pow(Math.sqrt(Math.pow(islands.get(from).x - islands.get(to).x, 2) +
                        Math.pow(islands.get(from).y - islands.get(to).y, 2)), 2);
                pq.add(new Line(from, to, cost));
            }
        }
    }

    static class Location {
        int x;
        int y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Line {
        int from;
        int to;
        double cost;

        Line(int from, int to, double cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
