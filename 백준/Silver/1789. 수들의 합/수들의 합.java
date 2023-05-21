import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long S = Long.parseLong(br.readLine());
        long sum = 0;
        long i = 1;
        long cnt = 0;
        while (sum+i<=S) {
            sum = sum + i;
            i++;
            cnt++;
        }
        System.out.println(cnt);
    }
}