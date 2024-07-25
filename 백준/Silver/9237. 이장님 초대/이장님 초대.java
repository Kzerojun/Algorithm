import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = 2;
        int res = 0;
        Integer arr[] = new Integer[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        Arrays.sort(arr, Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            res = Math.max(res, cnt + arr[i]);
            cnt++;
        }

        System.out.println(res);
    }
}