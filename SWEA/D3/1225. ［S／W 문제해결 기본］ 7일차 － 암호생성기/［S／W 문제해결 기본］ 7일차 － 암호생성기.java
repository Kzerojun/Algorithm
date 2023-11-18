import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
		static int[] discount = {1,2,3,4,5};
		
		public static void main(String[]args) throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int T = 10;
			
			for(int tc = 1; tc<=10; tc++) {
				List<Integer> arr = new ArrayList<>();
				br.readLine();
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for(int i = 0; i<8; i++) {
					arr.add(Integer.parseInt(st.nextToken()));
				}
				
				int index = 0;
				while(true) {
					if(arr.get(7)==0) break;
					int num = arr.remove(0)-discount[index%5];
					if(num<=0) {
						num =0;
					}
					arr.add(num);
					index++;
				}
				
				System.out.printf("#%d ",tc);
				for(int x : arr) {
					System.out.print(x+" ");
				}
				System.out.println();
			}
			

		}
}
