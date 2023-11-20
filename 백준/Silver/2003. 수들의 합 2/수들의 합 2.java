import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int sol = 0;
    public static void main(String[]args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];

        for(int i = 0; i<arr.length; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        twoPoint(N,M,arr);
        System.out.println(sol);

    }

    static void twoPoint(int N,int M, int[] arr){
        int start = 0;
        int end = 0;
        int sum = 0;

        while(true){
            if(sum >= M) {
                sum -= arr[start++];
            } else if(end == N) {
                break;
            } else {
                sum += arr[end++];
            }
            if(sum == M){
                sol++;
            }
        }
    }
}
