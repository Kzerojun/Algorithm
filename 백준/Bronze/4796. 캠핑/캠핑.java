import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 1;
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), P = Integer.parseInt(st.nextToken()), V = Integer.parseInt(st.nextToken());
            int count = 0;

            if(N == 0 && P == 0 && V == 0) break;
            if(V%P<N){
                count = count + (V/P)*N+(V%P);
            }
            else count = count + (V/P)*N+N;
            System.out.printf("Case %d: %d\n",num,count);
            num++;


        }
        br.close();
    }
}