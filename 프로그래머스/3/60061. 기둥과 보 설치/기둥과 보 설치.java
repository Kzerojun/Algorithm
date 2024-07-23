import java.util.*;

class Solution {
    static boolean[][] pillar;
    static boolean[][] paper;
    static int N;
    
    public int[][] solution(int n, int[][] build_frame) {
        init(n);
        play(build_frame);
        return sol();
    }
    
    private static int[][] sol() {
        List<Result> results = new ArrayList<>();
        
        for(int i = 0 ; i<=N; i++) {
            for(int j = 0; j<=N; j++) {
                if(pillar[i][j]) {
                    Location originLocation = swap(i,j);
                    results.add(new Result(originLocation.x,originLocation.y,0));
                }
                
                if(paper[i][j]) {
                    Location originLocation = swap(i,j);
                    results.add(new Result(originLocation.x,originLocation.y,1));
                }
            }
        }
        
        results.sort((o1,o2)->{
            if(o1.x == o2.x) {
                return o1.y - o2.y;
            };
            
            return o1.x - o2.x;
        });
        
        int[][] sol = new int[results.size()][3];
        int index = 0;
        for(Result result : results) {
            sol[index][0] = result.x;
            sol[index][1] = result.y;
            sol[index][2] = result.type;
            index++;
            
        }
        return sol;
    }
    
    private static void init(int n) {
        N = n;
        pillar = new boolean[N+1][N+1];
        paper = new boolean[N+1][N+1];
    }
    
    private static void play(int[][] build_frame) {
        for(int[] build : build_frame) {
            int x = build[0];
            int y = build[1];
            int a = build[2];
            int b = build[3];
            
            command(x,y,a,b);
            
        }
    }
    
    private static void command(int x, int y, int a , int b) {
        Location location = swap(x,y);
        
        if(b == 1 ) {
            build(location,a);
        }
        
        if(b == 0) {
            destory(location,a);
        }
    }
    
    private static void destory(Location location, int type) {
        if (type == 0) { // 기둥 제거
            pillar[location.x][location.y] = false;
            
            if (!isValidStructure()) {
                pillar[location.x][location.y] = true; // 제거 취소
                return;
            }
        } else if (type == 1) { // 보 제거
            paper[location.x][location.y] = false;
            
            if (!isValidStructure()) {
                paper[location.x][location.y] = true; // 제거 취소
                return;
            }
        }
    }

    private static boolean isValidStructure() {
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (pillar[i][j] && !canPillar(new Location(i, j))) return false;
                if (paper[i][j] && !canPaper(new Location(i, j))) return false;
            }
        }
        return true;
    }
    
    // 0 은 기둥 1은 보
    private static void build(Location location,int type) {
        
        if(type == 0 ) {
            boolean check = canPillar(location);
            if(check) {
                pillar[location.x][location.y] = true;
            }
        }
        
        if(type == 1) {
            boolean check = canPaper(location);
            if(check) {
                paper[location.x][location.y] = true;
            }
        }
    
    }
    
    private static boolean canPaper(Location location) {
        int[] dx = {-1,-1};
        int[] dy = {0,1};
        
        for(int i = 0; i<2; i++) {
            int nx = dx[i] + location.x;
            int ny = dy[i] + location.y;
            
            if(nx <0 || ny<0 || nx>N || ny>N) continue;
            
            if(pillar[nx][ny]) {
                return true;
            }
        }
        
        int[] ddy = {-1,1};
        
        for(int i = 0 ; i<2; i++) {
            int ny = ddy[i] + location.y;
            if(ny<0 || ny>N) return false;
            if(!paper[location.x][ny]) return false;
        }
        
        return true;
    }
    
    private static boolean canPillar(Location location) {
        if(location.x == 0 ) {
            return true;
        }
        
        int nx = location.x - 1;
        if(pillar[nx][location.y]) {
            return true;
        }
        
        int[] dx = {0,0};
        int[] dy = {-1,0};
        
        for(int i = 0; i<2; i++) {
            int nnx = location.x + dx[i];
            int nny = location.y + dy[i];
            
            if(nnx <0 || nny<0 || nnx>N || nny>N) continue;
            
            if(paper[nnx][nny]) {
                return true;
            }
        }
        
        return false;
    }
    
    
    private static Location swap(int x, int y) {
        int tmp = x;
        x = y;
        y = tmp;
        
        return new Location(x,y);
    }
}

class Result {
    int x;
    int y;
    int type;
    
    Result(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
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