import java.util.*;

class Solution {
    private static boolean[][] visited;
    private static int N, M;
    private static Player playerA, playerB;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int sol = Integer.MAX_VALUE;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        init(board, aloc, bloc);
        Result result = play(playerA, playerB, 0, true);
        return result.moveCount;
    }

    private static Result play(Player a, Player b, int moveCount, boolean isATurn) {
        boolean win = false;
        int minWin = Integer.MAX_VALUE;
        int maxWin = Integer.MIN_VALUE;
        int check = 0;

        if (isATurn && visited[a.x][a.y] || !isATurn && visited[b.x][b.y]) {
            return new Result(false, moveCount);
        }

        for (int i = 0; i < 4; i++) {
            if (isATurn) {
                int nx = a.x + dx[i];
                int ny = a.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
                    check++;
                    continue;
                }
                visited[a.x][a.y] = true;
                Result result = play(new Player(nx, ny), b, moveCount + 1, !isATurn);
                visited[a.x][a.y] = false;

                if (!result.canWin) {
                    win = true;
                    minWin = Math.min(minWin, result.moveCount);
                } else {
                    maxWin = Math.max(maxWin, result.moveCount);
                }
            } else {
                int nx = b.x + dx[i];
                int ny = b.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
                    check++;
                    continue;
                }
                visited[b.x][b.y] = true;
                Result result = play(a, new Player(nx, ny), moveCount + 1, !isATurn);
                visited[b.x][b.y] = false;

                if (!result.canWin) {
                    win = true;
                    minWin = Math.min(minWin, result.moveCount);
                } else {
                    maxWin = Math.max(maxWin, result.moveCount);
                }
            }
        }

        if (check == 4) {
            return new Result(false, moveCount);
        }

        return new Result(win, win ? minWin : maxWin);
    }

    private static void init(int[][] board, int[] aloc, int[] bloc) {
        N = board.length;
        M = board[0].length;

        playerA = new Player(aloc[0], aloc[1]);
        playerB = new Player(bloc[0], bloc[1]);

        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = board[i][j] == 0;
            }
        }
    }
}

class Result {
    boolean canWin;
    int moveCount;

    Result(boolean canWin, int moveCount) {
        this.canWin = canWin;
        this.moveCount = moveCount;
    }
}

class Player {
    int x;
    int y;

    Player(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
