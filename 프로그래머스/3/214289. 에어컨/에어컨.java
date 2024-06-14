import java.util.*;

class Solution {
    private static int[][] dp;
    private static final int MAX = 1_000_001;
    private static final int MAX_TEMP = 40, MIN_TEMP = -10, RANGE = MAX_TEMP - MIN_TEMP;
    private static int adjustedTemperature;
    private static int adjustedT1;
    private static int adjustedT2;
    
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        if (t1 <= temperature && temperature <= t2) return 0;
        init(temperature, t1, t2, onboard);
        calculate(onboard, a, b);
        return sol(onboard);
    }
    
    private static void init(int temperature, int t1, int t2, int[] onboard) {
        adjustedTemperature = temperature - MIN_TEMP;
        adjustedT1 = t1 - MIN_TEMP;
        adjustedT2 = t2 - MIN_TEMP;

        dp = new int[onboard.length][RANGE + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], MAX);
        }

        dp[0][adjustedTemperature] = 0;
    }
    
    private static void calculate(int[] onboard, int a, int b) {
        for (int i = 0; i < onboard.length - 1; i++) {
            for (int j = 0; j <= RANGE; j++) {
                if (onboard[i] == 1 && (j < adjustedT1 || j > adjustedT2)) continue;

                int nextTemp = j;
                if (j < adjustedTemperature && j < RANGE) nextTemp = j + 1;
                else if (j > adjustedTemperature && j > 0) nextTemp = j - 1;
                dp[i + 1][nextTemp] = Math.min(dp[i][j], dp[i + 1][nextTemp]);

                if (j > 0) dp[i + 1][j - 1] = Math.min(dp[i][j] + a, dp[i + 1][j - 1]);
                if (j < RANGE) dp[i + 1][j + 1] = Math.min(dp[i][j] + a, dp[i + 1][j + 1]);

                dp[i + 1][j] = Math.min(dp[i][j] + b, dp[i + 1][j]);
            }
        }
    }
    
    private static int sol(int[] onboard) {
        int answer = MAX;
        for (int j = 0; j <= RANGE; j++) {
            if (onboard[onboard.length - 1] == 1 && (j < adjustedT1 || j > adjustedT2)) continue;
            answer = Math.min(dp[onboard.length - 1][j], answer);
        }
        return answer;
    }
}
