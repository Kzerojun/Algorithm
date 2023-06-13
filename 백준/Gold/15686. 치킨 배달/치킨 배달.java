import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[][] city;
    static List<Pair> houses;
    static List<Pair> chickenRestaurants;
    static boolean[] selected;
    static int minChickenStreet = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        city = new int[N][N];
        houses = new ArrayList<>();
        chickenRestaurants = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                city[i][j] = scanner.nextInt();
                if (city[i][j] == 1) {
                    houses.add(new Pair(i, j));
                } else if (city[i][j] == 2) {
                    chickenRestaurants.add(new Pair(i, j));
                }
            }
        }

        selected = new boolean[chickenRestaurants.size()];
        selectChickenRestaurants(0, 0);
        System.out.println(minChickenStreet);

        scanner.close();
    }

    private static void selectChickenRestaurants(int idx, int count) {
        if (count == M) {
            minChickenStreet = Math.min(minChickenStreet, calculateChickenStreet());
            return;
        }

        if (idx == chickenRestaurants.size()) {
            return;
        }

        selected[idx] = true;
        selectChickenRestaurants(idx + 1, count + 1);

        selected[idx] = false;
        selectChickenRestaurants(idx + 1, count);
    }

    private static int calculateChickenStreet() {
        int sumChickenStreet = 0;

        for (Pair house : houses) {
            int minDistance = Integer.MAX_VALUE;

            for (int i = 0; i < chickenRestaurants.size(); i++) {
                if (selected[i]) {
                    int distance = Math.abs(house.x - chickenRestaurants.get(i).x)
                            + Math.abs(house.y - chickenRestaurants.get(i).y);
                    minDistance = Math.min(minDistance, distance);
                }
            }

            sumChickenStreet += minDistance;
        }

        return sumChickenStreet;
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}