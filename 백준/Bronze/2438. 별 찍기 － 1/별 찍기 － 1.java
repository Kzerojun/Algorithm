

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String star = "*";
        for (int i = 1; i <=n; i ++){
            bw.write(star+"\n");
            star = star + "*";
        }
        br.close();
        bw.flush();
        bw.close();

    }

}