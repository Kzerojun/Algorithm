import java.util.*;


class Solution {
    static List<List<Integer>> result = new ArrayList<>();
    public int[][] solution(int n) {
        hanoi(n,1,3,2);
        int[][] answer = new int[result.size()][2];
        for(int i = 0 ; i<answer.length; i++) {
            for(int j = 0 ; j<2; j++) {
                answer[i][j] = result.get(i).get(j);
            }
        }
        return answer;
        
    }
    
    static void hanoi(int num, int from , int to, int other) {
        if(num == 0) {
            return;
        }
        hanoi(num-1,from, other,to);
        List<Integer> tmp = new ArrayList<>();
        tmp.add(from);
        tmp.add(to);
        result.add(tmp);
        hanoi(num-1,other,to,from);
    }
}