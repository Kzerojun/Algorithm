import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
	private static List<int[]> primeNumbers;
	private static boolean[] isPrime;
	private static int[] visited;

	public static void main(String[] args) throws IOException {
		init();

		for (int[] number : primeNumbers) {
			visited = new int[10000];
			calculate(number[0],number[1]);
		}

	}

	public static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		primeNumbers = new ArrayList<>();

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			primeNumbers.add(new int[]{start, end});
		}

		isPrime = new boolean[10000];
		for (int i = 0; i < isPrime.length; i++) {
			isPrime[i] = true;
		}

		isPrime[0] = false;
		isPrime[1] = false;

		for (int i = 2; i < isPrime.length; i++) {
			if(isPrime[i]) {
				for (int j = i * i; j < isPrime.length; j += i) {
					isPrime[j] = false;
				}
			}
		}


	}

	public static void calculate(int start, int end) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		boolean check = false;

		while (!q.isEmpty()) {
			int now = q.poll();

			if (now == end) {
				check = true;
				break;
			}

			int thousands = now / 1000;            // 천의 자리
			int hundreds = (now / 100) % 10;       // 백의 자리
			int tens = (now / 10) % 10;            // 십의 자리
			int ones = now % 10;                   // 일의 자리

			for (int i = 0; i < 10; i++) {
				if (i != thousands) {
					int newThousands = i * 1000 + hundreds * 100 + tens * 10 + ones;
					if (visited[newThousands] == 0 && isPrime[newThousands] && newThousands>=1000) {
						visited[newThousands] = visited[now] +1;
						q.add(newThousands);
					}
				}

				if (i != hundreds) {
					int newHundreds = thousands * 1000 + i * 100 + tens * 10 + ones;
					if (visited[newHundreds] == 0 && isPrime[newHundreds]&&newHundreds>=1000) {
						visited[newHundreds]  = visited[now] +1;
						q.add(newHundreds);
					}
				}

				if (i != tens) {
					int newTens = thousands * 1000 + hundreds * 100 + i * 10 + ones;
					if (visited[newTens] == 0 && isPrime[newTens]&&newTens>=1000) {
						visited[newTens]  = visited[now] +1;
						q.add(newTens);
					}
				}

				if (i != ones) {
					int newOnes = thousands * 1000 + hundreds * 100 + tens * 10 + i;
					if (visited[newOnes] == 0 && isPrime[newOnes]&&newOnes>=1000) {
						visited[newOnes]  = visited[now] +1;
						q.add(newOnes);
					}
				}
			}
		}

		if (check) {
			System.out.println(visited[end]);
			return;
		}

		System.out.println("Impossible");

	}
}