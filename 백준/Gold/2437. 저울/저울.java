import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr); // 저울추를 무게순으로 정렬
        
        int target = 1; // 측정할 수 없는 양의 정수 무게 중 최솟값
        for (int i = 0; i < N; i++) {
            // 현재 저울추가 target보다 작다면 target에 해당 추의 무게를 더함
            if (arr[i] <= target) {
                target += arr[i];
            } else {
                break; // 더 이상 측정할 수 없는 무게이므로 반복 종료
            }
        }

        System.out.println(target);
    }
}