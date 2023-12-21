import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N, M;
    static int[] arr;
    static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        set = new LinkedHashSet<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        combination(0,0,"");

        for (String string : set) {
            System.out.println(string);
        }
    }

    static void combination(int index, int count, String string) {
        if (count == M) {
            set.add(string);
            return;
        }

        for (int i = index; i < N; i++) {
            combination(i,count+1,string+arr[i]+" ");
        }
    }


}

