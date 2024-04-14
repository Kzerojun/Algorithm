import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main{

	static int[] house;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//집의 개수 N, 공유기의 개수 C
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		house = new int[N];
		for(int i = 0; i < N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(house);	// 이분탐색을 하기 위해선 반드시 정렬 되어있어야 한다.

		int minLength = 1;
		int maxLength = house[N - 1] - house[0];

		while (minLength < maxLength) {
			int mid = (minLength + maxLength) / 2;

			if (cal(mid) < C) {
				maxLength = mid;
			}else {
				minLength = mid+1;
			}
		}

		System.out.println(minLength);

	}

	static int cal(int distance) {
		int count = 1;
		int lastLocation = house[0];

		for (int i = 1; i < house.length; i++) {
			int houseLocation = house[i];

			if (houseLocation - lastLocation > distance) {
				count++;
				lastLocation = houseLocation;
			}
		}

		return count;
	}

}