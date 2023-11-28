import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String S = br.readLine();

        int result = 0;
        int pattern = 0; 

        for(int i=0; i<M-2; i++) {
            if(S.charAt(i) == 'I' && S.charAt(i+1) == 'O' && S.charAt(i+2) == 'I') {
                pattern++;
                if(pattern == N) {
                    pattern--;
                    result++;
                }
                i++;
            } else {
                pattern = 0;
            }
        }
        System.out.println(result);
    }
}
