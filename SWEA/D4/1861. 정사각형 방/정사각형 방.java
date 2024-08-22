import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br;
	static int T;
	static int N;
	static int[][] graph;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int roomNumber;
	static int result;
	
	public static void main(String[] args) throws IOException{
		init();
		start();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
	}

	private static void start() throws IOException {
		for (int t = 1; t <= T; t++) {
			initSimul();
			play();
			
			System.out.println("#"+t+" "+roomNumber+" "+result);
		}
	}

	private static void initSimul() throws IOException {
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		result =  Integer.MIN_VALUE;
		roomNumber = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

	}
	
	private static void play() {
		for(int i = 0 ; i<N; i++) {
			for(int j = 0 ; j<N;j++) {
				dfs(i,j,new boolean[N][N],1,graph[i][j]);
			}
		}
	}

	private static void dfs(int x, int y, boolean[][] visited, int moveCount,int originRoomNumber) {
		if(result < moveCount) {
			result = moveCount;
			roomNumber = originRoomNumber;
		}
		if(result == moveCount) {
			roomNumber = Math.min(roomNumber, originRoomNumber);
		}

		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx< 0 || ny< 0 || nx>=N || ny>=N) continue;
			if(visited[nx][ny]) continue;
			if(graph[nx][ny] == graph[x][y]+1) {
				visited[nx][ny] = true;
				dfs(nx,ny,visited,moveCount+1,originRoomNumber);
				visited[nx][ny] = false;
			}
			

		}

	}

}
