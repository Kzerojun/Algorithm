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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] num1 = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N;i++){
            num1[i] = Integer.parseInt(st.nextToken());
        }
        int max1 = num1[0];
        int min1 = num1[0];

        for( int j = 0; j<N;j++){
            if(num1[j] < min1){
                min1 = num1[j];
            }
            if(num1[j]> max1){
                max1 = num1[j];
            }
        }
        bw.write(Integer.toString(min1)+" "+ Integer.toString(max1));
        br.close();
        bw.flush();
        bw.close();
    }

}