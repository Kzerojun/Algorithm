import java.util.*;

class Solution {
    private static int maxDiff;  // 최대 점수 차이
    private static List<int[]> results;  // 가능한 결과들을 저장하는 리스트
    private static int[] bestResult;  // 최적의 결과
    
    public int[] solution(int n, int[] info) {
        maxDiff = 0;
        results = new ArrayList<>();
        bestResult = new int[11];
        
        dfs(info, new int[11], n, 0);
        
        // 가장 낮은 점수를 많이 맞힌 경우 선택
        if (results.isEmpty()) {
            return new int[]{-1};
        }
        
        results.sort((a, b) -> {
            for (int i = 10; i >= 0; i--) {
                if (a[i] != b[i]) {
                    return b[i] - a[i];
                }
            }
            return 0;
        });
        
        return results.get(0);
    }
    
    private void dfs(int[] info, int[] lion, int arrows, int index) {
       
        if(index == 11) {
            //만약 화살이 남아 있다면
            if(arrows>0) {
                lion[10] += arrows;
            }
            
            calculate(info,lion);
            
            if(arrows>0) {
                lion[10] -= arrows;
            }
            return;
        }
        // 그대로
        dfs(info,lion,arrows,index+1);
        
        // 화살 쏘기
        if(arrows>info[index]) {
            lion[index] = info[index]+1;
            dfs(info,lion,arrows-lion[index],index+1);
            lion[index] = 0;
        }
        
    }
    
    private static void calculate(int[]info, int[] lion) {
           int lionScore = 0;
            int apacheScore = 0;
            
            for (int i = 0; i <= 10; i++) {
                if (lion[i] > info[i]) {
                    lionScore += 10 - i;
                } else if (info[i] > 0) {
                    apacheScore += 10 - i;
                }
            }
        
         int diff = lionScore - apacheScore;
            
            if (diff > 0) {
                if (diff > maxDiff) {
                    maxDiff = diff;
                    results.clear();
                    results.add(lion.clone());
                } else if (diff == maxDiff) {
                    results.add(lion.clone());
                }
            }
        }
}
