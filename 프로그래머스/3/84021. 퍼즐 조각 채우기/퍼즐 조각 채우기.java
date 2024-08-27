import java.util.*;

class Solution {
    static List<Puzzle> boards = new ArrayList<>();
    static List<Puzzle> puzzles = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우 방향
    static int[] dy = {0, 0, -1, 1}; 
    static int N;
    
    public int solution(int[][] game_board, int[][] table) {
        init(game_board);
        findBoards(game_board);
        findPuzzle(table);
        return simulate();
    }
    
    private static void init(int[][] game_board) {
        N = game_board.length;
    }
    
    private static void findBoards(int[][] game_board) {
        boolean[][] visited = new boolean[N][N];
        for (int i = 0 ; i < N; i++) {
            for (int j = 0 ; j < N; j++) {
                if (!visited[i][j] && game_board[i][j] == 0) { // 빈칸을 찾음
                    boards.add(bfs(i, j, visited, game_board, 0));
                }
            }
        }
    }
    
    private static void findPuzzle(int[][] table) {
        boolean[][] visited = new boolean[N][N];
        for (int i = 0 ; i < N; i++) {
            for (int j = 0 ; j < N; j++) {
                if (!visited[i][j] && table[i][j] == 1) { // 퍼즐 조각을 찾음
                    puzzles.add(bfs(i, j, visited, table, 1));
                }
            }
        }
    }
    
    private static Puzzle bfs(int x, int y, boolean[][] visited, int[][] graph, int target) {
        Queue<Location> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new Location(x, y));
        
        int minX = x, minY = y, maxX = x, maxY = y;
        List<Location> points = new ArrayList<>();
        
        while (!q.isEmpty()) {
            Location now = q.poll();
            points.add(now);
            
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (!visited[nx][ny] && graph[nx][ny] == target) {
                    visited[nx][ny] = true;
                    q.add(new Location(nx, ny));
                    
                    // 좌표의 최소 및 최대값 갱신
                    minX = Math.min(minX, nx);
                    minY = Math.min(minY, ny);
                    maxX = Math.max(maxX, nx);
                    maxY = Math.max(maxY, ny);
                }
            }
        }
        
        // 퍼즐의 범위를 기준으로 정규화된 배열 생성
        int height = maxX - minX + 1;
        int width = maxY - minY + 1;
        int[][] shape = new int[height][width];
        
        for (Location loc : points) {
            shape[loc.x - minX][loc.y - minY] = 1;
        }
        
        return new Puzzle(shape);
    }

    private static int simulate() {
        int totalFilled = 0;
        
        for (int i = puzzles.size() - 1; i >= 0; i--) {
            Puzzle puzzle = puzzles.get(i);
            boolean isMatched = false;
            
            for (int j = boards.size() - 1; j >= 0; j--) {
                Puzzle board = boards.get(j);
                
                for (int r = 0; r < 4; r++) { // 90도씩 회전하며 확인
                    if (isMatch(board.shape, puzzle.shape)) {
                        totalFilled += puzzle.getSize();
                        boards.remove(j);
                        isMatched = true;
                        break;
                    }
                    puzzle.rotate(); // 퍼즐 회전
                }
                
                if (isMatched) break;
            }
        }
        
        return totalFilled;
    }
    
    private static boolean isMatch(int[][] board, int[][] puzzle) {
        if (board.length != puzzle.length || board[0].length != puzzle[0].length) {
            return false;
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != puzzle[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}

class Puzzle {
    int[][] shape;

    Puzzle(int[][] shape) {
        this.shape = shape;
    }

    void rotate() {
        int rows = shape.length;
        int cols = shape[0].length;
        int[][] rotated = new int[cols][rows];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - i - 1] = shape[i][j];
            }
        }
        shape = rotated;
    }

    int getSize() {
        int size = 0;
        for (int[] row : shape) {
            for (int cell : row) {
                if (cell == 1) size++;
            }
        }
        return size;
    }
}

class Location {
    int x, y;
    
    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
