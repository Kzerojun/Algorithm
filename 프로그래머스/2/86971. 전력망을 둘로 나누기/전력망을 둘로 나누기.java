import java.util.*;

class Solution {
    static int length;
    static int wireLength;
    static int sol = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        length = n;
        wireLength = wires.length;
        for(int i = 0; i<wires.length; i++) {//스킵할 노드
            
            int[][] tree = new int[n+1][n+1];
            
            for(int j = 0 ; j<wires.length; j++) {
                if(i==j) continue;
                int start = wires[j][0];
                int to = wires[j][1];
                tree[start][to] = tree[to][start] = 1;
            }
            
            select(tree);
            
        }
        return sol;
    }
    
    static void select(int[][] tree) {
        boolean[] visited = new boolean[length+1];
        List<Integer> result = new ArrayList<>();
        int count = 0 ;
        int tmp = 0;
        for(int i = 1; i <= length; i++) {

            if(!visited[i]) {
                calculate(i, tree, visited);
                
                for(int j = 0; j<=length; j++) {
                    if(visited[j]) count++;
                }
                result.add(count-tmp);
                tmp = count;
                count = 0;
            }
        }
        int first = result.get(0);
        int second = result.get(1);
        
        int finalResult = Math.abs(first-second);
        sol = Math.min(sol, finalResult);
    }
    
    static void calculate(int index, int[][] tree, boolean[] visited) {
        visited[index] = true;
        
        for(int i = 1; i <= length; i++) {
            if(tree[index][i] != 0 && !visited[i]) {
                calculate(i, tree, visited);
            }

        }
        
    }
}
