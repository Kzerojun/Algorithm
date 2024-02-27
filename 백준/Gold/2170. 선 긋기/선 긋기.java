import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Line> lines = new ArrayList<>();

		for(int i = 0 ; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			lines.add(new Line(start, end));
		}

		Collections.sort(lines);
		int start = lines.get(0).start;
		int end = lines.get(0).end;

		int solution = 0;
		for(int i = 1 ; i<lines.size(); i++) {
			int tmpStart = lines.get(i).start;
			int tmpEnd = lines.get(i).end;

			if (tmpStart <= end) {
				if (tmpEnd > end) {
					end = tmpEnd;
				}
			}

			if (tmpStart > end && tmpEnd > end) {
				solution += end - start;
				start = tmpStart;
				end = tmpEnd;
			}
		}
		solution += end-start;

		System.out.println(solution);


	}
}
class Line implements Comparable<Line>{
	int start;
	int end;

	Line(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Line o) {
		if (this.start == o.start) {
			return this.end - o.end;
		}

		return this.start - o.start;
	}
}