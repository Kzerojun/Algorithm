import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<String, Integer> map = new HashMap<>();
        int index = 1;
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] arr = new String[N+1];
        
        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            arr[i+1] = name;
            map.put(name,index);
            index++;
        }
        for(int i = 0; i<M; i++){
            String input = br.readLine();
            if(map.containsKey(input)){
                System.out.println(map.get(input));
                continue;
            }
            System.out.println(arr[Integer.parseInt(input)]);

        }
    }


}



