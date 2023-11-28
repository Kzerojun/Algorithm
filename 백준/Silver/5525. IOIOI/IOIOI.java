import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int sol = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder p = new StringBuilder("IOI");
        while (--N > 0) {
            p.append("OI");
        }
        String comparison = p.toString();
        int S = Integer.parseInt(br.readLine());
        String string = br.readLine();

        for (int i = 0; i <= string.length() - comparison.length(); i++) {
            check(i, string, comparison);
        }
        System.out.println(sol);
    }

    static void check(int index, String string, String comparison) {
        boolean check = true;
        int count = 0;
        for (int x = index; x < index + comparison.length(); x++) {
            if (comparison.charAt(count) != string.charAt(x)) {
                check = false;
                break;
            }
            count++;
        }

        if (check) {
            sol++;
        }
    }
}
