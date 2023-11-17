import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int result = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T= 10;
		
		for(int tc = 1; tc<=T; tc++) {
			int max = Integer.MIN_VALUE;
			int[][] graph = new int[100][100];
			int N = Integer.parseInt(br.readLine());
			for(int i = 0 ; i<100; i++ ) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j<100; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i<100; i++) {
				int sum = Arrays.stream(graph[i]).sum();
				int sum2 = 0;
				for(int j = 0; j<100; j++) {
					sum2+=graph[j][i];
				}
				max = Math.max(max, sum);
				max = Math.max(max,sum2);
			}
			int sum3= graph[0][0];
			int x = 0;
			int y = 0;
			while(true) {
			    x = x+1;
			    y = y+1;
			    if(x>=100 || y>=100 || x<0 || y<0) break;
			    sum3 += graph[x][y];
			}
			max = Math.max(sum3, max);

			int sum4 = graph[99][0];
			x = 99;
			y = 0;
			while(true) {
			    x = x-1;
			    y = y+1;
			    if(x>=100 || y>=100 || x<0 || y<0) break;
			    sum4 += graph[x][y];
			}
			max = Math.max(sum4, max);
			
			System.out.printf("#%d %d",tc,max);
			System.out.println();
			
		
			
		}
		}
}
