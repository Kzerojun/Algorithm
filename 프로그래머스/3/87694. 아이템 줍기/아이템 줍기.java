import java.util.*;

class Solution {
    static int[][] graph;
    static int[] dx ={-1,1,0,0};
    static int[] dy ={0,0,-1,1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        init(rectangle);
        return move( characterX,  characterY,  itemX,  itemY);
    }
    
    private static void init(int[][] rectangle) {
        graph = new int[101][101];
        
        for(int[] location : rectangle) {
            int x1 = location[0]*2;
            int y1 = location[1]*2;
            int x2 = location[2]*2;
            int y2 = location[3]*2;     
            fillGraph(x1,y1,x2,y2);
        }
    }
    
    private static void fillGraph(int x1, int y1, int x2, int y2) {
        // 외부 그리기
        for(int j = y1; j<=y2; j++) {
            if(graph[x1][j]!=2) {
                 graph[x1][j] = 1;
            }
             if(graph[x2][j]!=2) {
                 graph[x2][j] = 1;
            }
        }
        //외부 그리기
        for(int i = x1; i<=x2; i++) {
             if(graph[i][y1]!=2) {
                 graph[i][y1] = 1;
            }
            if(graph[i][y2]!=2) {
                 graph[i][y2] = 1;
            }
        }
        
        //내부 그리기
       for(int i = x1+1; i<=x2-1; i++) {
           for(int j = y1+1; j<=y2-1; j++) {
                  graph[i][j] = 2;
           }
       }
        
        
        
    }
    
    private static int move(int chX, int chY, int iX, int iY) {
        Queue<Location> q = new LinkedList<>();
        boolean[][] visited = new boolean[101][101];
        q.add(new Location(chX*2,chY*2,0));
        visited[chX*2][chY*2] = true;
        
        while(!q.isEmpty()) {
            Location now = q.poll();
            
            if(now.x == iX*2 && now.y == iY*2) {
                return now.moveCount/2;
            }
            
            for(int i =0 ; i<4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;
                
                if(nx<0 || ny<0 || ny>=101 || nx>=101) continue;
                if(visited[nx][ny]) continue;
                if(graph[nx][ny]!=1) continue;
                
                visited[nx][ny] = true;
                q.add(new Location(nx,ny,now.moveCount+1));
            }    
        }
        return -1;
    }

}

class Location{
    int x;
    int y;
    int moveCount;
    
    Location(int x, int y, int moveCount) {
        this.x = x;
        this.y = y;
        this.moveCount = moveCount;
    }
}