import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main{
	static int N;
	static long[] result;
	static int[] heights;

	public static void main(String[] args) throws  IOException{
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		heights = new int[N];
		result = new long[N];


		//높이 입력, i가 빌딩 번호
		for (int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(br.readLine());
		}

		Stack<Building> stack = new Stack<>();

		for (int i = N - 1; i >= 0; i--) {
			if (stack.isEmpty()) {
				//높이 넣기
				stack.add(new Building(i,heights[i]));
				result[i] = 0;
			}else {
				while (true) {
					if (!stack.isEmpty() && stack.peek().height < heights[i] ) {
						Building building = stack.pop();
						result[i] += result[building.number] +1;
					}else {
						stack.add(new Building(i, heights[i]));
						break;
					}
				}
			}
		}

		long sum = 0;

		for (int i = 0; i < N; i++) {
			sum += result[i];
		}
		System.out.println(sum);
	}


}

class Building{
	int number;
	int height;

	Building(int number, int height) {
		this.number= number;
		this.height = height;
	}
}
