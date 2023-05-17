import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[][] arr = new char[5][15];
        int num = 0;

        for(int i =0; i<5; i++){
            String line = br.readLine();
            if(num<line.length()) num = line.length();

            for (int j =0; j<line.length(); j++){
                arr[i][j] = line.charAt(j);
            }
        }
        for(int i =0; i<num; i++){
            for (int j =0; j<5; j++){
                if(arr[j][i] == '\0') continue;
                bw.write((arr[j][i]));
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }

}