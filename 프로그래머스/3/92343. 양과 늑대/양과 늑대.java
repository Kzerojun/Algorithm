import java.util.*;

class Solution {
    static List<List<Integer>> graph;

    public int solution(int[] info, int[][] edges) {
        graph = new ArrayList<>();

        for (int i = 0; i < info.length; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        List<Integer> nextNodes = new ArrayList<>();

        for (int x : graph.get(0)) {
            nextNodes.add(x);
        }

        int solution = dfs(0, 0, 0, nextNodes, info);

        return solution;
    }

    static int dfs(int nowNode, int sheep, int wolf, List<Integer> nextNodes, int[] info) {
        
        if (info[nowNode] == 0) {
            sheep += 1;
        } else {
            wolf += 1;
        }
        int answer = sheep;

        if (sheep <= wolf) return answer;

        for (int nextNode : nextNodes) {
            List<Integer> stackNodes = new ArrayList<>(nextNodes);
            stackNodes.remove((Integer) nextNode); 
            stackNodes.addAll(graph.get(nextNode));
            answer = Math.max(answer, dfs(nextNode, sheep, wolf, stackNodes, info));
        }

        return answer;  
    }
}
