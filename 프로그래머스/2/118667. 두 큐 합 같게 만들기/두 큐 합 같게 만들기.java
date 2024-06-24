import java.util.*;

class Solution {
    static Queue<Long> q1;
    static Queue<Long> q2;
    static long q1Sum;
    static long q2Sum;

    public int solution(int[] queue1, int[] queue2) {
        init(queue1, queue2);
        return calculate();
    }

    private static int calculate() {
        int count = 0;
        int size = q1.size() + q2.size();
        while (count < 2* size) {
            if (q1Sum > q2Sum) {
                long value = q1.poll();
                q2.add(value);
                q1Sum -= value;
                q2Sum += value;
                count++;
                continue;
            }
            if (q1Sum < q2Sum) {
                long value = q2.poll();
                q1.add(value);
                q2Sum -= value;
                q1Sum += value;
                count++;
                continue;
            } 
            if (q1Sum == q2Sum) {
                return count;
            }
        }

        return -1;
    }

    private static void init(int[] queue1, int[] queue2) {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
        q1Sum = 0;
        q2Sum = 0;

        for (int number : queue1) {
            q1.add((long)number);
            q1Sum += (long)number;
        }

        for (int number : queue2) {
            q2.add((long)number);
            q2Sum += (long)number;
        }
    }
}
