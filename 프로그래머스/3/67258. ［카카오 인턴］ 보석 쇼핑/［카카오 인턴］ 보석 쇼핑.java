import java.util.*;

class Solution {
    static int kind;
    static int[] sol;
    static Map<String, Integer> map;
    
    public int[] solution(String[] gems) {
        init(gems);
        calculate(gems);
        return sol;
    }
    
    private static void calculate(String[] gems) {
        map = new TreeMap<>();
        
        int left = 0;
        int right = 0;
        int minLength = Integer.MAX_VALUE;
        
        while(right<gems.length) {
            map.put(gems[right],map.getOrDefault(gems[right],0)+1);
            right++;
            
     
            
            while(map.size() == kind) {
                      if(right-left < minLength) {
                sol[0] = left+1;
                sol[1] = right;
                minLength = right-left;
            }
                map.put(gems[left],map.getOrDefault(gems[left],0)-1);
                
                if(map.get(gems[left])==0) {
                    map.remove(gems[left]);
                }
                left++;
            }
        }
    }
    
    private static void init(String[] gems) {
        kind = new HashSet<>(Arrays.asList(gems)).size();
        sol = new int[]{1, gems.length};
    }
}