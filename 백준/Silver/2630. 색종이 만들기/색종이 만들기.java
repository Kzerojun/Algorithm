import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static int N;
    static int blue = 0;
    static int white = 0;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cut(N, 0, 0);
        System.out.println(white);
        System.out.println(blue);
    }

    static void cut(int size, int x, int y) {
        if (size < 1) {
            return;
        }
        int blueCount = 0;
        int whiteCount = 0;
        for (int i = x; i < size + x; i++) {
            for (int j = y; j < size + y; j++) {
                if (arr[i][j] == 1) {
                    blueCount++;
                    continue;
                }
                whiteCount++;
            }
        }
        if (blueCount == size * size) {
            blue++;
            return;
        }
        if (whiteCount == size * size) {
            white++;
            return;
        }

        cut(size / 2, x, y);
        cut(size / 2, x, y + size / 2);
        cut(size / 2, x + size / 2, y);
        cut(size / 2, x + size / 2, y + size / 2);
    }


}
