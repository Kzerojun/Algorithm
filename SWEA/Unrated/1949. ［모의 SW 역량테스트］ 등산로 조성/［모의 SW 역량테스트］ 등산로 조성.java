import java.util.*;
import java.io.*;


class Solution{
	static int T;
	static int N;
	static int K;
	static int maxHeightLoad;
	static int result;
	static BufferedReader br;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[]args) throws IOException{
		init();
		play();
	}
	
	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
	}
	
	private static void play() throws IOException {
		
		for(int testcase = 1; testcase<=T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			maxHeightLoad = Integer.MIN_VALUE;
			List<Location> loads = new ArrayList<>();
			int[][] graph = new int[N][N];
			result = Integer.MIN_VALUE;
			initGraph(graph);
			findStartLoad(graph,loads);

			for(Location load : loads) {

				int[][] tmp = new int[N][N];
				copyGraph(graph,tmp);
				boolean[][] visited = new boolean[N][N];
				visited[load.x][load.y] = true;
				dfs(tmp,1,load,false,visited);
			
			}
			
	
			System.out.println("#"+testcase+" "+result);
		}
	}
	
	private static void initGraph(int[][]graph) throws IOException {
		for(int i = 0 ; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				maxHeightLoad = Math.max(maxHeightLoad, graph[i][j]);
			}
		}
	}
	
	private static void findStartLoad(int[][] graph, List<Location> loads) {
		for(int i = 0 ; i<N; i++) {
			for(int j = 0 ; j<N; j++) {
				if(graph[i][j] == maxHeightLoad) {
					loads.add(new Location(i,j));
				}
			}
		}
	}
	
	
	private static void dfs(int[][]graph,int buildLoad,Location now, boolean cut,boolean[][] visited) {
		
		result = Math.max(buildLoad, result);

		for(int i = 0 ; i<4; i++) {
			int nx = now.x + dx[i];
			int ny = now.y + dy[i];
			
			if(nx<0 || ny<0 || ny>=N || nx>=N) continue;
			
			
				if(graph[nx][ny]>= graph[now.x][now.y]) {
					if(!cut) {
						if(!visited[nx][ny]) {
						for(int cutCount = 1 ; cutCount<= K; cutCount++) {
							if(graph[nx][ny]-cutCount< graph[now.x][now.y]) {
								
								graph[nx][ny] -= cutCount;
								visited[nx][ny] = true;
								dfs(graph,buildLoad+1,new Location(nx,ny),true,visited);
								graph[nx][ny] += cutCount;
								visited[nx][ny] = false;
								
							}
						}
						}
					}
				}else {
					if(!visited[nx][ny]) {
						visited[nx][ny] = true;
						dfs(graph,buildLoad+1,new Location(nx,ny),cut,visited);
						visited[nx][ny] = false;
					}
				}
			


			
		}
	}
	
	private static void copyGraph(int[][] graph, int[][] tmp) {
		for(int i = 0 ; i<N; i++) {
			for(int j = 0 ; j<N; j++) {
				tmp[i][j] = graph[i][j];
			}
		}
	}

}

class Location{
	int x;
	int y;
	
	Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
}