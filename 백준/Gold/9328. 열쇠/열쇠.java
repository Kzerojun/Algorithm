import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};

	static int h;
	static int w;
	static int sol;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int testCount = Integer.parseInt(br.readLine());

		for (int t = 0; t < testCount; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			sol = 0;
			char[][] map = new char[h + 2][w + 2];
			boolean[][] visited = new boolean[h + 2][w + 2];
			Set<Character> keySets = new TreeSet<>();

			for (int i = 0; i < h + 2; i++) {
				map[i][0] = '.';
				map[i][w + 1] = '.';
			}

			for (int j = 0; j < w + 2; j++) {
				map[0][j] = '.';
				map[h + 1][j] = '.';
			}


			for (int i = 1; i <= h; i++) {
				String string = br.readLine();
				for (int j = 1; j <= w; j++) {
					map[i][j] = string.charAt(j-1);
				}
			}

//			for (int i = 0; i < h+2; i++) {
//				for (int j = 0; j < w+2; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}

			String keys = br.readLine();
			for (int i = 0; i < keys.length(); i++) {
				char key = keys.charAt(i);
				keySets.add(key);
			}

			move(keySets, visited, map);

			bw.write(sol + "\n");
			bw.flush();

		}

		bw.close();
	}

	static void move(Set<Character> keySets, boolean[][] visited, char[][] map) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		visited[0][0] = true;

		while (!q.isEmpty()) {
			Point now = q.poll();
//			System.out.println("현재 위치" + now.x + " " + now.y);
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx > h + 1 || ny > w + 1 || visited[nx][ny]
						|| map[nx][ny] == '*') {
					continue;
				}

				if (map[nx][ny] == '$') { // 문서
//					System.out.println("문서를 획득했습니다" + nx +" "+ny);
					sol++;
					map[nx][ny] = '.';
					visited[nx][ny] = true;
					q.add(new Point(nx, ny));
				} else if (Character.isLowerCase(map[nx][ny])) { // 열쇠
					if (!keySets.contains(map[nx][ny])) {
//						System.out.println("열쇠를 획득했습니다" + nx +" "+ny);
						keySets.add(map[nx][ny]);
						map[nx][ny] = '.';
						q.clear();
						visited = new boolean[h+2][w+2];
						q.add(new Point(0, 0));
					}else {
						q.add(new Point(nx, ny));
						visited[nx][ny] = true;
					}
				} else if (Character.isUpperCase(map[nx][ny])) {
//					System.out.println("문에 도착했습니다" + nx +" "+ny);
					if (keySets.contains(Character.toLowerCase(map[nx][ny]))) {
//						System.out.println("문의 키가 있습니다." + nx +" "+ny);
						map[nx][ny] = '.';
						visited[nx][ny] = true;
						q.add(new Point(nx, ny));
					}
				} else if (map[nx][ny] == '.') {
					visited[nx][ny] = true;
					q.add(new Point(nx, ny));
				}
			}

		}

	}
}

class Point {

	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;

	}
}