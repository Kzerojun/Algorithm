import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Arrays;


public class Main{
public static void main(String[] args)throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    Integer [] arr = new Integer[N];
    for(int i =0; i<N; i++){
        arr[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(arr,Comparator.reverseOrder());
    int total = 0;
    for(int i = 0 ; i<N; i++){
        total = Math.max(total,arr[i]*(i+1));
    }
    System.out.println(total);
}
}