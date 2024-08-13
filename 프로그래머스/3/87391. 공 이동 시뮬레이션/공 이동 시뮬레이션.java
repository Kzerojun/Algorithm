class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        return play(n,m,x,y,queries);
    }
    
    private static long play(int n ,int m , int x, int y, int[][] queries) {
        long r1 = x;
        long c1 = y;
        long r2 = x;
        long c2 = y;
        
        for(int i = queries.length-1; i>=0; i--) {
            int direction = queries[i][0];
            int moveCount = queries[i][1];
            
            //좌 -> 우
            if(direction == 0 ) {
                if(c1 !=0) {
                    c1 = c1 + moveCount;
                }
                c2 = Math.min(c2+moveCount,m-1);
                if(c1>=m) return 0;
            }
            
            //우 -> 좌
            if(direction == 1) {
                if(c2 != m-1) {
                    c2 = c2 - moveCount;
                }
                c1 = Math.max(c1-moveCount,0);
                if(c2<0) return 0;
            }
            //상 -> 하
            if(direction ==2) {
                if(r1!= 0) {
                    r1 = r1 + moveCount;
                }
                
                r2 = Math.min(r2+moveCount,n-1);
                if(r1>=n) return 0;
            }
            
            //하 -> 상
            if(direction == 3) {
                if(r2 != n-1) {
                    r2 = r2 - moveCount;
                }
                
                r1 = Math.max(r1-moveCount,0);
                if(r2<0) return 0;
            }

        }
        
        return (r2-r1+1) * (c2-c1+1);
    }
    
    
}