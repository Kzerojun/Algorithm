import java.util.*;
import java.io.*;

class Main{
	static int G,P;
	static int[] parent;
	
	
	public static void main(String[] args) throws IOException {
		simulate();
	}
	
	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		
		parent = new int[G+1];
		for(int i = 0; i<=G; i++) {
			parent[i] = i;
		}
		
		int count = 0;
		
		for(int i =0 ; i<P; i++) {
			int number = Integer.parseInt(br.readLine());
			
			int gate = find(number);
			
			if(gate == 0 ) {
				break;
			}
			
			union(gate, gate - 1);
			count++;
		}
		
		System.out.println(count);
		
		
	}
	
	private static void union(int A, int B) {
		int x = find(A);
		int y = find(B);
		
		if(x == y) return;
		
		if(x<y) parent[y] = x;
		else parent[x]  =y;
		
	}
	
	private static int find(int number) {
		if(parent[number]==number) return number;
		return parent[number] = find(parent[number]);
	}
}