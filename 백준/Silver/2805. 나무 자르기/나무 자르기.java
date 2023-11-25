import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] trees;
    static int maxLength = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trees = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            maxLength = Math.max(trees[i], maxLength);
        }

        System.out.println(binarySearch());
    }

    static int binarySearch(){
        int start = 0;
        int end = maxLength;

        while (start <= end) {
            int mid = (start + end) / 2;
            long sum = 0;

            for (int tree : trees) {
                if (tree > mid)
                    sum += tree - mid;
            }

            if (sum >= M)
                start = mid + 1;
            else
                end = mid - 1;
        }

        return end;
    }
}
