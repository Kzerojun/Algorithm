import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static char[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;

		for (int tc = 1; tc <= T; tc++) {
			arr = new char[100][100];
			br.readLine();
			for (int i = 0; i < 100; i++) {
				String input = br.readLine();
				for (int j = 0; j < 100; j++) {
					arr[i][j] = input.charAt(j);
				}
			}
			for(int len = 100; len>0; len--) {
				if(findPal(len)) {
					 System.out.println("#" + tc + " " + len);
					 break;
				}
			}
			

		}

	}
	
	static boolean findPal(int len) {
		for(int i = 0; i<100; i++) {
			for(int j =0; j<=100-len; j++) {
				boolean row = true;
				boolean col = true;
				
				for(int k =0; k<len/2; k++) {
					if(arr[i][j+k]!=arr[i][j+len-1-k]) row = false;
					if(arr[j+k][i]!=arr[j+len-1-k][i]) col = false;
				}
				if(row || col) return true;
			}
		}
		
		return false;
	}

}
