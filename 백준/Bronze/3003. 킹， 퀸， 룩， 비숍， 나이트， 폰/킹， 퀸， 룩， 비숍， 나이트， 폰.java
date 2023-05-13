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
        int[] arr = new int[6];
        arr[0] = 1;
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 2;
        arr[4] = 2;
        arr[5] = 8;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int [] arr2 = new int[6];
        for(int x =0; x<6; x++){
            arr2[x] = Integer.parseInt(st.nextToken());
        }
        for(int k =0; k<6; k++){
            if(arr[k] != arr2[k]) arr[k] = arr[k] - arr2[k];
            else arr[k] =0;
        }
        for(int c :arr){
            bw.write(c+" ");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}