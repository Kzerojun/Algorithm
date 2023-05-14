import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String S = br.readLine();
        int result = 1;
        for(int i =0; i <S.length()/2; i++){
            if(S.charAt(i) != S.charAt(S.length()-1-i)) {
                result = 0;
                break;
            }

        }
        bw.write(result +"");
        br.close();
        bw.flush();
        bw.close();
    }
}