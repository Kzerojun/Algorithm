import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int[] time;
    static int[] count;
    static int minTime = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        time = new int[100_001];
        count = new int[100_001];

        bfs(N, K);
        System.out.println(time[K]);
        System.out.println(count[K]);
    }

    static void bfs(int N, int K) {
        Queue<Location> q = new LinkedList<>();
        q.offer(new Location(N, 0));
        time[N] = 0;
        count[N] = 1;
        if (N == K) {
            count[K] = 1;
            return;
        }

        while (!q.isEmpty()) {
            Location now = q.poll();

            for (int next : new int[]{now.location + 1, now.location - 1, now.location * 2}) {
                if (next >= 0 && next < 100_001) {
                    if (time[next] == 0) {
                        q.offer(new Location(next, now.time + 1));
                        time[next] = now.time + 1;
                    }

                    if (time[next] != 0) {
                        if (time[next] == now.time + 1) {
                            count[next] += count[now.location];
                        }
                    }
                }
            }

        }

    }
}

class Location {
    int location;
    int time;

    Location(int location, int time) {
        this.location = location;
        this.time = time;
    }
}
