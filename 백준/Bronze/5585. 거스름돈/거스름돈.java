import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Arrays;


public class Main{
public static void main(String[] args)throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int remain = 1000 - N;
    int[] count = {500, 100, 50, 10, 5, 1};
    int cnt = 0;
    for(int i =0; i<6; i++){
        if(remain/count[i]!=0){
            cnt = cnt + remain/count[i];
            remain = remain%count[i];
        }

    }
    System.out.println(cnt);
    br.close();
}
}