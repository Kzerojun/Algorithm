import java.util.*;

class Solution {
    static int[][] graph;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        init();
        drawGraph(rectangle);

        return play(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
    }

    private static void init() {
        graph = new int[102][102];  // 좌표 확장 후의 최대 크기 반영
    }

    private static void drawGraph(int[][] rectangle) {
        for (int[] point : rectangle) {
            fillGraph(point[0] * 2, point[1] * 2, point[2] * 2, point[3] * 2);
        }
    }

    private static void fillGraph(int x1, int y1, int x2, int y2) {
        for (int i = x1 + 1; i < x2; i++) {  // 내부를 2로 채움 (x1+1 ~ x2-1, y1+1 ~ y2-1)
            for (int j = y1 + 1; j < y2; j++) {
                graph[i][j] = 2;
            }
        }
        
        // 테두리를 1로 덮어씀 (내부가 아닌 부분만 1로 처리)
        for (int i = x1; i <= x2; i++) {
            if (graph[i][y1] != 2) graph[i][y1] = 1;
            if (graph[i][y2] != 2) graph[i][y2] = 1;
        }
        for (int j = y1; j <= y2; j++) {
            if (graph[x1][j] != 2) graph[x1][j] = 1;
            if (graph[x2][j] != 2) graph[x2][j] = 1;
        }
    }

    private static int play(int chx, int chy, int goalX, int goalY) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[102][102];
        q.add(new Node(chx, chy, 0));
        visited[chx][chy] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == goalX && now.y == goalY) {
                return now.moveCount / 2;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 2 || ny < 2 || nx > 100 || ny > 100) continue;
                if (graph[nx][ny] != 1) continue;  // 테두리(1)만 탐색
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.add(new Node(nx, ny, now.moveCount + 1));
            }
        }

        return -1;
    }
}

class Node {
    int x;
    int y;
    int moveCount;

    Node(int x, int y, int moveCount) {
        this.x = x;
        this.y = y;
        this.moveCount = moveCount;
    }
}
