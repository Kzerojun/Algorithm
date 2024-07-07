import java.util.*;

class Solution {
    static long[] times;
    
    public String solution(String play_time, String adv_time, String[] logs) {
        int play_second_time = getSeconds(play_time);
        times = new long[play_second_time + 1];  // 동적으로 배열 크기 설정
        init(logs);
        return calculate(play_time, adv_time);
    }
    
    private static String calculate(String play_time, String adv_time) {
        int play_second_time = getSeconds(play_time);
        int adv_second_time = getSeconds(adv_time);
        
        for(int i = 1; i <= play_second_time; i++) {
            times[i] += times[i-1];
        }
        
        // 누적 시청 시간 계산
        for(int i = 1; i <= play_second_time; i++) {
            times[i] += times[i-1];
        }
        
        long maxTime = times[adv_second_time], maxStartTime = 0;
        for (int i = 1; i + adv_second_time <= play_second_time; i++) {
            long tmp = times[i + adv_second_time - 1] - times[i - 1];
            if (tmp > maxTime) {
                maxTime = tmp;
                maxStartTime = i;
            }
        }
        
        return String.format("%02d:%02d:%02d", maxStartTime / 3600, (maxStartTime % 3600) / 60, maxStartTime % 60);
    }
    
    private static void init(String[] logs) {
        for(String log : logs) {
            String[] tmp = log.split("-");
            int startSecond = getSeconds(tmp[0]);
            int endSecond = getSeconds(tmp[1]);
            
            times[startSecond]++;
            times[endSecond]--;
        }
    }
    
    private static int getSeconds(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 3600 + Integer.parseInt(parts[1]) * 60 + Integer.parseInt(parts[2]);
    }
}