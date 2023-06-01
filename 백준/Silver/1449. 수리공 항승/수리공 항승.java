import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int count = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        Queue<Integer> q = new LinkedList<>();
        q.add(arr[0]+L-1);
        for(int i =1 ; i<N; i++){
            if(q.peek()<arr[i]){
                q.poll();
                q.add(arr[i]+L-1);
                count++;
            }
        }
        System.out.println(count+1);




    }
}