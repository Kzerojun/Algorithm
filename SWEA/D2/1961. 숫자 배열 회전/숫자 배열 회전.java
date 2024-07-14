import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); // 테케
		
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine()); // 맵 가로 세로
			
			int[][] arr = new int[N][N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) arr[i][j] = Integer.parseInt(st.nextToken());
			}
			
			// 이전 모양의 배열을 90도씩 돌리기
			int[][] arr90 = rotation(arr);
			int[][] arr180 = rotation(arr90);
			int[][] arr270 = rotation(arr180);
			
            System.out.println("#"+(t+1));
			for(int i=0; i<N; i++) {
				// 출력해야할 건 90도, 180도, 270도 세 개뿐
				for(int j=0; j<N; j++)	System.out.print(arr90[i][j]);
				System.out.print(" ");
				
				for(int j=0; j<N; j++) 	System.out.print(arr180[i][j]);
				System.out.print(" ");
				
				for(int j=0; j<N; j++) 	System.out.print(arr270[i][j]);
				
				System.out.println("");
			}
		}
	 } 
	
	// 회전 계산 메서드
	public static int[][] rotation(int[][] beforeArr){
		
		int[][] resultArr = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				resultArr[i][j] = beforeArr[beforeArr.length-1-j][i];
			}
		}
		
		return resultArr;
	}
}