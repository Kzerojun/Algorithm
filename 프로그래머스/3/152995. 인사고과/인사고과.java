import java.util.*;

class Solution {
    
    private static List<Score> tmpScores;
    private static Score wanho;
    
    public int solution(int[][] scores) {
        init(scores);
        return calculate();
    }
    
    
    private static void init(int[][] scores) {
        tmpScores = new ArrayList<>();
        wanho = new Score(scores[0][0],scores[0][1]);
        
        tmpScores.add(wanho);
        for(int i = 1; i<scores.length; i++) {
            tmpScores.add(new Score(scores[i][0],scores[i][1]));
        }
        
        tmpScores.sort((o1,o2)->{
            if(o1.workScore == o2.workScore) {
                return o1.reviewScore - o2.reviewScore;
            }
            
            return o2.workScore - o1.workScore;
        });   
    }
    
    private static int calculate() {
        int highReviewScore = -1;
        int rank = 1;

        for(Score score : tmpScores) {
            highReviewScore = Math.max(highReviewScore,score.reviewScore);
            
            if(score.reviewScore < highReviewScore) {
                if(score.workScore== wanho.workScore && score.reviewScore == wanho.reviewScore) {
                    return -1;
                }
                continue;
            }
            
            if(score.workScore + score.reviewScore > wanho.workScore + wanho.reviewScore) {
                rank++;
            }
            
            
        }
        
        return rank;
    }
    
    
    
}

class Score {
    int workScore;
    int reviewScore;
    
    Score(int workScore,int reviewScore) {
        this.workScore = workScore;
        this.reviewScore = reviewScore;
    }
}