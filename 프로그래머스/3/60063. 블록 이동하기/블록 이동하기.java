import java.util.*;

class Solution {
    static final int HORIZONTAL = 0;
    static final int VERTICAL = 1;
    
    int N;
    int[][] board;
    
    public int solution(int[][] board) {
        this.board = board;
        this.N = board.length;
        return bfs();
    }
    
    private int bfs() {
        Queue<Robot> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][N][2];
        
        queue.offer(new Robot(0, 0, 0, 1, HORIZONTAL, 0));
        visited[0][0][HORIZONTAL] = true;
        
        while (!queue.isEmpty()) {
            Robot cur = queue.poll();
            
            if ((cur.r1 == N-1 && cur.c1 == N-1) || (cur.r2 == N-1 && cur.c2 == N-1)) {
                return cur.time;
            }
            
            List<Robot> nextMoves = getNextMoves(cur);
            for (Robot next : nextMoves) {
                if (next.r1 < 0 || next.r1 >= N || next.c1 < 0 || next.c1 >= N 
                    || next.r2 < 0 || next.r2 >= N || next.c2 < 0 || next.c2 >= N
                    || board[next.r1][next.c1] == 1 || board[next.r2][next.c2] == 1
                    || visited[next.r1][next.c1][next.dir]) {
                    continue;
                }
                
                queue.offer(next);
                visited[next.r1][next.c1][next.dir] = true;
            }
        }
        
        return -1;
    }
    
    private List<Robot> getNextMoves(Robot robot) {
        List<Robot> moves = new ArrayList<>();
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        // 상하좌우 이동
        for (int[] dir : dirs) {
            int nr1 = robot.r1 + dir[0], nc1 = robot.c1 + dir[1];
            int nr2 = robot.r2 + dir[0], nc2 = robot.c2 + dir[1];
            moves.add(new Robot(nr1, nc1, nr2, nc2, robot.dir, robot.time + 1));
        }
        
        // 회전
        if (robot.dir == HORIZONTAL) {
            if (robot.r1 > 0 && board[robot.r1-1][robot.c1] == 0 && board[robot.r1-1][robot.c2] == 0) {
                moves.add(new Robot(robot.r1-1, robot.c1, robot.r1, robot.c1, VERTICAL, robot.time + 1));
                moves.add(new Robot(robot.r1-1, robot.c2, robot.r1, robot.c2, VERTICAL, robot.time + 1));
            }
            if (robot.r1 < N-1 && board[robot.r1+1][robot.c1] == 0 && board[robot.r1+1][robot.c2] == 0) {
                moves.add(new Robot(robot.r1, robot.c1, robot.r1+1, robot.c1, VERTICAL, robot.time + 1));
                moves.add(new Robot(robot.r1, robot.c2, robot.r1+1, robot.c2, VERTICAL, robot.time + 1));
            }
        } else {
            if (robot.c1 > 0 && board[robot.r1][robot.c1-1] == 0 && board[robot.r2][robot.c1-1] == 0) {
                moves.add(new Robot(robot.r1, robot.c1-1, robot.r1, robot.c1, HORIZONTAL, robot.time + 1));
                moves.add(new Robot(robot.r2, robot.c1-1, robot.r2, robot.c1, HORIZONTAL, robot.time + 1));
            }
            if (robot.c1 < N-1 && board[robot.r1][robot.c1+1] == 0 && board[robot.r2][robot.c1+1] == 0) {
                moves.add(new Robot(robot.r1, robot.c1, robot.r1, robot.c1+1, HORIZONTAL, robot.time + 1));
                moves.add(new Robot(robot.r2, robot.c1, robot.r2, robot.c1+1, HORIZONTAL, robot.time + 1));
            }
        }
        
        return moves;
    }
    
    class Robot {
        int r1, c1, r2, c2, dir, time;
        
        Robot(int r1, int c1, int r2, int c2, int dir, int time) {
            this.r1 = r1; this.c1 = c1;
            this.r2 = r2; this.c2 = c2;
            this.dir = dir;
            this.time = time;
        }
    }
}