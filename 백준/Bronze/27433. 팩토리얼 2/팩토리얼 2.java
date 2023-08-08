import java.io.*;
import java.nio.Buffer;

public class Main {
   static int sum;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long N = Integer.parseInt(br.readLine());

        bw.write(recursion(N)+"");

        br.close();
        bw.flush();
        bw.close();


    }

    static long recursion(long N){
        if(N==0||N==1){
            return 1;
        }
        else{
            return N*recursion(N-1);
        }



    }
}
