import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m, fuel;
    static int[][] graph;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {-1, 1, 0, 0};
    static Taxi taxi;
    static List<Passenger> passengers;
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        taxi = getTaxiInput();
        passengers = getPassengerInput();
        for(int i = 0; i<m; i++){
            countDistance();
            if(taxi.fuel ==-1){
                break;
            }
        }

        System.out.println(taxi.fuel);
    }

    static void countDistance() {
        PriorityQueue<Passenger> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.distance == o2.distance) {
                if (o1.passengerX == o2.passengerX) {
                    return o1.passengerY - o2.passengerY;
                }
                return o1.passengerX - o2.passengerX;
            }
            return o1.distance - o2.distance;
        });

        for(Passenger passenger : passengers){
            int distance = getShortestDistance(taxi.x, taxi.y, passenger.passengerX, passenger.passengerY);
            if (distance != -1) {
                passenger.distance = distance;
                pq.offer(passenger);
            }
        }

        if (pq.isEmpty()) {
            taxi.fuel = -1;
            return;
        }

        Passenger passenger = pq.poll();
        if(passenger.distance <= taxi.fuel){
            taxi.x = passenger.passengerX;
            taxi.y = passenger.passengerY;
            taxi.fuel -= passenger.distance;
            movetoDestination(passenger);
        } else{
            taxi.fuel = -1;
        }
    }

    static void movetoDestination(Passenger passenger){
        int distance = getShortestDistance(taxi.x, taxi.y, passenger.destinationX, passenger.destinationY);
        if (distance != -1 && taxi.fuel >= distance) {
            taxi.fuel -= distance;
            taxi.fuel += distance * 2;
            taxi.x = passenger.destinationX;
            taxi.y = passenger.destinationY;
            passengers.remove(passenger);
        } else {
            taxi.fuel = -1;
        }
    }

    static int getShortestDistance(int x1, int y1, int x2, int y2) {
        boolean[][] visit = new boolean[n][n];
        Queue<Taxi> q = new LinkedList<>();
        q.offer(new Taxi(x1, y1, 0));
        visit[x1][y1] = true;

        while (!q.isEmpty()) {
            Taxi nowTaxi = q.poll();
            if (nowTaxi.x == x2 && nowTaxi.y == y2) {
                return nowTaxi.fuel;
            }
            for (int i = 0; i < 4; i++) {
                int nx = nowTaxi.x + dx[i];
                int ny = nowTaxi.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (!visit[nx][ny] && graph[nx][ny] != 1) {
                    visit[nx][ny] = true;
                    q.offer(new Taxi(nx, ny, nowTaxi.fuel + 1));
                }
            }
        }
        return -1;
    }




    static Taxi getTaxiInput() throws IOException {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        return new Taxi(x, y, fuel);
    }

    static List<Passenger> getPassengerInput() throws IOException {
        List<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int passengerX = Integer.parseInt(st.nextToken()) - 1;
            int passengerY = Integer.parseInt(st.nextToken()) - 1;
            int destinationX = Integer.parseInt(st.nextToken()) - 1;
            int destinationY = Integer.parseInt(st.nextToken()) - 1;
            passengers.add(new Passenger(passengerX, passengerY, destinationX, destinationY, 0));
        }

        return passengers;
    }
}


class Passenger {
    int passengerX;
    int passengerY;
    int destinationX;
    int destinationY;
    int distance;

    Passenger(int passengerX, int passengerY, int destinationX, int destinationY, int distance) {
        this.passengerX = passengerX;
        this.passengerY = passengerY;
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.distance = distance;
    }
}

class Taxi {
    int x;
    int y;
    int fuel;

    Taxi(int x, int y, int fuel) {
        this.x = x;
        this.y = y;
        this.fuel = fuel;
    }
}
