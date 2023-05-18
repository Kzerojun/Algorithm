import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int count =0;
        for(int i =0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int j = N-1; j>=0; j--){
            if(K/arr[j]>0){
            count = count +K/arr[j];
            K = K%arr[j];
            }
        }
        bw.write(count +"");
        br.close();
        bw.flush();
        bw.close();

    }

}