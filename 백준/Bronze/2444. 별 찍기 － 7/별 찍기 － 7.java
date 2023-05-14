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
        int N = Integer.parseInt(br.readLine());
        for(int i=1; i<N;i++){
            for(int x =0; x<N-i; x++){
                bw.write(" ");
            }
            for(int k = 0; k<i*2-1; k++){
                bw.write("*");
            }
            bw.newLine();

        }
        for(int i=N; i>0; i--){
            for(int x = N-i; x>0; x--){
                bw.write(" ");
            }
           
            for(int k = 0; k<i*2-1; k++){
                bw.write("*");
            }
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();

    }
}