class Solution {
    static char[] ch = {'1', '1', '0'};
    static int[] priority;
    static int length;
    
    public String[] solution(String[] s) {
        return start(s);    
    }
    
    private static String[] start(String[] s) {
        String[] sol = new String[s.length];
        int index = 0;
        for (String str : s) {
            length = str.length();
            priority = new int[length];
            String result = calculate(str);
            sol[index++] = result;
        }
        return sol;
    }
    
    private static String calculate(String str) {
        StringBuilder sb = new StringBuilder(str);
        int count110 = 0;
        
        for (int i = 0; i < sb.length() - 2; i++) {
            if (sb.substring(i, i + 3).equals("110")) {
                sb.delete(i, i + 3);
                count110++;
                i = Math.max(i - 3, -1);
            }
        }
        
        int insertPosition = 0;
        
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) == '0') {
                insertPosition = i + 1;
                break;
            }
        }
        
        for (int i = 0; i < count110; i++) {
            sb.insert(insertPosition, "110");
        }
        
        return sb.toString();
    }
}