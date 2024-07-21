import java.util.*;

class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int length = weak.length;
        int[] extendedWeak = new int[length * 2];
        for (int i = 0; i < length; i++) {
            extendedWeak[i] = weak[i];
            extendedWeak[i + length] = weak[i] + n;
        }

        Arrays.sort(dist);
        
        for (int i = 1; i <= dist.length; i++) {
            int[] perm = new int[i];
            if (permute(extendedWeak, dist, perm, 0, i, new boolean[dist.length], length)) {
                return i;
            }
        }
        
        return -1;
    }

    private boolean permute(int[] weak, int[] dist, int[] perm, int depth, int r, boolean[] used, int length) {
        if (depth == r) {
            return check(weak, perm, length);
        }

        for (int i = 0; i < dist.length; i++) {
            if (!used[i]) {
                used[i] = true;
                perm[depth] = dist[dist.length - 1 - i];  // 큰 값부터 사용
                if (permute(weak, dist, perm, depth + 1, r, used, length)) {
                    return true;
                }
                used[i] = false;
            }
        }
        return false;
    }

    private boolean check(int[] weak, int[] friends, int length) {
        for (int start = 0; start < length; start++) {
            int position = weak[start];
            boolean success = true;
            for (int i = 0; i < friends.length; i++) {
                int distance = friends[i];
                for (int j = start; j < start + length; j++) {
                    if (weak[j] - position > distance) {
                        position = weak[j];
                        if (i == friends.length - 1) {
                            success = false;
                            break;
                        }
                        i++;
                        distance = friends[i];
                    }
                }
                if (!success) break;
            }
            if (success) return true;
        }
        return false;
    }
}