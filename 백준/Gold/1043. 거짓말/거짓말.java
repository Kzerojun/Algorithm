import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	static int[] parent;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		boolean[] truePerson = new boolean[51];
		st = new StringTokenizer(br.readLine());
		
		int trueCount = Integer.parseInt(st.nextToken());
		for (int i = 0; i < trueCount; i++) {
			truePerson[Integer.parseInt(st.nextToken())] = true;
		}

		List<Integer>[] party = new ArrayList[M];
		int pre = 0;
		int value = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			party[i] = new ArrayList<>();
			int personCount = Integer.parseInt(st.nextToken());

			if (personCount > 0) {
				pre = Integer.parseInt(st.nextToken());
				party[i].add(pre);
			}
			for (int j = 1; j < personCount; j++) {
				value = Integer.parseInt(st.nextToken());
				party[i].add(value);
				union(pre, value);
				pre = value;
			}
		}

		for (int i = 1; i < truePerson.length; i++) {
			if (truePerson[i]) {
				truePerson[find(i)] = true;
			}
		}
		int total =0;
		int number = 0;

		for (int i = 0; i < M; i++) {
	if (!party[i].isEmpty()) {
		number = find(party[i].get(0));
		if(!truePerson[number]) total ++;
	}
}

		System.out.println(total);

	}

	public static boolean union(int a, int b) {
		int x = find(a);
		int y = find(b);

		if(x==y) return false;

		if(x<y) parent[y] = x;
		else parent[x] = y;

		return true;
	}

	public static int find(int x) {
		if(parent[x]==x) return x;
		return parent[x] = find(parent[x]);
 	}
}