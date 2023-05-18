import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int temp =0;
        int temp2 =0;
        int sum =0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i< N; i++){
            arr[i]  = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i<N; i++){
            for(int j =i+1; j<N; j++){
                if(arr[i] > arr[j]){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }

            }
        }

        for(int i =0; i<N; i++){
            sum = sum +arr[i] + temp2;
            temp2 = temp2 +arr[i];

        }
        bw.write(sum +"");
        br.close();
        bw.flush();
        bw.close();

    }

}