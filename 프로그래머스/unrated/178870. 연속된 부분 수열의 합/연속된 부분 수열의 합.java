class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] index = new int[2];
        int length = Integer.MAX_VALUE;

        int start = 0; // 부분 수열의 시작 인덱스
        int end = 0; // 부분 수열의 끝 인덱스
        int sum = sequence[0]; // 부분 수열의 합

        while (start < sequence.length && end < sequence.length) {
            if (sum == k) {
                if (end - start < length) {
                    length = end - start;
                    index[0] = start;
                    index[1] = end;
                }
                sum -= sequence[start];
                start++;
            } else if (sum < k) {
                end++;
                if (end < sequence.length) {
                    sum += sequence[end];
                }
            } else { // sum > k
                sum -= sequence[start];
                start++;
            }
        }

        return index;
    }
}