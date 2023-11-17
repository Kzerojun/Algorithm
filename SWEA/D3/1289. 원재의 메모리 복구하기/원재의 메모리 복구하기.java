import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int count = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			String input = br.readLine();
			int length = input.length();
			int[] reset = new int[length];
			int[] result = new int[length];
			count = 0;
			for(int i = 0; i<result.length; i++) {
				result[i] = input.charAt(i) - '0';
			}
			
			for(int i = 0; i<length; i++) {
				copy(reset,result,i);
				if(isSame(reset,result)) {
					break;
				}
			}
			
			System.out.printf("#%d %d",tc,count);
			System.out.println();
			
			
			
		}
	}
	
	static void copy(int[] reset, int[] result, int index) {
		if(reset[index] != result[index]) {
			for(int i = index; i<result.length; i++) {
				reset[i] = result[index];
				
			}
			count++;
		}
		
	}
	
	static boolean isSame(int[]reset, int[]result) {
		if(reset == result) {
			return true;
		}
		return false;
	}

}
