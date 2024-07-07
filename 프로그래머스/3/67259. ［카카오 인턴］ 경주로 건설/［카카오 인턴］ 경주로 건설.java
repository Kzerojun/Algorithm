import java.util.*;

class Solution {
    static int[] dx = {-1,0,1,0}; //상 우 하 좌
    static int[] dy = {0,1,0,-1};
    static int[][][] cost;
    static int N;
    static int INF = Integer.MAX_VALUE;
    
    public int solution(int[][] board) {
        init(board);
        return move(board);
    }
    
    private static int move(int[][] board) {
        PriorityQueue<Point> pq = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);
        
        pq.add(new Point(0,0,0,1)); // 오른쪽 방향으로 시작
        pq.add(new Point(0,0,0,2)); // 아래쪽 방향으로 시작
        
        for (int i = 0; i < 4; i++) {
            cost[0][0][i] = 0;
        }
        
        while(!pq.isEmpty()) {
            Point now = pq.poll();
            
            if(now.x == N-1 && now.y == N-1) {
                return now.cost;
            }
            
            if(cost[now.x][now.y][now.direction] < now.cost) continue;
            
            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == 1) continue;
                
                int nextCost = getCost(now.direction, i) + now.cost;
                
                if(cost[nx][ny][i] <= nextCost) continue;
                
                cost[nx][ny][i] = nextCost;
                pq.add(new Point(nx, ny, nextCost, i));
            }
        }
        
        return -1;
    }
    
     private static int getCost(int nowDirection, int nextDirection) {
        // 같은 방향
        if(nowDirection == nextDirection) {
            return 100;
        }

        // 다른 방향
        if((nowDirection + nextDirection % 2) ==0) {
            return 100;
        }

        // 직각 방향
        return 600;
    }
    
    private static void init(int[][] board) {
        N = board.length;
        cost = new int[N][N][4];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                Arrays.fill(cost[i][j], INF);
            }
        }
    }
}

class Point {
    int x;
    int y;
    int cost;
    int direction;
    
    Point(int x, int y, int cost, int direction) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.direction = direction;
    }
}