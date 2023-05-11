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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        for(int i = 0; i< num; i++){
            String S = br.readLine();
            bw.write(S.charAt(0)+"");
            bw.write((S.charAt(S.length()-1)+""));
            bw.newLine();
        }
        
        br.close();
        bw.flush();
        bw.close();
    }
}