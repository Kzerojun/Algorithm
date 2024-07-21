import java.util.*;

class Solution {
    static List<List<Key>> keyCombinations;
    
    public boolean solution(int[][] key, int[][] lock) {
        init(key);
        
        return playGame(lock,key);
    }
    
    private static boolean playGame(int[][] lock,int[][]key) {
        int N = lock.length;
        
        for(int i = -key.length ; i<N; i++) {
            for(int j = -key.length; j<N; j++) {
                boolean can = calculate(i,j,lock);
                if(can) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private static boolean calculate(int originX, int originY,int[][] lock) {

        //90도 180도 270도 360도 미리돌린 키들 확인해보기
        for(List<Key> keys : keyCombinations) {
            
            //맵 복사
            int[][] tmp = new int[lock.length][lock.length];
            
            for(int i = 0 ; i<lock.length; i++) {
               for(int j = 0 ; j<lock.length; j++) {
                   tmp[i][j] = lock[i][j];
               }
            }
            
            boolean check = true;
            
            // 키 살펴보기
            for(int i = 0 ; i<keys.size(); i++) {
                Key key = keys.get(i);
                // System.out.println("키 위치"+key.x +" "+key.y);
                
                int nx = key.x + originX;
                int ny = key.y + originY;
                
                //만약 맵밖이면 계신 안하기
                if(nx < 0 || ny < 0 || nx>=lock.length || ny>= lock.length) continue;
                
                //만약 열쇠가 맞다면 1로 바꾸기
                // 근데 돌기에돌기가 맞닿으면 실패 불가능함
                if(tmp[nx][ny] == 0 ) {
                    tmp[nx][ny] = 1;
                    // System.out.println("열쇠 장착"+key.x +" "+key.y);
                }else if(tmp[nx][ny] == 1) {
                    // System.out.println("돌기 닿음"+key.x +" "+key.y);
                    check = false;
                    break;
                }
            }
            
            
            
            for(int i = 0 ; i<lock.length; i++) {
                for(int j = 0 ; j<lock.length; j++) {
                    if(tmp[i][j]==0) {
                        check = false;
                        break;
                    }
                }
            }
            
            if(check) {
                return true;
            }
            
            // System.out.println();
        }
        
        return false;

    }
    
    private static void init(int[][]key) {
        keyCombinations = new ArrayList<>();
        rotate(key);
    }
    
    private static void rotate(int[][]key) {
        int N = key.length;
        
        for (int rotation = 0; rotation <= 4; rotation++) {
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
            key = rotated; // 다음 회전을 위해 key 업데이트
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