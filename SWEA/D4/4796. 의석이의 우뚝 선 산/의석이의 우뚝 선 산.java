
import java.util.*;
import java.io.*;

public class Solution {
	static Scanner sc;
	static int T;
	static int N;
	static int[] heights;
	static List<Integer> mountains;
	static int result;
	
	public static void main(String[] args) throws IOException{
		init();
		start();
	}

	private static void init() throws IOException {
		
		sc = new Scanner(System.in);
		T = sc.nextInt();
	}

	private static void start() throws IOException {
		for (int t = 1; t <= T; t++) {
			N =  sc.nextInt();
			
			heights = new int[N];
			

			for(int i = 0 ; i<N; i++) {
				heights[i] = sc.nextInt();
			}
			
			mountains = new ArrayList<>();
			findMountains();
			result = 0;
			for(int index : mountains) {
				findSection(index);
			}
			
			System.out.println("#"+t+" "+result);
			
			
		}
	}
	
	private static void findMountains() {
		for(int i = 1; i<N-1; i++) {
			int mid = heights[i];
			int left = heights[i-1];
			int right = heights[i+1];
			
			if(left<mid && mid>right) {
				mountains.add(i);
			}
		}
	}
	
	private static void findSection(int index) {
		
		int left = index -1;
		int right = index +1;
		
		int leftcount = 1;
	
		
		while(true) {
			int next = left -1;
			if(next<0) {
				break;
			}
			
			if(heights[next]<heights[left]) {
				left--;
				leftcount++;
			}else {
				break;
			}
		}
		
		int rightcount = 1;
		
		while(true) {
			int next = right +1;
			if(next>=N) {
				break;
			}
			
			if(heights[next]<heights[right]) {
				right++;
				rightcount++;
			}else {
				break;
			}
		}
		
		result = result + leftcount*rightcount;
	}
}
