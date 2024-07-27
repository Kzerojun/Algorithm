import java.util.*;

class Solution {
    static Map<Integer,Integer> orders;
    static Set<String> dictonary;
    public int[] solution(int n, String[] words) {
        init();
        return play(words,n);
    }
    
    private static int[] play(String[] words, int n) {
        int[] sol = new int[2];
        
        String startWord = words[0];
        dictonary.add(startWord);
        orders.put(1,orders.getOrDefault(1,0)+1);
        
        for(int i = 1 ; i < words.length; i++) {
            int number = (i%n)+1;
            String word = words[i];
            
            if(dictonary.contains(word)) {
                sol[0] = number;
                sol[1] = orders.getOrDefault(number,0)+1;
                return sol;
            }
            
            if(startWord.charAt(startWord.length()-1) != word.charAt(0)) {
                sol[0] = number;
                sol[1] = orders.getOrDefault(number,0)+1;
                return sol;
            }
            
            orders.put(number,orders.getOrDefault(number,0)+1);
            dictonary.add(word);
            startWord = word;
        }
        
        sol[0] = 0;
        sol[1] = 0;
        
        return sol;
    }
    
    private static void init() {
        dictonary = new HashSet<>();
        orders = new TreeMap<>();
    }
}