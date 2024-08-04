import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class  Main{

	static int N,M;
	static int[] dx = {-1,0,1,0}; //상 우 하 좌
	static int[] dy = {0,1,0,-1};
	static int[][] graph;

	static Robot robot;

	public static void main(String[] args)throws IOException {
		init();
		play();
	}
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//N M 정보 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];

		//로봇 정보 입력
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int direction = Integer.parseInt(st.nextToken());
		robot = new Robot(x,y,direction,0);

		//그래프 정보 입력
		for(int i = 0 ; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void play() {
		Queue<Robot> q = new LinkedList<>();
		q.add(robot);

		while (!q.isEmpty()) {
			Robot now = q.poll();

//			System.out.println("현재 로봇 위치 와 방향"+now.x+" "+now.y+" "+now.direction);
//			System.out.println("현재 로봇이 청소한 방 갯수"+now.cleanRoom);

			if(graph[now.x][now.y]==0) {
				graph[now.x][now.y] = -1;
				q.add(new Robot(now.x,now.y,now.direction, now.cleanRoom+1));
				continue;
			}


			if(isExistRoom(now)) {

				while(true) {
					now.direction = (now.direction+3)%4;
					int nx = now.x + dx[now.direction];
					int ny = now.y + dy[now.direction];

					if(graph[nx][ny] == 0) {
						q.add(new Robot(nx, ny, now.direction, now.cleanRoom));
						break;
					}
				}



			}else {
				int nx = now.x - dx[now.direction];
				int ny = now.y - dy[now.direction];

				if(nx < 0 || ny<0 || nx>=N || ny>= M || graph[nx][ny]==1)  {
					System.out.println(now.cleanRoom);
					return;
				}

				q.add(new Robot(nx,ny,now.direction,now.cleanRoom));
			}

		}
	}

	private static boolean isExistRoom(Robot robot) {

		for(int i = 0 ; i<4; i++) {
			int nx = robot.x + dx[i];
			int ny = robot.y + dy[i];

			if(nx< 0 || ny<0 || nx>=N || ny>=M || graph[nx][ny]==1) continue;

			if(graph[nx][ny]==0){
				return true;
			}
		}

		return false;
	}

}

class Robot{
	int x;
	int y;
	int direction;
	int cleanRoom;

	Robot(int x, int y, int direction, int cleanRoom) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.cleanRoom = cleanRoom;
	}


}