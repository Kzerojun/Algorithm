import java.util.*;

class Solution {

    private static int maxWinCount = Integer.MIN_VALUE;
    private static List<Integer> bestCombination;

    public int[] solution(int[][] dice) {
        bestCombination = new ArrayList<>();
        findBestCombination(dice, 0, new ArrayList<>());


        int[] result = new int[bestCombination.size()];
        for (int i = 0; i < bestCombination.size(); i++) {
            result[i] = bestCombination.get(i) + 1;
        }
        return result;
    }

    private static void findBestCombination(int[][] dice, int start, List<Integer> currentCombination) {
        if (currentCombination.size() == dice.length / 2) {
            List<Integer> otherCombination = new ArrayList<>();
            for (int i = 0; i < dice.length; i++) {
                if (!currentCombination.contains(i)) {
                    otherCombination.add(i);
                }
            }
            evaluateCombination(currentCombination, otherCombination, dice);
            return;
        }

        for (int i = start; i < dice.length; i++) {
            currentCombination.add(i);
            findBestCombination(dice, i + 1, currentCombination);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    private static void evaluateCombination(List<Integer> groupA, List<Integer> groupB, int[][] dice) {
        List<Integer> groupASums = new ArrayList<>();
        List<Integer> groupBSums = new ArrayList<>();

        generateSums(groupA, dice, 0, 0, groupASums);
        generateSums(groupB, dice, 0, 0, groupBSums);

        Collections.sort(groupASums);
        Collections.sort(groupBSums);

        int totalWinCount = calculateWinCount(groupASums, groupBSums);

        if (maxWinCount < totalWinCount) {
            maxWinCount = totalWinCount;
            bestCombination = new ArrayList<>(groupA);
        }
    }

    private static int calculateWinCount(List<Integer> groupASums, List<Integer> groupBSums) {
        int winCount = 0;

        for (int aSum : groupASums) {
            int left = 0;
            int right = groupBSums.size() - 1;

            // 이분 탐색으로 groupB에서 aSum보다 작은 요소의 개수 찾기
            while (left <= right) {
                int mid = (left + right) / 2;

                if (aSum > groupBSums.get(mid)) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            winCount += left;
        }
        return winCount;
    }

    private static void generateSums(List<Integer> group, int[][] dice, int currentSum, int index, List<Integer> sums) {
        if (index == group.size()) {
            sums.add(currentSum);
            return;
        }

        int diceIndex = group.get(index);
        for (int faceValue : dice[diceIndex]) {
            generateSums(group, dice, currentSum + faceValue, index + 1, sums);
        }
    }
}
