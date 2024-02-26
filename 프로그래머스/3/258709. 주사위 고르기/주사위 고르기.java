import java.util.*;

class Solution {

    static int max = Integer.MIN_VALUE;
    static List<Integer> resultCombi;
    
    public int[] solution(int[][] dice) {
        resultCombi = new ArrayList<>();
        combination(dice,0,new ArrayList<>());
        
        int[] result = new int[resultCombi.size()];
        
        for(int i = 0 ; i<result.length; i++) {
            result[i] = resultCombi.get(i) + 1;
        }
        
        return result;
    }
    
    static void combination(int[][] dice, int start, List<Integer> combiDices) {
        if(combiDices.size() == dice.length/2) {
            List<Integer> combiDicesB = new ArrayList<>();
            for(int i = 0 ; i<dice.length; i++) {
                if(!combiDices.contains(i)) {
                    combiDicesB.add(i);
                }
            }

            calculate(combiDices,combiDicesB,dice);
            return;
        }
    
        for(int i = start; i<dice.length; i++) {
            combiDices.add(i);
            combination(dice,i+1, combiDices);
            combiDices.remove(combiDices.size() - 1);
        }
    }

    
    static void calculate(List<Integer> combiDicesA, List<Integer> combiDicesB,int[][]dice) {
        List<Integer> sumCombiA = new ArrayList<>();
        List<Integer> sumCombiB = new ArrayList<>();
        
        combiSum(combiDicesA,dice,0,0,sumCombiA);
        combiSum(combiDicesB,dice,0,0,sumCombiB);
        
        Collections.sort(sumCombiA);
        Collections.sort(sumCombiB);

        int totalWinCount = 0;
        
        for (Integer a : sumCombiA) {
            int left = 0;
            int right = sumCombiB.size() - 1;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (a > sumCombiB.get(mid)) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            totalWinCount += left;
        }
        
        if(max < totalWinCount) {
            max = totalWinCount;
            resultCombi = new ArrayList<>(combiDicesA);
        }
    }
    
    static void combiSum(List<Integer>combiDices,int[][]dice,int sum, int index,List<Integer> sumCombi) {
        
        if(index == combiDices.size()) {
            sumCombi.add(sum);
            return;
        }
        
        for(int i = 0 ; i<6; i++) {
            combiSum(combiDices,dice,sum+dice[combiDices.get(index)][i],index+1,sumCombi);
        }
    }
}
