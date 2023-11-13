import java.io.*;
import java.util.*;

class Solution
{	
	public static void main(String args[]) throws Exception{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = 10;
		
		for(int tc=1; tc<=testCase; tc++) {
			int[] road = new int[100];
			int dump = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i<road.length; i++) {
				road[i] = Integer.parseInt(st.nextToken());
			}
			
			Index max = new Index(Integer.MIN_VALUE,-1);
			Index min = new Index(Integer.MAX_VALUE,-1);
			
			for(int j = 0; j<dump; j++) {
				findMaxMin(road, max, min);
				dump(road,max,min);
				findMaxMin(road, max, min);
			}
			
			System.out.printf("#%d %d\n",tc,road[max.index]-road[min.index]);
		}
		
	}
	
	public static void findMaxMin(int[]road, Index max, Index min) {
		max.number = Integer.MIN_VALUE;
		min.number = Integer.MAX_VALUE;
		for(int i = 0; i<road.length; i++) {
			if(road[i] > max.number) {
				max.number = road[i];
				max.index = i;
			}
			if(road[i] < min.number) {
				min.number = road[i];
				min.index = i;
			}
		}
	}
	
	public static void dump(int[]road,Index max, Index min) {
		road[max.index]--;
		road[min.index]++;
	}
	
}
class Index{
	int number;
	int index;
	Index(int number, int index){
		this.number = number;
		this.index = index;
	}
}
