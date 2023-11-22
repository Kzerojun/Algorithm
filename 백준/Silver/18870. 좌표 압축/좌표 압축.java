import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static int N;
    static int[] arr;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        result = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> map = new TreeMap<>();

        for(int i = 0; i<N; i++){
            int number = Integer.parseInt(st.nextToken());
            arr[i] = number;
            map.put(arr[i],0);
        }
        int idx = 0;
        for (int num : map.keySet()) {
            map.put(num, idx++);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(map.get(arr[i])).append(' ');
        }

        System.out.println(sb);
    }


}
