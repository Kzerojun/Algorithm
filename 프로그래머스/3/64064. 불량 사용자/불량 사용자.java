import java.util.*;

class Solution {
    static Map<String, List<String>> map;
    static Set<String> uniqueCombinations;
    static int result;
    
    public int solution(String[] user_id, String[] banned_id) {
        init();
        play(user_id, banned_id);
        
        return result;
    }
    
    private static void play(String[] user_id, String[] banned_id) {
        for(String userId : user_id) {
            makeBanList("", userId.toCharArray(), 0);
        }
        
        dfs(banned_id, 0, new ArrayList<>());
        
        result = uniqueCombinations.size();
    }
    
    private static void dfs(String[] banned_id, int index, List<String> comb) {
        if(index == banned_id.length) {
            List<String> sortedComb = new ArrayList<>(comb);
            Collections.sort(sortedComb);
            uniqueCombinations.add(String.join(",", sortedComb));
            
            return;
        }
        
        List<String> list = map.get(banned_id[index]);
        
        for(int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            
            if(comb.contains(str)) {
                continue;
            }
            
            comb.add(str);
            dfs(banned_id, index + 1, comb);
            comb.remove(comb.size() - 1);
        }
    }
    
    private static void init() {
        map = new TreeMap<>();
        uniqueCombinations = new HashSet<>();
        result = 0;
    }
    
    private static void makeBanList(String str, char[] origin, int index) {
        if(str.length() == origin.length) {
            List<String> list = map.getOrDefault(str, new ArrayList<>());
            list.add(new String(origin));
            map.put(str, list);
            return;
        }
        
        makeBanList(str + origin[index], origin, index + 1);
        makeBanList(str + "*", origin, index + 1);
    }
}