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
        int[] num1 = new int[9];

        for(int i = 0; i<9;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            num1[i] = Integer.parseInt(st.nextToken());
        }
        int max = num1 [0];
        int point = 1;
        for ( int j = 0; j<9; j++){
            if(num1[j]>max){
                max = num1[j];
                point = j+1;
            }
        }
        bw.write(Integer.toString(max));
        bw.newLine();
        bw.write(Integer.toString(point));
        br.close();
        bw.flush();
        bw.close();
    }

}