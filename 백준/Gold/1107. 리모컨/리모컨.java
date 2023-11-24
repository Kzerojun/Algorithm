import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    static List<Integer> list;
    static String target;
    static int solution = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int channel = 100;
        int[] button = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        target = br.readLine();

        int M = Integer.parseInt(br.readLine());

        if (M != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int num = Integer.parseInt(st.nextToken());
                button[num] = -1;
            }
        }

        list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            if (button[i] != -1) {
                list.add(button[i]);
            }
        }
        int result = 0;
        dfs("", 0);
        int buttonMinus = Math.abs(channel - Integer.parseInt(target));
        solution = Math.min(buttonMinus, solution);
        System.out.println(solution);
    }

    static void dfs(String s, int length) {
        if (length > 6) {
            return;
        }

        if (length >= 1) {
            int num = Integer.parseInt(s);
            int minus = Math.abs(Integer.parseInt(target) - num);
            int click = s.length();
            solution = Math.min(solution, minus + click);
        }

        for (int i = 0; i < list.size(); i++) {
            dfs(s + list.get(i), length + 1);
        }
    }


}



