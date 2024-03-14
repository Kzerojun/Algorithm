import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		long minSum = Long.MAX_VALUE;

		int[] answer = new int[3]; // 0 left 1 mid 2 right

		for (int left = 0; left < N - 2; left++) {
			int mid = left+1;
			int right = N-1;

			while (mid < right) {
				long sum = (long) arr[left] + arr[mid] + arr[right];
				if (Math.abs(sum) < minSum) {
					answer[0] = left;
					answer[1] = mid;
					answer[2] = right;
					minSum = Math.abs(sum);
				}

				if (sum < 0) {
					mid++;
				}else{
					right --;
				}
			}
		}

		System.out.println(arr[answer[0]]+" "+arr[answer[1]]+" "+arr[answer[2]]);
	}
}