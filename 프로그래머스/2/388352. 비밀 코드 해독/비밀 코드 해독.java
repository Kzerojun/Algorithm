import java.util.*;

class Solution {
    static Set<Integer> canNumbers;
    static int[][] q;
    static int n;
    static int[] ans;
    static int result;
    
    public int solution(int n, int[][] q, int[] ans) {
       init(n,q,ans);
       simulate();
        return result;
    }
    
    private static void init(int n, int[][] q, int[] ans) {
        Solution.q = q;
        Solution.n = n;
        Solution.ans  = ans;
        canNumbers = new HashSet<>();
        
        for(int i = 1; i<=n; i++) {
            canNumbers.add(i);
        }
        result = 0;
    }
    
    private static void simulate() {
        collectNumbers();
        sol(new ArrayList<>(canNumbers),0,new ArrayList<>());
    }
    
    
    private static void collectNumbers() {
        for(int i = 0; i<q.length; i++) {
            if(ans[i] == 0 ) {
                for(int number : q[i]) {
                    canNumbers.remove(number);
                }
            }
        }
        
        
    }
    
    private static void sol(List<Integer> canNumbers, int index, List<Integer> combinations) {
        
        if(combinations.size() ==5) {
            isCanPossibleCombination(combinations);
            return;
        }
        
        for(int i = index ; i<canNumbers.size(); i++) {
            combinations.add(canNumbers.get(i));
            sol(canNumbers,i+1,combinations);
            combinations.remove(combinations.size()-1);
        }
    }
    
    private static void isCanPossibleCombination(List<Integer> combinations) {

        System.out.println();
        
        for(int i = 0 ; i<q.length; i++) {
            int ansCount = 0;
            for(int j = 0; j<5; j++) {
                if(combinations.contains(q[i][j])) {
                    ansCount++;
                }
            }
            
            if(ansCount != ans[i]) {
                return;
            }
        }
        
        result++;
    }
    
    
    

}