import java.util.*;

class Solution {
    static int[] groupSize;
    static int N,M;
    static boolean[][] visited;
    static int[][] group;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int maxResult = Integer.MIN_VALUE;
    
    public int solution(int[][] land) {
        init(land);
        makeGroup(land);
        // calculate(land);
        return maxResult;
    }
    
    private static void init(int[][]land) {
                
        N = land.length;
        M = land[0].length;
        groupSize = new int[N*M];
        group = new int[N][M];

        visited = new boolean[N][M];
    }
    
    
    
    private static void makeGroup(int[][]land) {
        int groupNumber = 1;
        
        for(int j = 0 ; j<M; j++) {
            int size = 0;
            boolean[] check = new boolean[N*M/2+1];
            for(int i = 0 ; i<N; i++) {
                if(land[i][j]==1 && !visited[i][j]) {
                    move(new Location(i,j),groupNumber++,land);   
                }
                if(!check[group[i][j]]) {
                    size += groupSize[group[i][j]];
                    check[group[i][j]] = true;
                }
                
            }
            maxResult = Math.max(maxResult,size);
        }
    
    }
    
    private static void move(Location location,int groupNumber,int[][] land) {
        Queue<Location> q = new LinkedList<>();
        q.add(location);
        visited[location.x][location.y] = true;
        group[location.x][location.y] = groupNumber;
        int size = 1;
        while(!q.isEmpty()) {
            Location now = q.poll();
            
            for(int i = 0 ; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;
                
                if(nx<0 || ny< 0 || nx>=N || ny>= M || visited[nx][ny] || land[nx][ny] == 0) continue;
                
                visited[nx][ny] = true;
                group[nx][ny] = groupNumber;
                q.add(new Location(nx,ny));
                size++;
            }
        }
        groupSize[groupNumber] = size;
    }
}


class Location{
    int x;
    int y;
    
    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}