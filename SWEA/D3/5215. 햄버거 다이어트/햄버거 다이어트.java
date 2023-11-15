import java.util.*;
import java.io.*;

public class Solution {
    static int maxScore;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for (int i = 1; i <= testCase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            HamBurger[] hamBurgers = new HamBurger[N];

            for (int j = 0; j < hamBurgers.length; j++) {
                st = new StringTokenizer(br.readLine());
                int score = Integer.parseInt(st.nextToken());
                int calorie = Integer.parseInt(st.nextToken());
                hamBurgers[j] = new HamBurger(score, calorie);
            }

            maxScore = 0;
            dfs(hamBurgers, L, 0, 0, 0);

            System.out.println("#" + i + " " + maxScore);
        }
    }

    static void dfs(HamBurger[] hamBurgers, int limitCalorie, int totalScore, int totalCalorie, int index) {
        if (totalCalorie > limitCalorie) { 
            return;
        }

        if(index == hamBurgers.length) {
        	if(totalScore>maxScore) {
        		maxScore = totalScore;
        	}
        	return;
        }
        
        dfs(hamBurgers,limitCalorie,totalScore+hamBurgers[index].score,totalCalorie+hamBurgers[index].calorie,index+1);
        dfs(hamBurgers,limitCalorie,totalScore,totalCalorie,index+1);

    }
}

class HamBurger {
    int score;
    int calorie;

    HamBurger(int score, int calorie) {
        this.score = score;
        this.calorie = calorie;
    }
}



