class Solution {
    public int solution(int[] arr) {
       return calculate(arr);
    }
    
    private static int calculate(int[] arr) {
        int start = arr[0];
        
        for(int i = 1; i<arr.length; i++) {
            int gcd = gcd(start,arr[i]);
            start = start * arr[i] / gcd;
        }
        
        return start;
    }
    
    private static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b,a%b);
    }
}