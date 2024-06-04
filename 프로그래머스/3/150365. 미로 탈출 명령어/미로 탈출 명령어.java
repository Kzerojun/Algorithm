import java.util.*;

class Solution {
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static String[] direction = new String[]{"d", "l", "r", "u"};
    static String answer = "impossible";

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        // 출발점과 도착점의 맨해튼 거리 계산
        int distance = Math.abs(x - r) + Math.abs(y - c);
        
        // k가 맨해튼 거리보다 작거나 (k - 거리)가 홀수인 경우 불가능
        if (distance > k || (k - distance) % 2 != 0) {
            return "impossible";
        }
        
        answer = "impossible";  // 초기화
        dfs(x - 1, y - 1, r - 1, c - 1, n, m, k, "");
        return answer;
    }
    
    private void dfs(int x, int y, int r, int c, int n, int m, int k, String path) {
        // 현재 위치와 이동 횟수가 도착점에 도달했는지 확인
        if (x == r && y == c && path.length() == k) {
            answer = path;
            return;
        }
        
        // 이동 횟수가 k를 초과하면 종료
        if (path.length() >= k) {
            return;
        }

        // 사전순으로 탐색을 위해 방향 순서를 정렬된 순서로 유지
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            // 유효한 이동인지 확인
            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                // 현재까지의 경로와 이동 후 남은 거리가 홀짝성이 맞지 않으면 무시
                int remainingDistance = k - path.length() - 1;
                int targetDistance = Math.abs(nx - r) + Math.abs(ny - c);
                if (targetDistance <= remainingDistance && (remainingDistance - targetDistance) % 2 == 0) {
                    dfs(nx, ny, r, c, n, m, k, path + direction[i]);
                    // 만약 경로를 찾았다면 더 이상 탐색하지 않음
                    if (!answer.equals("impossible")) {
                        return;
                    }
                }
            }
        }
    }

}
