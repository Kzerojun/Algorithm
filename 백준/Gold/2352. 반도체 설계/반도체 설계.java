import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	static int n;
	static int[] dp;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		init();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		dp = new int[n];
		arr = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int len = 0;
		for (int i = 0; i < n; i++) {
			if (len == 0 || dp[len - 1] < arr[i]) {
				dp[len++] = arr[i];
			}else {
				int index = binarySearch(arr[i], 0, len - 1, dp);
				dp[index] = arr[i];
			}
		}

		System.out.println(len);

	}

	private static int binarySearch(int target, int left, int right,int[] dp) {

		while(left<=right) {
			int mid = (left+right) / 2;

			if(dp[mid]<=target) {
				left = mid+1;
			}else {
				right = mid-1;
			}
		}
		return left;
	}
}
