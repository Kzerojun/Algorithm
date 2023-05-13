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


        for(int i =0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            String s = st.nextToken();
            for(int x = 0; x<s.length(); x++) {
                for (int k = 0; k < R; k++) {
                    bw.write(s.charAt(x) + "");
                }
            }
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();

    }
}