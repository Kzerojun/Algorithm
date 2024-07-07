import java.util.*;

class Solution {
    static Map<Integer, Map<String, Integer>> map;
    static List<String> result;
    
    public String[] solution(String[] orders, int[] course) {
        init(orders, course);
        calculate();
        Collections.sort(result);
        return result.toArray(new String[0]);
    }
    
    private static void calculate() {
        for (Map.Entry<Integer, Map<String, Integer>> entry : map.entrySet()) {
            Map<String, Integer> combination = entry.getValue();
            if (combination.isEmpty()) continue;
            
            int maxValue = Collections.max(combination.values());
            if (maxValue < 2) continue;  // 최소 2명 이상의 손님에게서 주문된 조합만 고려
            
            for (Map.Entry<String, Integer> innerEntry : combination.entrySet()) {
                if (innerEntry.getValue() == maxValue) {
                    result.add(innerEntry.getKey());
                }
            }
        }
    }
    
    private static void init(String[] orders, int[] course) {
        map = new TreeMap<>();
        result = new ArrayList<>();
        
        for (int size : course) {
            map.put(size, new TreeMap<>());
        }
        
        for (String order : orders) {
            char[] charArray = order.toCharArray();
            Arrays.sort(charArray);
            String sortedOrder = new String(charArray);
            
            for (int size : map.keySet()) {
                if (sortedOrder.length() >= size) {
                    makeCombination("", 0, size, sortedOrder);
                }
            }
        }
    }
    
    private static void makeCombination(String str, int index, int goal, String order) {
        if (str.length() == goal) {
            Map<String, Integer> combination = map.get(goal);
            int count = combination.getOrDefault(str, 0);
            combination.put(str, count + 1);
            return;
        }
        
        if (index == order.length()) return;
        
        makeCombination(str + order.charAt(index), index + 1, goal, order);
        makeCombination(str, index + 1, goal, order);
    }
}