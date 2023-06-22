import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (o1,o2)->(o1[1]-o2[1]));
        int rocket = Integer.MIN_VALUE;
        int answer =0;
        
        for(int[] t : targets){
            if(rocket<t[0]){
                rocket  = t[1] -1;
                answer++;
            }
        }
        return answer;
    }
}