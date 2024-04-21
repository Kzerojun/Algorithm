import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            int left = 0;
            int right = word.length() - 1;
            if (isPalindrome(word, left, right)) {
                System.out.println(0);
                continue;
            }

            if (isPalindromeDelete(word, left, right)) {
                System.out.println(1);
                continue;
            }

            System.out.println(2);
        }
    }

    public static boolean isPalindrome(String word, int left, int right) {
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeDelete(String word, int left, int right) {
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return isPalindrome(word, left + 1, right) || isPalindrome(word, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }
}
