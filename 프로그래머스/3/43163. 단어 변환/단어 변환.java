import java.util.*;

class Solution {
    static int answer= Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        move(begin,words,target);
        
        if(answer == Integer.MAX_VALUE) {
            return 0;
        }
        return answer;
    }
    static void move(String begin,String[] words,String target) {
        Queue<Target> q = new LinkedList<>();
        q.offer(new Target(begin,0));
        boolean[] visit = new boolean[words.length];
    
        while(!q.isEmpty()) {
            Target now = q.poll();
            if(now.word.equals( target)) {
                answer = Math.min(now.count,answer);
            }
            List<Integer> possibleIndex = calculate(now.word,words);
            
            for(int x : possibleIndex) {
                if(!visit[x]) {
                    q.offer(new Target(words[x],now.count+1));
                    visit[x] = true;
                }
            }
            
        }
    }
    
    static List<Integer> calculate(String word,String[] words) {
        List<Integer> list = new ArrayList<>();
        
        for(int i = 0 ; i<words.length; i++) {
            int count = 0;
            for(int j = 0 ; j<word.length(); j++) {
                if(words[i].charAt(j)!=word.charAt(j)) {
                    count++;
                }
            }
            if(count==1) {
                list.add(i);
            }
        }
        return list;
    }
    
    
    
}

class Target {
    String word;
    int count;
    
    Target(String word, int count) {
        this.word = word;
        this.count = count;
    }
}