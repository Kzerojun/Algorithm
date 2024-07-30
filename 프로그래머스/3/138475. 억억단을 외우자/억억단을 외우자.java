import java.util.*;

class Solution {
    static int[] measureCount;
    static int[] maxNumbers;
    static int[] sol;
    public int[] solution(int e, int[] starts) {
        init(e,starts);
        play(e,starts);
        return sol;
    }
    
    private static void init(int e, int[] starts) {
        measureCount = new int[e+1];
        maxNumbers = new int[e+1];
        
        sol = new int[starts.length];
        measureCount(e);
    }
    
    private static void play(int e, int[] starts) {
        int maxNumber = -1;
        int maxCount = -1;
        for(int i = e; i>=1; i--) {
            
            if(measureCount[i]>=maxCount) {
                maxCount = measureCount[i];
                maxNumber = i;
            }
            maxNumbers[i] = maxNumber;
        }
        
        int index = 0;
        for(int start : starts) {
            sol[index++] = maxNumbers[start];
        }
        
    }
    
    private static void measureCount(int e) {
       for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
            measureCount[j]++;
            }
        }
        
        
    }
}