import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Main {
    static int minDistance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            minDistance = Integer.MAX_VALUE;
            int N = Integer.parseInt(br.readLine());
            String[] MBTIs = new String[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            Map<String, Integer> types = new TreeMap<>();
            
            for (int j = 0; j < N; j++) {
                String type = st.nextToken();
                MBTIs[j] = type;
                types.put(type, types.getOrDefault(type, 0) + 1);
            }
            boolean check = true;
            for(int x : types.values()){
                if (x >= 3) {
                    check = false;
                }
            }

            boolean[] visited = new boolean[N];
            if(check){
                calculateDistance(MBTIs, visited, 0, N, 0);
            } else{
                minDistance = 0;
            }
            
            System.out.println(minDistance);
        }
    }

    static void calculateDistance(String[] MBTIs, boolean[] visited, int start, int n, int r) {
        if (r == 3) {
            int distance = 0;
            String[] selected = new String[3];
            int index = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    selected[index++] = MBTIs[i];
                }
            }

            distance += getDistance(selected[0], selected[1]);
            distance += getDistance(selected[1], selected[2]);
            distance += getDistance(selected[0], selected[2]);
            minDistance = Math.min(minDistance, distance);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            calculateDistance(MBTIs, visited, i + 1, n, r + 1);
            visited[i] = false;
        }
    }

    static int getDistance(String MBTI1, String MBTI2) {
        int distance = 0;
        for (int i = 0; i < 4; i++) {
            if (MBTI1.charAt(i) != MBTI2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }
}
