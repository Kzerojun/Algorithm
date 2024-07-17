import java.util.*;
class Solution {
    static List<List<Key>> keyCombinations;
    
    public boolean solution(int[][] key, int[][] lock) {
        init(key);
        
        return playGame(lock,key);
    }
    
    private static boolean playGame(int[][] lock,int[][] key) {
        int N = lock.length;
        
        // 문제점: 키의 크기를 고려하지 않고 있습니다.
        // 개선: 키가 자물쇠 밖으로 나갈 수 있도록 범위를 확장해야 합니다.
        for(int i = -key.length + 1; i < N; i++) {
            for(int j = -key.length + 1; j < N; j++) {
                boolean can = calculate(i,j,lock);
                if(can) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private static boolean calculate(int originX, int originY,int[][] lock) {
        for(List<Key> keys : keyCombinations) {
            int[][] tmp = new int[lock.length][lock.length];
            
            for(int i = 0 ; i<lock.length; i++) {
               System.arraycopy(lock[i], 0, tmp[i], 0, lock.length);
            }
            
            boolean check = true;
            
            for(Key key : keys) {
                int nx = key.x + originX;
                int ny = key.y + originY;
                
                // 문제점: 자물쇠 영역 밖의 키 부분을 무시하고 있습니다.
                // 개선: 자물쇠 영역 밖의 키 부분은 영향을 주지 않도록 처리해야 합니다.
                if(nx < 0 || ny < 0 || nx >= lock.length || ny >= lock.length) continue;
                
                if(tmp[nx][ny] == 0) {
                    tmp[nx][ny] = 1;
                } else {
                    check = false;
                    break;
                }
            }
            
            if(check && isUnlocked(tmp)) {
                return true;
            }
        }
        
        return false;
    }
    
    // 새로운 메소드: 자물쇠가 완전히 열렸는지 확인
    private static boolean isUnlocked(int[][] lock) {
        for(int i = 0; i < lock.length; i++) {
            for(int j = 0; j < lock.length; j++) {
                if(lock[i][j] == 0) return false;
            }
        }
        return true;
    }
    
    private static void init(int[][]key) {
        keyCombinations = new ArrayList<>();
        rotate(key);
    }
    
    private static void rotate(int[][]key) {
        int N = key.length;
        
        // 문제점: 5번 회전하고 있어 불필요한 연산이 있습니다.
        // 개선: 4번만 회전하면 충분합니다. (0, 90, 180, 270도)
        for (int rotation = 0; rotation < 4; rotation++) {
            List<Key> comb = new ArrayList<>();
            int[][] rotated = new int[N][N];
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    rotated[j][N-1-i] = key[i][j];
                    if (rotated[j][N-1-i] == 1) {
                        comb.add(new Key(j, N-1-i));
                    }
                }
            }
            
            keyCombinations.add(comb);
            key = rotated;
        }
    }
}

class Key{
    int x;
    int y;
    
    Key(int x, int y) {
        this.x = x;
        this.y = y;
    }
}