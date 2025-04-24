import java.util.*;

class Solution {
    
    public String solution(long n, String[] bans) {
        init(bans);
        return sol(bans,n);
    }
    
    private static void init(String[] bans) {
        Arrays.sort(bans,(o1,o2)->{
            if(o1.length()==o2.length()) {
                return o1.compareTo(o2);
            }
            return o1.length()-o2.length();
        });
    }
    
    private static String sol(String[] bans, long n) {
        
        for(String ban : bans) {
            long number = stringToNumber(ban);
            if(number<=n) {
                n++;
            }
        }
        
        return numberToString(n);
    }
    
    private static long stringToNumber(String str) {
        long number = 0;
        for(int i = str.length()-1 ; i>=0; i--) {
            number += (str.charAt(i) - 'a' +1)  * (Math.pow(26,str.length()-i-1));
        }
        return number;
    }
    
    private static String numberToString(long number) {
        StringBuilder sb = new StringBuilder();
        
        while(number > 0 ) {
            number--;
            sb.append((char)('a'+number%26));
            number /=26;
        }
        return sb.reverse().toString();
    }
}