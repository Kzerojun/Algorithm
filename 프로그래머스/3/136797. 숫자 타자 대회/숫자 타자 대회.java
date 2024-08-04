import java.util.*;

class Solution {
    static int[][] costs;
    static int[][] graph;
    static int[][][] dp;
    
    public int solution(String numbers) {
        init(numbers);
        initGraph();
        return sol(0,4,6,numbers);
    }
    
    private static int sol(int index , int left, int right,String numbers) {
        if(index == numbers.length()) {
            return 0;
        }
        
        if(dp[index][left][right]!= -1) {
            return dp[index][left][right];
        }
        
        int number = numbers.charAt(index) - '0';
        int result = Integer.MAX_VALUE;
        
        //숫자가 오른쪽 손가락 위에 없을경우만
        if(number != right) {
            result = Math.min(sol(index+1,number,right,numbers)+costs[left][number],result);
        }
        if(number != left) {
            result = Math.min(sol(index+1,left,number,numbers)+costs[right][number],result);
        }
        
        return dp[index][left][right] = result;
    }
   
    private static void init(String numbers) {
        costs = new int[10][10]; 
        
        dp = new int[numbers.length() + 1][10][10];
        for (int i = 0; i < numbers.length()+ 1; i++) {
            for (int j = 0; j < 10; j++)
                Arrays.fill(dp[i][j], -1);
        }
    }
    
    private static void initGraph() {
        graph = new int[4][3];
        int number = 1;
        
        for(int i = 0 ; i<3; i++) {
            for(int j = 0 ; j<3; j++) {
                graph[i][j] = number++;
            }
        }
        
        graph[3][0] = -1;
        graph[3][1] = 0;
        graph[3][2] = -1;
        
        for(int i = 0 ; i<4; i++) {
            for(int j = 0 ; j<3; j++) {
                if(graph[i][j] == -1) continue;
                calculate(new Position(i,j,0),graph[i][j]);
            }
        }
    }
    
    //각 위치에서 다른 숫자로의 가중치 구하기
    private static void calculate(Position position,int startNumber) {
        Queue<Position> q = new LinkedList<>();
        boolean[][] visited = new boolean[4][3];
        
        q.add(position);
        visited[position.x][position.y] = true;
        costs[startNumber][graph[position.x][position.y]] = 1;
        
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        
        int[] ddx = {-1,-1,1,1};
        int[] ddy = {-1,1,1,-1};
        
        while(!q.isEmpty()) {
            Position now = q.poll();
            
            //true -> 4방향
            move(dx,dy,visited,q,now,true,startNumber);
            //false -> 대각선
            move(ddx,ddy,visited,q,now,false,startNumber);
            
        }
    }
    
    private static void move(int[] dx, int[] dy ,boolean[][] visited, Queue<Position> q, Position now, boolean direction,int startNumber) {
        for(int i = 0 ; i< 4; i++) {
            int nx = dx[i] + now.x;
            int ny = dy[i] + now.y;
            
            if(nx <0 || ny<0 || nx>=4 || ny>=3 ) continue;
            if(visited[nx][ny]) continue;
            if(graph[nx][ny]== -1) continue;
            
            visited[nx][ny] = true;
            
            if(direction) {
                costs[startNumber][graph[nx][ny]] = now.weight+2;
                q.add(new Position(nx,ny,now.weight+2));
            }else {
                 costs[startNumber][graph[nx][ny]] = now.weight+3;
                q.add(new Position(nx,ny,now.weight+3));
            }
        }
    }
}
class Test{
    Position left;
    Position right;
    int result;
    
    Test(Position left, Position right, int result) {
        this.left = left;
        this.right = right;
        this.result = result;
    }
}

class Weight {
    int number;
    int weight;
    
    Weight(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }
}

class Position {
    int x;
    int y;
    int weight;
    
    Position(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }
}