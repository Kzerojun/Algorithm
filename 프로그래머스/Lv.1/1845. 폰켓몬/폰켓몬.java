import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        
        for(int x : nums) {
            set.add(x);
        }
        
        int count = nums.length;
        
        if(set.size()>=count/2) {
            return count/2;
        }
        
        return set.size();
    }
}