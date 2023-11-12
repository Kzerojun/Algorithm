import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int swapCount;
    static int[] arr;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String number = st.nextToken();
            swapCount = Integer.parseInt(st.nextToken());

            arr = new int[number.length()];
            for (int i = 0; i < number.length(); i++) {
                arr[i] = number.charAt(i) - '0';
            }

            max = Integer.MIN_VALUE;
            solve(0, 0);
            System.out.printf("#%d %d ", tc, max);
            System.out.println();
        }
    }

    static void solve(int count, int index) {
        if (count == swapCount) {
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                sum = sum * 10 + arr[i];
            }
            max = Math.max(sum, max);
            return;
        }

        for (int i = index; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {

                    swap(i, j);
                    solve(count + 1, i);
                    swap(j, i);
                
            }
        }
    }

    static void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
