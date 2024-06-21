import java.util.*;

class Solution {
    private static List<List<Path>> paths;
    private static int[] cost;
    private static Set<Integer> gateSet;
    private static Set<Integer> summitSet;

    public int[] solution(int n, int[][] pathsInput, int[] gates, int[] summits) {
        init(n, pathsInput, gates, summits);
        return play(gates, summits);
    }

    private static int[] play(int[] gates, int[] summits) {
        int minCost = Integer.MAX_VALUE;
        int[] result = new int[2];

        Arrays.sort(summits);
        int[] moveResults = move(gates);
        
        for (int summit : summits) {
            int currentCost = moveResults[summit];
            if (currentCost < minCost) {
                minCost = currentCost;
                result[0] = summit;
                result[1] = minCost;
            }
        }

        return result;
    }

    private static void init(int n, int[][] pathsInput, int[] gates, int[] summits) {
        paths = new ArrayList<>();
        cost = new int[n + 1];
        gateSet = new HashSet<>();
        summitSet = new HashSet<>();

        for (int gate : gates) {
            gateSet.add(gate);
        }

        for (int summit : summits) {
            summitSet.add(summit);
        }

        for (int i = 0; i <= n; i++) {
            paths.add(new ArrayList<>());
            cost[i] = Integer.MAX_VALUE;
        }

        for (int[] path : pathsInput) {
            int from = path[0];
            int to = path[1];
            int pathCost = path[2];

            if (isGate(from) || isSummit(to)) {
                paths.get(from).add(new Path(to, pathCost));
            } else if (isGate(to) || isSummit(from)) {
                paths.get(to).add(new Path(from, pathCost));
            } else {
                paths.get(from).add(new Path(to, pathCost));
                paths.get(to).add(new Path(from, pathCost));
            }
        }
    }

    private static int[] move(int[] startNodes) {
        PriorityQueue<Path> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        int[] moveCosts = new int[cost.length];
        Arrays.fill(moveCosts, Integer.MAX_VALUE);

        for (int start : startNodes) {
            pq.add(new Path(start, 0));
            moveCosts[start] = 0;
        }
        
        while (!pq.isEmpty()) {
            Path now = pq.poll();

            if (now.cost > moveCosts[now.to]) {
                continue;
            }

            for (Path next : paths.get(now.to)) {
                int newCost = Math.max(now.cost, next.cost);
                if (newCost < moveCosts[next.to]) {
                    moveCosts[next.to] = newCost;
                    pq.add(new Path(next.to, newCost));
                }
            }
        }

        return moveCosts;
    }

    private static boolean isGate(int number) {
        return gateSet.contains(number);
    }

    private static boolean isSummit(int number) {
        return summitSet.contains(number);
    }
}

class Path {
    int to;
    int cost;

    Path(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}
