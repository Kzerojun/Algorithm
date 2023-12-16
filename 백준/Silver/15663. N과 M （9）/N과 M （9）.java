import javax.management.MBeanNotificationInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{
    static boolean[] visited;
    static int[] arr;
    static int N,M;
    static Set<String> set;

    public static void main(String[]args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        visited = new boolean[N];
        set = new LinkedHashSet<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        solution(0,0,"");

        for (String x : set) {
            System.out.println(x);
        }


    }

    static void solution(int index, int count, String string) {
        if (count == M) {
            set.add(string);
            return;
        }

        for (int i = 0; i < N; i++) {
            if(!visited[i]){
                visited[i] = true;
                solution(i+1,count+1,string+arr[i]+" ");
                visited[i] = false;
            }

        }
    }


}