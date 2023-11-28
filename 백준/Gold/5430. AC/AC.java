import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String command = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String[] input = br.readLine()
                    .replaceAll("[\\[\\]]", "")
                    .split(",");
            Deque<Integer> deque = new LinkedList<>();

            for (String s : input) {
                if (!s.equals("")) {
                    deque.offer(Integer.parseInt(s));
                }
            }

            boolean isReverse = false;
            boolean isError  = false;
            for (int x = 0; x < command.length(); x++) {
                char ch = command.charAt(x);
                if(ch=='R'){
                    isReverse = !isReverse;
                }
                if(ch=='D'){
                    if (deque.isEmpty()) {
                        isError = true;
                        break;
                    }

                    if (isReverse) {
                        deque.pollLast();
                        continue;
                    }
                    deque.pollFirst();

                }
            }

            if (isError) {
                System.out.println("error");
                continue;
            }
            StringBuilder sb = new StringBuilder("[");
            while(!deque.isEmpty()){
                sb.append(isReverse?deque.pollLast() : deque.pollFirst());
                if (!deque.isEmpty()) {
                    sb.append(",");
                }
            }
            sb.append("]");
            System.out.println(sb);


        }
    }

    static void R(List<Integer> numbers) {
        Collections.reverse(numbers);
    }

    static boolean D(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return false;
        }
        numbers.remove(0);
        return true;
    }







}

