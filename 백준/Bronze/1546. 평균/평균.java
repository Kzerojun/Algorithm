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
        st = new StringTokenizer(br.readLine());
        double[] arr = new double[N];

        for(int i = 0; i <N; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        double max = arr[0];
        for(int x= 0; x<N; x++){
            if(max <arr[x]) {
                max =arr[x];
            }
        }
        for(int k = 0; k <N; k ++){
            arr[k] = arr[k]/max*100;
        }
        bw.write(Arrays.stream(arr).sum()/N + " ");

        br.close();
        bw.flush();
        bw.close();
    }
}