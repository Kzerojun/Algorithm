import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] alphabet = new int[26];
        String word = br.readLine();

        for(int i = 0; i<word.length(); i++){
            int num = word.charAt(i) - 'A';
            alphabet[num]++;
        }
        int odd =0, index = -1;
        for(int i = 0; i<26; i++){
            if(alphabet[i]%2!=0){
                odd++;
                index = i;
            }
        }

        if(odd>1){
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < alphabet[i] / 2; j++) {
                sb.append((char) (i + 'A'));
            }
        }
        if (index != -1) {
            sb.append((char) (index + 'A'));
        }
        for (int i = 25; i >= 0; i--) {
            for (int j = 0; j < alphabet[i] / 2; j++) {
                sb.append((char) (i + 'A'));
            }
        }

        System.out.println(sb);
    }
}