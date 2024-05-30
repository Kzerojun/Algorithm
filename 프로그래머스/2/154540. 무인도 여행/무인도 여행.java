import java.util.*;

class Solution {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int[] solution;
    static char[][] graph;
    static boolean[][] visited;
    static int N,M ;
    static List<Integer> solutions;
    
    public int[] solution(String[] maps) {
        //지도에는 X 또는 1에서 9사이의 자연수
        // X는 바다 자연수는 무인도
        // 상하좌우로 연결된 땅들은 하나의 무인도
        init(maps);
        int[] solution = start();
        
        if(solution.length == 0) {
            return new int[]{-1};
        }
        
        return solution;
    }
    
    private static void init(String[] maps) {
        int length = maps[0].length();
        graph = new char[maps.length][length];
        visited = new boolean[maps.length][length];
        solutions = new LinkedList<>();
        
        N = maps.length;
        M = length;
        int index = 0;
        
        for(String line : maps) {
            for(int i = 0 ; i < line.length(); i++) {
                char ch = line.charAt(i);
                graph[index][i] = ch;
            }
            index++;
        }
    }
    
    private static int[] start() {
        for(int i = 0 ; i<N; i++) {
            for(int j =0 ; j<M; j++) {
                if(!visited[i][j] && graph[i][j]!='X') {
                    solutions.add(move(i,j));
                }
                visited[i][j] = true;
            }
        }
        
        Collections.sort(solutions);
        int[] solution = new int[solutions.size()];
        
        for(int i = 0 ; i<solutions.size(); i++) {
            solution[i] = solutions.get(i);
        }
        return solution;
    }
    
    private static int move(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        
        q.add(new Node(x,y));
        visited[x][y] = true;
        
        int size = graph[x][y] -'0';
        
        while(!q.isEmpty()) {
            Node now = q.poll();
            
            for(int i = 0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx<0 || ny< 0 || nx>=N || ny>=M || visited[nx][ny]) continue;
                
                if(graph[nx][ny]!='X') {
                    visited[nx][ny] = true;
                    size += graph[nx][ny] - '0';
                    q.add(new Node(nx,ny));
                }
            }
        }
        
        return size;
    }
    
    
    private static class Node{
        int x;
        int y;
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}