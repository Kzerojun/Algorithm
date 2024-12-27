import java.util.*;

class Solution {

    // N : 행의 길이, M : 열의 길이
    static int N, M;
    static List<List<Integer>> rowCombination;
    static List<List<Integer>> colCombination;
    static int result;

    
    public int solution(int[][] beginning, int[][] target) {
        result = Integer.MAX_VALUE; // result를 매번 초기화
        init(beginning);
        makeCombination(0, N, new ArrayList<>(), true);
        makeCombination(0, M, new ArrayList<>(), false);
        return play(beginning, target);
    }

    private static void init(int[][] beginning) {
        N = beginning.length;
        M = beginning[0].length;
        rowCombination = new ArrayList<>();
        colCombination = new ArrayList<>();
    }

    private static int play(int[][] beginning, int[][] target) {
        for (List<Integer> row : rowCombination) {
            int[][] tmp = new int[N][M];
            copyGraph(tmp, beginning);
            flipRow(row, tmp);

            for (List<Integer> col : colCombination) {
                flipCol(col, tmp);

                if (isSame(tmp, target)) {
                    result = Math.min(result, row.size() + col.size());
                }

                flipCol(col, tmp);
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static void copyGraph(int[][] tmp, int[][] graph) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = graph[i][j];
            }
        }
    }

    private static boolean isSame(int[][] tmp, int[][] graph) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmp[i][j] != graph[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void flipRow(List<Integer> row, int[][] tmp) {
        for (int rowIndex : row) {
            for (int i = 0; i < M; i++) {
                tmp[rowIndex][i] = 1 - tmp[rowIndex][i];
            }
        }
    }

    private static void flipCol(List<Integer> col, int[][] tmp) {
        for (int colIndex : col) {
            for (int i = 0; i < N; i++) {
                tmp[i][colIndex] = 1 - tmp[i][colIndex];
            }
        }
    }

    private static void makeCombination(int index, int size, List<Integer> comb, boolean isRow) {
        if (index == size) {
            if (isRow) {
                rowCombination.add(new ArrayList<>(comb));
            } else {
                colCombination.add(new ArrayList<>(comb));
            }
            return;
        }

        // 현재 index를 선택하는 경우
        comb.add(index);
        makeCombination(index + 1, size, comb, isRow);

        // 현재 index를 선택하지 않는 경우
        comb.remove(comb.size() - 1);
        makeCombination(index + 1, size, comb, isRow);
    }
}
