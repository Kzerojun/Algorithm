import java.util.*;

class Solution {
    static int[] dx = {-1,-2,-1,0,0,1,1,2,1,0,0,-1}; //위 1, 위 2, 대각선
    static int[] dy = {0,0,1,1,2,1,0,0,-1,-1,-2,-1};
    static final char PARTITION = 'X';
    static int[] sol = new int[5];
    static int index = 0;
    public int[] solution(String[][] places) {
        play(places);
        return sol;
    }
    
    private static void play(String[][] places) {
        for(String[] place : places) {
            char[][] graph = new char[5][5];
            List<Player> players = new ArrayList<>();
            
            for(int i = 0 ; i<5; i++) {
                for(int j = 0 ; j<5; j++) {
                    char ch = place[i].charAt(j);
                    graph[i][j] = ch;
                    if(ch == 'P') {
                        players.add(new Player(i,j));
                    }
                }
            }
            
            calculate(players,graph);
            
        }
    }
    
    private static void calculate(List<Player> players, char[][] graph) {
        for(Player player : players) {
            
            for(int i = 0 ; i<8; i++) {
                int nx = player.x + dx[i];
                int ny = player.y + dy[i];
                
                if(nx<0 || ny<0 || nx>=5 || ny>=5) continue;
                
                //맨해튼 거리에 위치
                if(graph[nx][ny]=='P') {
                    if(!isValid(i,graph,player)) {
                        sol[index++] = 0;
                        return;
                    }
                }
            }
        }
        
        sol[index++] = 1;
        
    }
    
    private static boolean isValid(int direction,char[][] graph,Player player) {
        if(direction == 0 || direction == 3 || direction ==6 || direction ==9) {
            return false;
        }
        
        if(direction == 1) {
            if(graph[player.x-1][player.y]==PARTITION) {
                return true;
            }
        }
        
        if(direction == 2) {
           if(graph[player.x-1][player.y]==PARTITION && graph[player.x][player.y+1]==PARTITION){
               return true;
           }
        }
        
        if(direction == 4) {
            if(graph[player.x][player.y+1]==PARTITION){
                return true;
            }
        }
        
        if(direction == 5) {
            if(graph[player.x][player.y+1]==PARTITION && graph[player.x+1][player.y]==PARTITION){
                return true;
            }
        }
        
        if(direction == 7) {
            if(graph[player.x+1][player.y]==PARTITION) {
                return true;
            }
        }
        
        if(direction == 8) {
            if(graph[player.x][player.y-1]==PARTITION && graph[player.x+1][player.y]==PARTITION) {
                return true;
            }
        }
        
        if(direction == 10) {
            if(graph[player.x][player.y-1]==PARTITION) {
                return true;
            }
        }
        
        if(direction == 11) {
            if(graph[player.x-1][player.y]== PARTITION&&graph[player.x][player.y-1]==PARTITION) {
                return true;
            }
        }
        
        return false;
    }
    
    
}

class Player{
    int x;
    int y;
    
    Player(int x, int y) {
        this.x = x;
        this.y = y;
    }
}