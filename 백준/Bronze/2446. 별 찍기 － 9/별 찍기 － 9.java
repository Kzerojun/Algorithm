import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
 
 
		// 역삼각형 코드
		for (int i = 0; i < N; i++) {	// 1 ~ N
 
			// 공백
			for (int j = 0; j < i; j++) {
				sb.append(' ');
			}
 
			// 별
			for (int k = 0; k < (2 * N - 1) - (2 * i); k++) {
				sb.append('*');
			}
 
			sb.append('\n');
		}
 
 
		// 삼각형 코드
		for (int i = 0; i < N - 1; i++) {	// N+1 ~ 2N-1
        
			// 공백
			for (int j = 1; j < (N - 1) - i; j++) {
				sb.append(' ');
			}
 
			// 별
			for (int k = 0; k < 3 + 2 * i; k++) {
				sb.append('*');
			}
			sb.append('\n');
		}
        
        
		System.out.println(sb);
	}
 
}