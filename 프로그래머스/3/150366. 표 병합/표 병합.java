import java.util.*;

class Solution {
    static String[] graph;
    static int[] parent;
    static String[] sol;
    static int solIndex = 0;

    public String[] solution(String[] commands) {
        System.out.println("솔루션 시작");
        init();
        play(commands);
        return Arrays.copyOf(sol, solIndex);
    }

    private static void init() {
        System.out.println("초기화 시작");
        graph = new String[2501];
        parent = new int[2501];
        sol = new String[2501];

        for (int i = 1; i <= 2500; i++) {
            parent[i] = i;
        }
        System.out.println("초기화 완료");
    }

    private static void play(String[] commands) {
        System.out.println("명령어 실행 시작");
        for (String command : commands) {
            System.out.println("명령어: " + command);
            String[] str = command.split(" ");
            execute(str);
        }
        System.out.println("명령어 실행 완료");
    }

    private static void execute(String[] str) {
        String type = str[0];
        System.out.println("명령어 타입: " + type);

        if (type.equals("UPDATE")) {
            if (str.length == 4) {
                int r = Integer.parseInt(str[1]);
                int c = Integer.parseInt(str[2]);
                String value = str[3];
                System.out.println("UPDATE 명령어: (" + r + ", " + c + ") -> " + value);
                int index = parse(r, c);
                graph[find(index)] = value;
            } else {
                String value1 = str[1];
                String value2 = str[2];
                System.out.println("UPDATE 명령어: " + value1 + " -> " + value2);
                update(value1, value2);
            }
        } else if (type.equals("MERGE")) {
            int r1 = Integer.parseInt(str[1]);
            int c1 = Integer.parseInt(str[2]);
            int r2 = Integer.parseInt(str[3]);
            int c2 = Integer.parseInt(str[4]);
            System.out.println("MERGE 명령어: (" + r1 + ", " + c1 + ") <-> (" + r2 + ", " + c2 + ")");
            merge(r1, c1, r2, c2);
        } else if (type.equals("UNMERGE")) {
            int r = Integer.parseInt(str[1]);
            int c = Integer.parseInt(str[2]);
            System.out.println("UNMERGE 명령어: (" + r + ", " + c + ")");
            unmerge(r, c);
        } else if (type.equals("PRINT")) {
            int r = Integer.parseInt(str[1]);
            int c = Integer.parseInt(str[2]);
            System.out.println("PRINT 명령어: (" + r + ", " + c + ")");
            print(r, c);
        }
    }

    private static void update(String value1, String value2) {
        System.out.println("update: " + value1 + " -> " + value2);
        for (int i = 1; i <= 2500; i++) {
            if (graph[i] != null && graph[i].equals(value1)) {
                graph[i] = value2;
            }
        }
    }

    private static void merge(int r1, int c1, int r2, int c2) {
        if (r1 == r2 && c1 == c2) {
            return;
        }
                int n1 = parse(r1, c1);
                int n2 = parse(r2, c2);
                int root1 = find(n1);
                int root2 = find(n2);
             
                //1. 값을 가진쪽으로 병합
                String rootString = graph[root1]==null ? graph[root2] : graph[root1];
                graph[root1] = null;
                graph[root2] = null;
                union(root1, root2);
                graph[root1] = rootString;
    }

    private static void unmerge(int r, int c) {
        int index = parse(r, c);
        int root = find(index);

        String originalValue = graph[root];
        graph[root] = null;
        graph[index] = originalValue;

        System.out.println("unmerge: (" + r + ", " + c + ") [" + originalValue + "]");

        List<Integer> dels = new ArrayList<>();
        for (int i = 1; i <= 2500; i++) {
            if (find(i) == root) {
                dels.add(i);
            }
        }
        for (int del : dels) {
            parent[del] = del;
        }
    }

    private static void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x == y) return;

        System.out.println("union: " + a + " (" + x + ") <- " + b + " (" + y + ")");
        parent[y] = x;  // y의 부모를 x로 설정하여 트리를 연결합니다.
    }

    private static void print(int r, int c) {
        int index = parse(r, c);
        if (graph[find(index)] == null) {
            sol[solIndex++] = "EMPTY";
        } else {
            sol[solIndex++] = graph[find(index)];
        }
        System.out.println("print: (" + r + ", " + c + ") -> " + sol[solIndex - 1]);
    }

    private static int find(int number) {
        if (parent[number] == number) return number;
        return parent[number] = find(parent[number]);
    }

    private static int parse(int r, int c) {
        // 2차원 인덱스 (r, c)를 1차원 인덱스로 변환합니다.
        return 50 * (r - 1) + c;
    }
}
