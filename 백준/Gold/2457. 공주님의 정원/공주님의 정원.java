import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{

	static int N;
	static Info[] infos;

	static int START_DAY = 301;
	static int END_DAY = 1130;

	public static void main(String[] args)  throws IOException{
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		infos = new Info[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int startMonth = Integer.parseInt(st.nextToken());
			int startDay = Integer.parseInt(st.nextToken());
			int start = startMonth * 100 + startDay;

			int endMonth = Integer.parseInt(st.nextToken());
			int endDay = Integer.parseInt(st.nextToken());
			int end = endMonth * 100 + endDay;

			infos[i] = new Info(start, end);
		}

		Arrays.sort(infos,(o1,o2)->{
			if (o1.start == o2.start) {
				return o2.end - o1.end;
			}

			return o1.start - o2.start;
		});


		int now = 301;
		int count = 0;
		int index = 0;

		while(now<1200) {

			boolean check = false;
			int max = -1;
			for (int i = index; i < N; i++) {
				if(infos[i].start>now) break;

				if (infos[i].end > now) {
					check = true;
					max = Math.max(max,infos[i].end);
				}
			}

			if (!check) {
				break;
			}else {
				count++;
				now = max;
			}

		}

		if(now<=1130) {
			System.out.println(0);
		}else {
			System.out.println(count);
		}


	}
}

class Info{
	int start;
	int end;

	Info(int start, int end) {
		this.start = start;
		this.end = end;
	}
}