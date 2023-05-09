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
        StringTokenizer st;
        int[] arr = new int[10];
        int count = 0;

        for(int i = 0; i<10; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken()) %42;
        }
        for(int j= 0; j <10; j++){
            
            int tmp = 0;
            for( int k =j+1; k <10; k++){
                if(arr[j]==arr[k]){
                    tmp++;
                }

            }
            if(tmp==0){
                count++;
            }

        }
        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }
}