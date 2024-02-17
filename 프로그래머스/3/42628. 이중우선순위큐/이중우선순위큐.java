import java.util.*;

public class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (String operation : operations) {
            String[] split = operation.split(" ");
            String cmd = split[0];
            int num = Integer.parseInt(split[1]);

            if (cmd.equals("I")) {
                maxHeap.offer(num);
                minHeap.offer(num);
            } else if (cmd.equals("D")) {
                if (!maxHeap.isEmpty()) {
                    if (num == 1) {
                        int max = maxHeap.poll();
                        minHeap.remove(max);
                    } else if (num == -1) {
                        int min = minHeap.poll();
                        maxHeap.remove(min);
                    }
                }
            }
        }

        if (maxHeap.isEmpty()) {
            return new int[]{0, 0};
        } else {
            return new int[]{maxHeap.poll(), minHeap.poll()};
        }
    }
}
