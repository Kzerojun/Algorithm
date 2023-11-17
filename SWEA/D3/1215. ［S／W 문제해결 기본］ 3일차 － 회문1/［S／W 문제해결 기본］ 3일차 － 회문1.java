import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int result = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T= 10;

		for(int tc = 1; tc<=T; tc++) {
			int size = Integer.parseInt(br.readLine());
			String[][] graph = new String[8][8];
			result = 0;
			for(int i = 0; i<8; i++) {
				String input = br.readLine();
				for(int j = 0; j<8; j++) {
					graph[i][j] = Character.toString(input.charAt(j));
				}
			}

			for(int i = 0; i<8; i++) {
				for(int j = 0; j<=8 - size; j++) {
					String horizontal = "";
					String vertical = "";
					for(int k = 0; k< size; k++) {
						horizontal += graph[i][j+k];
						vertical += graph[j+k][i];
					}
					isPalindrome(horizontal);
					isPalindrome(vertical);
				}
			}
			System.out.printf("#%d %d\n",tc,result);
		}
	}

	static void isPalindrome(String input) {
		int len = input.length();
		for(int i = 0; i<len/2; i++) {
			if(input.charAt(i)!=input.charAt(len-1-i)) {
				return;
			}
		}
		result++;
	}
}
