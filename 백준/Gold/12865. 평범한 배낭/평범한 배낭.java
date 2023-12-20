import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static List<Item> items;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        items = new ArrayList<>();
        dp = new int[N+1][K+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            items.add(new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for (int i = 1; i <= N; i++) {
            Item item = items.get(i-1);
            for (int j = 0; j <= K; j++) {
                if(item.weight <= j)
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-item.weight] + item.value);
                else
                    dp[i][j] = dp[i-1][j];
            }
        }

        System.out.println(dp[N][K]);
    }
}

class Item {
    int weight;
    int value;

    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}
