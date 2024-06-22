class Solution {
    static int N,M;
    static int sol = 0;
    public int solution(int[][] board, int[][] skill) {
        init(board);
        calculate(skill,board);
        return sol;
    }
    
    private static void init(int[][] board) {
        N = board.length;
        M = board[0].length;
    }
    
    private static void calculate(int[][] skills,int[][] board) {
        int[][] prefixSum = new int[N+1][M+1];
        
        for(int[] skill : skills ) {
            int type = skill[0];
            int r1 = skill[1];
            int c1 = skill[2];
            int r2 = skill[3];
            int c2 = skill[4];
            int degree = skill[5];
            
            // attack
            if(type == 1) {
                prefixSum[r1][c1] += -degree;
                prefixSum[r1][c2+1] += degree;
                prefixSum[r2+1][c1] += degree;
                prefixSum[r2+1][c2+1] += -degree;
            }else {
                prefixSum[r1][c1] += degree;
                prefixSum[r1][c2+1] += -degree;
                prefixSum[r2+1][c1] += -degree;
                prefixSum[r2+1][c2+1] += degree;
            }
        }
        
        for(int i = 0 ; i<N; i++) {
            for(int j = 0; j<M; j++) {
                prefixSum[i][j+1] = prefixSum[i][j+1] + prefixSum[i][j];
            }
        }
        
       for(int j = 0 ; j<M; j++) {
           for(int i = 0 ; i<N; i++) {
               prefixSum[i+1][j] = prefixSum[i+1][j] + prefixSum[i][j];
           }
       }
        
        for(int i = 0 ; i<N; i++) {
            for(int j = 0 ; j<M; j++) {
                board[i][j] += prefixSum[i][j];
                if(board[i][j]>0) {
                    sol++;
                }
            }

        }
        
    }
}