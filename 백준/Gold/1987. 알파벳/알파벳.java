import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static char[][] board;
    static int R, C;
    static int maxCount = 0;
    static HashSet<Character> visitedCharacters = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = row.charAt(j);
            }
        }

        dfs(0, 0, 1); // 시작 위치 (0,0)에서 시작하므로 초기 count를 1로 설정

        System.out.println(maxCount);
    }

    static void dfs(int x, int y, int count) {
        if (count > maxCount) {
            maxCount = count;
        }

        visitedCharacters.add(board[x][y]);

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visitedCharacters.contains(board[nx][ny])) {
                dfs(nx, ny, count + 1);
            }
        }

        visitedCharacters.remove(board[x][y]); // Backtrack: 현재 위치에서 빠져나오면 해당 문자를 다시 사용할 수 있도록 제거
    }
}