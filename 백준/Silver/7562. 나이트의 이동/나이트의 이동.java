import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Node{
    int x;
    int y;
    Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Main {

    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCount; i++) {
            int l = Integer.parseInt(br.readLine());
            Node start, end;

            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            start = new Node(startX, startY);

            st = new StringTokenizer(br.readLine());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            end = new Node(endX, endY);

            int solution = bfs(start, end, l);
            System.out.println(solution);
        }
    }

    static int bfs(Node start, Node end, int size) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visit = new boolean[size][size];

        queue.offer(start);
        visit[start.x][start.y] = true;

        int count = 0;
        boolean found = false;

        while (!queue.isEmpty()) {
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; i++) {
                Node current = queue.poll();

                if (current.x == end.x && current.y == end.y) {
                    found = true;
                    break;
                }

                for (int j = 0; j < 8; j++) {
                    int ddx = current.x + dx[j];
                    int ddy = current.y + dy[j];

                    if (ddx >= 0 && ddx < size && ddy >= 0 && ddy < size && !visit[ddx][ddy]) {
                        queue.offer(new Node(ddx, ddy));
                        visit[ddx][ddy] = true;
                    }
                }
            }

            if (found) {
                break;
            }

            count++;
        }

        return count;
    }
}