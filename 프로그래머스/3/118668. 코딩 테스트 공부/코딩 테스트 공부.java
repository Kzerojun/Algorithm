import java.util.*;

class Solution {
    private static List<Problem> problemList;
    private static int currentAlp, currentCop;
    private static int[][] ability;
    private static final int INF = 100_001;
    private static State maxProblem;
    
    public int solution(int alp, int cop, int[][] problems) {
        init(alp, cop, problems);
        return calculate();
    }
    
    private static int calculate() {
        PriorityQueue<State> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        q.add(new State(currentAlp, currentCop, 0));
        
        while (!q.isEmpty()) {
            State now = q.poll();
            // System.out.println("현재 값"+now.alp + " "+now.cop+"값"+now.cost);
            if(ability[now.alp][now.cop]<now.cost) continue;
            
            if(now.alp>=maxProblem.alp && now.cop >= maxProblem.cop) {
                return ability[now.alp][now.cop];
            }
            
            for (Problem next : problemList) {
                
                if(now.alp>= next.alp_req && now.cop >= next.cop_req) {
                    int nextAlp = Math.min(150, now.alp + next.alp_rwd);
                    int nextCop = Math.min(150, now.cop + next.cop_rwd);
                
                if (ability[nextAlp][nextCop] > now.cost + next.cost) {
                    ability[nextAlp][nextCop] = now.cost + next.cost;
                    q.add(new State(nextAlp, nextCop,ability[nextAlp][nextCop] ));
                }
                    
                }
   
            }
        }
        return -1;
    }
    
    private static void init(int alp, int cop, int[][] problems) {
        int maxAlp = alp;
        int maxCop = cop;
        
        problemList = new ArrayList<>();


        for (int[] problem : problems) {
            int alp_req = problem[0];
            int cop_req = problem[1];
            maxAlp = Math.max(maxAlp, alp_req);
            maxCop = Math.max(maxCop, cop_req);
            
            Problem newProblem = new Problem(alp_req, cop_req, problem[2], problem[3], problem[4]);
            
            problemList.add(newProblem);
        }
        
        currentAlp = alp;
        currentCop = cop;
        
        ability = new int[151][151];
        for (int i = 0; i < 151; i++) {
            Arrays.fill(ability[i], INF);
        }
        
        ability[currentAlp][currentCop] = 0;
        
        problemList.add(new Problem(0, 0, 1, 0, 1));  // Increase alp
        problemList.add(new Problem(0, 0, 0, 1, 1));  // Increase cop
        
        maxProblem = new State(maxAlp, maxCop, 0);
    }
}

class State {
    int alp;
    int cop;
    int cost;
    
    State(int alp, int cop, int cost) {
        this.alp = alp;
        this.cop = cop;
        this.cost = cost;
    }
}

class Problem {
    int alp_req;
    int cop_req;
    int alp_rwd;
    int cop_rwd;
    int cost;
    
    Problem(int alp_req, int cop_req, int alp_rwd, int cop_rwd, int cost) {
        this.alp_req = alp_req;
        this.cop_req = cop_req;
        this.alp_rwd = alp_rwd;
        this.cop_rwd = cop_rwd;
        this.cost = cost;
    }
}
