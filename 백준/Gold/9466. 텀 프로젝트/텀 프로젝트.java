import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static int cycle;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			int studentCount = Integer.parseInt(br.readLine());
			int[] students = new int[studentCount+1];
			cycle = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= studentCount; j++) {
				students[j] = Integer.parseInt(st.nextToken());
			}

			boolean[] visited = new boolean[studentCount+1];
			boolean[] finished = new boolean[studentCount+1];
			for (int start = 1; start <= studentCount; start++) {
				if(!visited[start]){
					move(start, visited, finished, students);
				}

			}
			System.out.println(studentCount - cycle);
		}
	}

	static void move(int current,boolean[] visited, boolean[]finished,int[] students) {
		if(visited[current]) return;

		visited[current] = true;
		int next = students[current];

		if (!visited[next]) {
			move(next,visited,finished,students);
		} else {
			if (!finished[next]) {
				cycle++;
				while (next != current) {
					cycle++;
					next = students[next];
				}
			}
		}

		finished[current] = true;

	}
}