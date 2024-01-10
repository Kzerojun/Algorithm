import java.util.*;

class Solution {
    static int diceCount;
    static boolean[] visited;
    static List<int[]> A = new ArrayList<>();
    static List<int[]> B = new ArrayList<>();

    static int[] result;
    static List<Integer> AdiceResult;
    static List<Integer> BdiceResult;

    public int[] solution(int[][] dice) {
        diceCount = dice.length;
        visited = new boolean[diceCount];
        combination(0, 0);
        int max = Integer.MIN_VALUE;
        int index = 0;
        for(int i = 0; i < A.size(); i++) {
            AdiceResult = new ArrayList<>();
            BdiceResult = new ArrayList<>();

            calculate(0, A.get(i), dice, 0, AdiceResult);
            calculate(0, B.get(i), dice, 0, BdiceResult);
            Collections.sort(AdiceResult);
            Collections.sort(BdiceResult);

            int totalWinCount = 0;
            
            for(int a : AdiceResult) {
                int left = 0 ;
                int right = BdiceResult.size();
                
                while(left+1<right) {
                    int mid = (left+right) /2 ;
                    
                    if(a > BdiceResult.get(mid)) {
                        left = mid;
                    } else {
                        right = mid;
                    }
                }
                
                totalWinCount += left;
            }
            
            if(max< totalWinCount) {
                max = totalWinCount;
                index = i;
            }
            
            
        }

        result = A.get(index);
        return result;
    }

    static void combination(int index, int count) {
        if(count == diceCount / 2) {
            int[] Atmp = new int[diceCount / 2];
            int[] Btmp = new int[diceCount / 2];
            int tmpIndex = 0;
            int tmpIndex2 = 0;
            for(int i = 0; i < diceCount; i++) {
                if(visited[i]) {
                    Atmp[tmpIndex] = i + 1;
                    tmpIndex++;
                }
                if(!visited[i]) {
                    Btmp[tmpIndex2] = i + 1;
                    tmpIndex2++;
                }
            }
            A.add(Atmp);
            B.add(Btmp);
            return;
        }

        for(int i = index; i < diceCount; i++) {
            visited[i] = true;
            combination(i + 1, count + 1);
            visited[i] = false;
        }
    }

    static void calculate(int index, int[] dices, int[][] originDices, int sum, List<Integer> team) {
        if(index == dices.length) {
            team.add(sum);
            return;
        }

        for(int i = 0; i < 6; i++) {
            calculate(index + 1, dices, originDices, sum+originDices[dices[index]-1][i], team);
        }
    }
}
