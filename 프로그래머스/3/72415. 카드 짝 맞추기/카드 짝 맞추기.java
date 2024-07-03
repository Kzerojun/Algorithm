import java.util.*;

class Solution {
    int[][] Board;
    static final int INF = 987756542;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public int solution(int[][] board, int r, int c) {
        Board = board;
        return permutate(new Point(r, c, 0));
    }

    int bfs(Point src, Point dst) {
        boolean[][] visited = new boolean[4][4];
        Queue<Point> q = new LinkedList<>();
        q.add(src);
        visited[src.row][src.col] = true;

        while (!q.isEmpty()) {
            Point curr = q.poll();

            if (curr.row == dst.row && curr.col == dst.col) {
                return curr.count;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + curr.row;
                int ny = dy[i] + curr.col;

                if (nx < 0 || ny < 0 || nx > 3 || ny > 3) continue;
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny, curr.count + 1));
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = curr.row;
                int ny = curr.col;
                while (true) {
                    int tx = nx + dx[i];
                    int ty = ny + dy[i];
                    if (tx < 0 || ty < 0 || tx > 3 || ty > 3) break;
                    nx = tx;
                    ny = ty;
                    if (Board[nx][ny] != 0) break;
                }

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny, curr.count + 1));
                }
            }
        }

        return INF;
    }

    int permutate(Point src) {
        int ret = INF;

        for (int num = 1; num <= 6; num++) {
            List<Point> card = new ArrayList<>();

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (Board[i][j] == num) {
                        card.add(new Point(i, j, 0));
                    }
                }
            }

            if (card.isEmpty()) {
                continue;
            }

            int one = bfs(src, card.get(0)) + bfs(card.get(0), card.get(1)) + 2;
            int two = bfs(src, card.get(1)) + bfs(card.get(1), card.get(0)) + 2;

            for (int i = 0; i < 2; i++) {
                Board[card.get(i).row][card.get(i).col] = 0;
            }

            ret = Math.min(ret, one + permutate(card.get(1)));
            ret = Math.min(ret, two + permutate(card.get(0)));

            for (int i = 0; i < 2; i++) {
                Board[card.get(i).row][card.get(i).col] = num;
            }
        }

        if (ret == INF) return 0;
        return ret;
    }

    class Point {
        int row, col, count;

        Point(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }
    }
}
