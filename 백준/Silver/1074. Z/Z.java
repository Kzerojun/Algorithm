import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static int[] dx = {0, 0, 1, 1};
    static int[] dy = {0, 1, 0, 1};
    static int N, r, c;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int size = (int) Math.pow(2, N);

        z(size, 0, 0);

    }

    static void z(int size, int x, int y) {
        if(r==x&&c==y){
            System.out.println(count);
            return;
        }

        if (r < x + size && c < y + size && r >= x && c >= y) {
            z(size / 2, x, y);
            z(size / 2, x, y + size / 2);
            z(size / 2, x + size / 2, y);
            z(size / 2, x + size / 2, y + size / 2);
        } else{
            count += size * size;
        }

    }

}



