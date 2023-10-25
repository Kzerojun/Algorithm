import com.sun.security.jgss.GSSUtil;
import java.awt.Point;
import java.util.*;
import java.io.*;
import org.w3c.dom.ls.LSOutput;

public class Main {

    static int[][] board;
    static int N;
    static Queue<Node3190> direction = new LinkedList<>();
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0}; //좌 상 우 하
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        board = new int[N][N];

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            board[A][B] = 1; // 1이 사과
        }

        int L = Integer.parseInt(br.readLine());

        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            String B = st.nextToken();
            direction.offer(new Node3190(A, B));
        }

        soltuion();
        System.out.println(count);
    }

    static void soltuion() {
        Deque<Point3190> snake = new LinkedList<>();
        snake.offer(new Point3190(0,0));

        int index = 2;

        while(true){
            Point3190 current = snake.peekLast();

            int nx = current.x + dx[index];
            int ny = current.y + dy[index];

            if(nx>=N || ny >=N || nx<0 || ny <0) {
                count+=1;
                break;
            }
            if(board[nx][ny]==2){
                count++;
                break;
            }

            if(board[nx][ny] == 0){
                snake.offerLast(new Point3190(nx,ny));
                board[nx][ny] = 2;
                board[snake.peekFirst().x][snake.peekFirst().y] = 0;
                snake.pollFirst();
                count++;
            }

            if(board[nx][ny] == 1){
                snake.offerLast(new Point3190(nx,ny));
                board[nx][ny] = 2;
                count++;
            }

            if (!direction.isEmpty() && count == direction.peek().count) {
                if (direction.peek().d.equals("L")) {
                    index = (index - 1 < 0) ? 3 : index - 1;
                } else if (direction.peek().d.equals("D")) {
                    index = (index + 1 > 3) ? 0 : index + 1;
                }
                direction.poll(); // 방향 정보를 큐에서 제거
            }



        }

    }
}

class Node3190 {

    int count;
    String d;

    Node3190(int count, String direction) {
        this.count = count;
        this.d = direction;
    }
}

class Point3190 {

    int x;
    int y;

    Point3190(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
