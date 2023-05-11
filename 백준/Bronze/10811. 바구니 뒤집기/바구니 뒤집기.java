import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];

        for( int j =1; j <= N; j++){
            arr[j] = j;
        }

        for(int i =0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int I = Integer.parseInt(st.nextToken());
            int J = Integer.parseInt(st.nextToken());
            while(I<J) {
                int start = arr[I];
                int end = arr[J];
                int temp = start;
                arr[I] = end;
                arr[J] = temp;
                I++;
                J--;
            }
        }
        for(int x =1; x<=N; x++) {
            bw.write(Integer.toString(arr[x])+" ");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}