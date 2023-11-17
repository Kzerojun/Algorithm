import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    static int result = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for (int tc = 1; tc <= T; tc++) {
            int lines = sc.nextInt();
            int[][] table = new int[lines][lines];
            result = 0;
            List<Magnetic> magnets = new ArrayList<>();

            for (int i = 0; i < lines; i++) {
                for (int j = 0; j < lines; j++) {
                    table[i][j] = sc.nextInt();
                    if (table[i][j] != 0) {
                        magnets.add(new Magnetic(i, j, table[i][j]));
                    }
                }
            }

            while (!magnets.isEmpty()) {
                List<Magnetic> tmp = new ArrayList<>();
                for (Magnetic magnet : magnets) {
                    int x = magnet.x;
                    int y = magnet.y;
                    int pole = magnet.pole;
                    if (pole == 1) { // N극
                        int nx = x + 1;
                        if (nx >= lines || table[nx][y] != 0) continue;
                        table[nx][y] = pole;
                        table[nx - 1][y] = 0;
                        magnet.x = nx;
                        tmp.add(magnet);
                    } else if (pole == 2) { // S극
                        int nx = x - 1;
                        if (nx < 0) continue;
                        if (table[nx][y] != 0) {
                            if (table[nx][y] == 1) {
                                result++;
                            }
                            continue;
                        }
                        table[nx][y] = pole;
                        table[nx + 1][y] = 0;
                        magnet.x = nx;
                        tmp.add(magnet);
                    }
                }
                magnets = tmp;
            }

            System.out.println("#" + tc + " " + result);
        }

        sc.close();
    }
}

class Magnetic {
    int x;
    int y;
    int pole;

    Magnetic(int x, int y, int pole) {
        this.x = x;
        this.y = y;
        this.pole = pole;
    }
}
