import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = N - 1;
		int min = Integer.MAX_VALUE;
		int[] ans = new int[2];

		while(left<right) {
            int tmp = arr[left] + arr[right];
            
            if(Math.abs(tmp)<min) {
                min = Math.abs(tmp);
                ans[0] = left;
                ans[1] = right;
            }
            
            if(tmp<0) {
                left++;
            }else {
                right--;
            }
        }


		System.out.println(arr[ans[0]] + " " + arr[ans[1]]);
	}
}
