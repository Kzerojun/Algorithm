import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int count = 0;
        for(int i =0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int find = Integer.parseInt(br.readLine());
        for(int i =0; i<N; i++){
            if(arr[i]==find) {
                count = count + 1;
            }
            }
        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();

    }

}