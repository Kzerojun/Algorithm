import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class Main {
	static boolean[][] visited;
	static int N;
	static List<Paper> papers;
	
	public static void main(String[] args) throws IOException {
		init();
		play();
	}
	
	private static void init()throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[101][101];
		papers = new ArrayList<>();
		
		//색종이 정보 입력하기
		for(int i = 0 ; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			papers.add(new Paper(x,y));
		}
	}
	
	private static void play() {
		for(Paper paper : papers) {
			int startX = paper.x;
			int startY = paper.y;
			
			for(int i = startX; i< startX+10; i++) {
				for(int j = startY; j<startY+10; j++) {
					visited[i][j] = true;
				}
			}
		}
		
		
		int result = 0;
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		for(int i = 0 ; i<=100; i++) {
			for(int j = 0 ; j<=100; j++) {
				
				if(visited[i][j]) {
					for(int k = 0 ; k<4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						
						if(nx<0 || ny<0 || nx>100 || ny>100) continue;
						if(!visited[nx][ny]) {
							result++;
						}
					}
				}
				
			}
		}
		
		System.out.println(result);
	}
}

class Paper {
	int x;
	int y;
	
	Paper(int x, int y) {
		this.x =x;
		this.y = y;
	}
}
