import java.util.*;
import java.io.*;

class Solution{
	static BufferedReader br;
	static int[][] graph;
	static Location start;
	
	// 좌 상 우
	static int[] dx = {0,-1,0};
	static int[] dy = {-1,0,1};
	
	public static void main(String[]args) throws IOException {
		init();
		start();
	}
	
	private static void init() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	private static void start() throws IOException {
		
		for(int t = 1; t<=10 ; t++) {
			int testNumber = Integer.parseInt(br.readLine());
			initGraph();
			int result = calculate();
			
			System.out.println("#"+testNumber+" "+result);
		}
		
	}
	
	private static void initGraph() throws IOException{
		graph = new int[100][100];
		
		for(int i = 0 ; i<100; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ;j <100; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j]==2) {
					start = new Location(i,j,1);
				}
			}
		}
	}
	
	
	private static int calculate() {
		Queue<Location> q = new LinkedList<>();
		q.add(start);
		boolean[][] visited = new boolean[100][100];
		visited[start.x][start.y] = true;
		
		while(!q.isEmpty()) {
			Location now = q.poll();
			
			if(now.x == 0) {
				return now.y;
			}
			
			int check =isOtherLadder(now.x,now.y,now.direction,visited);
			
			if(check!= -1) {
				now.direction = check;
			}
			
			
			
			
			int nx = dx[now.direction] + now.x;
			int ny = dy[now.direction] + now.y;
			
			if(visited[nx][ny]) continue;
			visited[nx][ny] = true;
			q.add(new Location(nx,ny,now.direction));
		}
		
		return -1;
	}
	
	private static int isOtherLadder(int x, int y, int direction, boolean[][]visited) {
		for(int i = 0 ; i<3; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || ny<0 || nx>=100 || ny>=100) continue;
			if(graph[nx][ny]==1 && !visited[nx][ny] && i!=direction) {
				return i;
			}
			
		}
		
		return -1;
	}
}


class Location{
	int x;
	int y;
	int direction;
	
	Location(int x, int y,int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
}