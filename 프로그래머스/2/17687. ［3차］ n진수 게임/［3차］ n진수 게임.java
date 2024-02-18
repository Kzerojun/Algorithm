import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        List<Character> list = calculate(n, t * m);

        for (int i = p - 1; i < list.size(); i += m) {
            sb.append(list.get(i));
            if (sb.length() == t) {
                break;
            }
        }

        return sb.toString();
    }

    static List<Character> calculate(int n, int limit) {
        List<Character> list = new ArrayList<>();
        int number = 0;

        while (list.size() < limit) {
            StringBuilder sb = new StringBuilder();
            int temp = number;

            do {
                int remainder = temp % n;
                String word = (remainder >= 10) ? String.valueOf((char) ('A' + remainder - 10)) : String.valueOf(remainder);
                sb.insert(0, word);
                temp = temp / n;
            } while (temp != 0);

            for (int j = 0; j < sb.length(); j++) {
                list.add(sb.charAt(j));
            }

            number++;
        }
        return list;
    }
}
