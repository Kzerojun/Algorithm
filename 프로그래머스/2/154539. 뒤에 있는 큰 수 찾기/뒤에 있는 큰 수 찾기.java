import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        return calculate(numbers);
    }
    
    private static int[] calculate(int[] numbers) {
        int[] solution = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();
        
        for(int i = numbers.length-1; i>=0; i--) {
            while(!stack.isEmpty() && stack.peek()<=numbers[i]) {
                stack.pop();
            }
            
            if(stack.isEmpty()) {
                solution[i] = -1;
            }else {
                solution[i] = stack.peek();
            }
            
            stack.push(numbers[i]);
            
        }
        return solution;
    }
}