import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = br.readLine();
        int count = 0;

        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);

            if (i < word.length() - 1) {
                char nextChar = word.charAt(i + 1);

                if ((currentChar == 'c' && nextChar == '=') ||
                        (currentChar == 'c' && nextChar == '-')) {
                    count++;
                    i++;
                } else if (currentChar == 'd' && nextChar == 'z' && i < word.length() - 2 && word.charAt(i + 2) == '=') {
                    count++;
                    i += 2;
                } else if (currentChar == 'd' && nextChar == '-') {
                    count++;
                    i++;
                } else if (currentChar == 'l' && nextChar == 'j') {
                    count++;
                    i++;
                } else if (currentChar == 'n' && nextChar == 'j') {
                    count++;
                    i++;
                } else if (currentChar == 's' && nextChar == '=') {
                    count++;
                    i++;
                } else if (currentChar == 'z' && nextChar == '=') {
                    count++;
                    i++;
                } else {
                    count++;
                }
            } else {
                count++;
            }
        }

        bw.write(count + "");
        br.close();
        bw.flush();
        bw.close();
    }
}