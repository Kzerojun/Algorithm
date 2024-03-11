import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		List<Line> lines = new ArrayList<>();
		parent = new int[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			lines.add(new Line(x, y,i));
			parent[i] = i;
		}

		List<CostLine> costLines = new ArrayList<>();

		for (int i = 0; i < lines.size(); i++) {
			for (int j = i + 1; j < lines.size(); j++) {
				int from = i;
				int to = j;

				double cost = Math.sqrt(Math.pow(lines.get(to).x-lines.get(from).x,2)+
						Math.pow(lines.get(to).y-lines.get(from).y,2));

				costLines.add(new CostLine(from, to, cost));
			}
		}

		PriorityQueue<CostLine> pq = new PriorityQueue<>((o1, o2) -> {
			return Double.compare(o1.cost, o2.cost);
		});

		pq.addAll(costLines);
		double solution = 0;
		while (!pq.isEmpty()) {
			CostLine now = pq.poll();

			if (find(now.from) == find(now.to)) {
				continue;
			}

			union(now.from, now.to);
			solution += now.cost;
		}

		System.out.printf("%.2f",solution);
	}

	static void union(int a, int b) {
		int x = find(a);
		int y = find(b);

		if(x ==y) return;

		if(x<y) parent[y] =  x;
		else parent[x] = y;
	}

	static int find(int x) {
		if(parent[x]==x) return x;
		return parent[x] = find(parent[x]);
	}
}
class CostLine {
	int to;
	int from;
	double cost;

	CostLine(int to, int from, double cost) {
		this.to = to;
		this.from =from;
		this.cost = cost;
	}
}

class Line{
	double x;
	double y;
	int number;
	Line(double x, double y,int number) {
		this.x = x;
		this.y = y;
		this.number = number;
	}
}