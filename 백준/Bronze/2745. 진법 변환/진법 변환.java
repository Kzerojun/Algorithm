import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());
        int sum = 0;

        for(int i = 0; i < N.length(); i++){
            char c = N.charAt(i);
            int digit;
            if (Character.isDigit(c)) {
                digit = c - '0';  // 숫자인 경우 0부터 9까지의 값으로 변환
            } else {
                digit = c - 'A' + 10;  // 알파벳인 경우 10부터 35까지의 값으로 변환
            }
            sum += digit * Math.pow(B, N.length() - 1 - i);
        }
        System.out.println(sum);
    }
}