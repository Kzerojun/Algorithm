import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import javax.sound.sampled.Line;

class Main {
	static int[] parent;
	static int totalPrice;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		List<Edge> edges = new ArrayList<>();

		Point[] xArr = new Point[N];
		Point[] yArr = new Point[N];
		Point[] zArr = new Point[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			xArr[i] = new Point(x, i);
			yArr[i] = new Point(y, i);
			zArr[i] = new Point(z, i);
		}

		Arrays.sort(xArr);
		Arrays.sort(zArr);
		Arrays.sort(yArr);

		for (int i = 0; i < N-1; i++) {
			int xMinPrice = Math.abs(xArr[i].point - xArr[i+1].point);
			int yMinPrice = Math.abs(yArr[i].point - yArr[i+1].point);
			int zMinPrice = Math.abs(zArr[i].point - zArr[i+1].point);

			edges.add(new Edge(xArr[i].planetNumber,xArr[i+1].planetNumber,xMinPrice));
			edges.add(new Edge(yArr[i].planetNumber,yArr[i+1].planetNumber,yMinPrice));
			edges.add(new Edge(zArr[i].planetNumber,zArr[i+1].planetNumber,zMinPrice));
		}

		parent = new int[N];
		totalPrice = 0;

		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}


		makeTunnel(edges);

		bw.write(totalPrice+"");
		bw.flush();
		bw.close();

	}


	static void makeTunnel(List<Edge> edges) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
			return o1.price - o2.price;
		});

		pq.addAll(edges);

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if(find(now.from)==find(now.to)) continue;

			union(now.from, now.to);
			totalPrice += now.price;
		}
	}

	static void union(int x, int y) {
		int a = find(x);
		int b = find(y);

		if(a==b) return;

		if(a<b) parent[b] = a;
		else parent[a] = b;
	}

	static int find(int number) {
		if(parent[number]==number) return number;
		return parent[number] = find(parent[number]);
  	}
}



class Edge {
	int from;
	int to ;
	int price;

	Edge(int from, int to, int price) {
		this.from = from;
		this.to = to;
		this.price = price;
	}

}

class Point implements Comparable<Point>{
	int point;
	int planetNumber;

	Point(int point, int planetNumber) {
		this.point = point;
		this.planetNumber = planetNumber;
	}

	public int compareTo(Point o) {
		return this.point - o.point;
	}
}