import java.util.*;

class Solution {
    private static List<String> binaryNumbers;
    private static int[] sol;
    private static int index = 0;

    public int[] solution(long[] numbers) {
        init(numbers);
        sol();
        return sol;
    }

    private static void init(long[] numbers) {
        binaryNumbers = new ArrayList<>();
        sol = new int[numbers.length];

        for (long number : numbers) {
            String binaryNumber = Long.toBinaryString(number);
            int size = calculateSize(binaryNumber.length());
            int length = (int) Math.pow(2, size) - 1;
            int diffCount = length - binaryNumber.length();

            // 앞에 '0' 추가
            for (int i = 0; i < diffCount; i++) {
                binaryNumber = "0" + binaryNumber;
            }

            binaryNumbers.add(binaryNumber);
        }
    }

    private static void sol() {
        index = 0; // 인덱스 초기화
        for (String binaryNumber : binaryNumbers) {
            if (!isCanMake(binaryNumber)) {
                sol[index++] = 0;
            } else {
                sol[index++] = 1;
            }
        }
    }

    private static boolean isCanMake(String data) {
        if(data.length() <=1) {
            return true;
        }
        
        String leftTree = data.substring(0,data.length()/2);
        String rightTree = data.substring(data.length()/2+1);
        
        char root = data.charAt(data.length()/2);
        char left = leftTree.charAt(leftTree.length()/2);
        char right = rightTree.charAt(rightTree.length()/2);
        
        if(root == '0' && (left =='1' || right == '1')) {
            return false;
        } else {
            return isCanMake(leftTree) && isCanMake(rightTree);
        }

    }

    private static int calculateSize(int size) {
        int k = 1;
        while ((Math.pow(2, k) - 1) < size) {
            k++;
        }
        return k;
    }
}
