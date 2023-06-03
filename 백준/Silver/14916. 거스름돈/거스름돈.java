import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int count =0;
        while(N>0){

            N=N-5;
            count++;
            if(N%2!=0 && N<5){
                N = N+5;
                count--;
                break;
            }

        }
        if(N%2 ==0&&N>0) {
            count +=N/2;
            N=0;
        }
        if(N!=0) System.out.println(-1);
        else System.out.println(count);

    }
}