import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            bfs(A,B);
        }
    }

    public static void bfs(int A, int B) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(A);

        boolean[] visited = new boolean[10000];
        String[] command = new String[10000];

        Arrays.fill(command, "");
        visited[A] = true;

        
        while (!queue.isEmpty()) {
            int curNum = queue.poll();

            if (curNum == B) {
                System.out.println(command[curNum]);
                return;
            }

            int Dnum = D(curNum);
            int Snum = S(curNum);
            int Lnum = L(curNum);
            int Rnum = R(curNum);

            if (!visited[Dnum]) {
                visited[Dnum] = true;
                command[Dnum] = command[curNum] + "D";
                queue.offer(Dnum);
            }

            if (!visited[Snum]) {
                visited[Snum] = true;
                command[Snum] = command[curNum] + "S";
                queue.offer(Snum);
            }

            if (!visited[Lnum]) {
                visited[Lnum] = true;
                command[Lnum] = command[curNum] + "L";
                queue.offer(Lnum);
            }

            if (!visited[Rnum]) {
                visited[Rnum] = true;
                command[Rnum] = command[curNum] + "R";
                queue.offer(Rnum);
            }
        }
    }

    public static int D(int num) {
        return (num * 2) % 10000;
    }

    public static int S(int num) {
        return (num == 0)? 9999 : num - 1;
    }

    public static int L(int num) {
        return (num%1000)*10 + num/1000;
    }

    public static int R(int num) {
        return (num%10)*1000 + num/10;
    }

}
