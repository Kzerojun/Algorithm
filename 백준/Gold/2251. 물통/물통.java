import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	private static int A,B,C;
	private static boolean[][][] visited;
	private static int[] pourSize;
	private static TreeSet<Integer> results;
	
	public static void main(String[] args) throws IOException{
		simulate();
	}
	
	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		
		pourSize = new int[] {A,B,C};
		visited = new boolean[A+1][B+1][C+1];
		results = new TreeSet<>((o1,o2)-> {
			return o1 - o2;
		});
		
		dfs(0,0,C);
		

		
		results.stream()
		.forEach(result -> System.out.print(result+" "));
		
	}
	
    private static void dfs(int a, int b, int c) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    int[] next = pour(new int[] { a, b, c }, i, j);
                    if (!visited[next[0]][next[1]][next[2]]) {
                        visited[next[0]][next[1]][next[2]] = true;
                        if(next[0]==0) {
                        	results.add(next[2]);
                        }
                        dfs(next[0], next[1], next[2]);
                    }
                }
            }
        }
    }
	
    private static int[] pour(int[] state, int from, int to) {
        int[] next = state.clone();

     
        int amount = Math.min(state[from], pourSize[to] - state[to]);
        next[from] -= amount;
        next[to] += amount;

        return next;
    }
}
