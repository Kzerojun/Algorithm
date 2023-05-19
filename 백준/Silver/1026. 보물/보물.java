import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Arrays;


public class Main{
public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        Integer[] B = new Integer[N];
        int sum =0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int j =0; j <N; j++)
            A[j] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int j =0; j <N; j++)
            B[j] = Integer.parseInt(st.nextToken());

        Arrays.sort(A);
        Arrays.sort(B, Comparator.reverseOrder());
        int[] C = Arrays.stream(B).mapToInt(Integer::intValue).toArray();

        for(int i =0; i<N; i++){
            sum = sum + (A[i] * B[i]);
        }
    System.out.println(sum);
        br.close();
    }
}