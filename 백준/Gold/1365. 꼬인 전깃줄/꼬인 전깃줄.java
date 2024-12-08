

import java.util.*;
import java.io.*;

class Main{
	static int[] dp;
	static int[] arr;
	static int N;
	
	public static void main(String[] args) throws IOException {
		simulate();
	}
	
	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		
		int[] lis = new int[N];
		int len = 0;
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i<N; i++) {
			int number = Integer.parseInt(st.nextToken());
			if(len == 0 || lis[len-1] <number) {
				lis[len++] = number;
			}else {
				int index = binarySearch(number,0,len-1,lis);
				lis[index] = number;
			}
		}
		
		System.out.println(N -len);
	}
	
	
	private static int binarySearch(int target, int left, int right,int[] lis) {
		
		while(left<=right) {
			int mid = (left+right) / 2;
			
			if(lis[mid]<=target) {
				left = mid+1;
			}else {
				right = mid-1;
			}
		}
		
		return left;
	}
}