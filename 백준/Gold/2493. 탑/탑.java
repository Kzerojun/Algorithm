import java.util.*;
import java.io.*;

class Main{
	static int[] towers;
	static int[] results;
	
	static int N;
	
	public static void main(String[]args) throws IOException {
		init();
		play();
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		towers = new int[N];
		results = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0 ; i<N; i++) {
			towers[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private static void play() {
	
		Stack<Tower> stack = new Stack<>();
		
		stack.push(new Tower(0,towers[0]));
		results[0] = 0;
		
		for(int i = 1; i<N; i++) {
			int tower = towers[i];
			boolean check = false;
			
			while(!stack.isEmpty()) {
				Tower now = stack.pop();
				
				if(now.height> tower) {
					check = true;
					stack.push(now);
					stack.push(new Tower(i,tower));
					results[i] = now.index+1;
					break;
				}
			}
			
			if(!check) {
				results[i] = 0;
				stack.push(new Tower(i,tower));
			}
			
			
		}
		
		for(int i = 0 ; i<N; i++) {
			System.out.print(results[i]+" ");
		}
	}
	
	
}

class Tower{
	int index;
	int height;

	Tower(int index, int height) {
		this.index = index;
		this.height = height;
	}
}
