import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;


class Main {
    static int result;
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int K = Integer.parseInt(br.readLine());
            eachCase(K);
        }
    }

    static void eachCase(int K) throws IOException {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int data = Integer.parseInt(st.nextToken());
            if (command.equals("I")) {
                treeMap.put(data, treeMap.getOrDefault(data, 0) + 1);
            }
            if (command.equals("D")) {
                if (!treeMap.isEmpty()) {

                    int key = (data == 1) ? treeMap.lastKey() : treeMap.firstKey();
                    if (treeMap.get(key) - 1 == 0) {
                        treeMap.remove(key);
                        continue;
                    }
                    treeMap.put(key, treeMap.get(key) - 1);
                }
            }


        }

        if (treeMap.isEmpty()) {
            System.out.println("EMPTY");
            return;
        }

        System.out.println(treeMap.lastKey() + " " + treeMap.firstKey());

    }

}


