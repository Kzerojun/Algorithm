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
        String s = br.readLine();
        int num =0;
        for(int i =0; i <s.length(); i++){
            char a = s.charAt(i);
            if(a == 'A' || a=='B' || a=='C') num= num+3;
            else if ((a == 'D' || a=='E' || a=='F')) num = num+4;
            else if ((a == 'G' || a=='H' || a=='I')) num = num+5;
            else if ((a == 'J' || a=='K' || a=='L')) num = num+6;
            else if ((a == 'M' || a=='N' || a=='O')) num = num+7;
            else if ((a == 'P' || a=='Q' || a=='R' || a=='S')) num = num+8;
            else if ((a == 'T' || a=='U' || a=='V')) num = num+9;
            else if ((a == 'W' || a=='X' || a=='Y' || a=='Z')) num = num+10;
        }
        bw.write(num +"");
        br.close();
        bw.flush();
        bw.close();

    }
}