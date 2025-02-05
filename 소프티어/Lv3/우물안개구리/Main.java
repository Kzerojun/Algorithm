import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> relations;
    static int[] dp;
    static int[] weights;
    static int result;
    static int N,M;

    public static void main(String[] args) throws IOException {
        init();
        simulate();
    }

    private static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        weights = new int[N];
        relations = new ArrayList<>();
        
        for(int i = 0 ; i<=N; i++) {
            relations.add(new ArrayList<>());   
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i<N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;

            relations.get(from).add(to);
            relations.get(to).add(from);
        }

        result = 0;
    }

    private static void simulate() {
        for(int i = 0 ; i<N; i++) {
            bfs(i);
        }
        System.out.println(result);
    }

    private static void bfs(int index) {
        boolean check = true;
        
        for(int next : relations.get(index)) {
            if(weights[index] <= weights[next]) {
                check = false;
                break;
            }
        }

        if(check) {
            result++;
        }
    }

}
