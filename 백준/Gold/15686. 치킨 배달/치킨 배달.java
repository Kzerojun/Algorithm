import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static List<Location> chickens;
	static List<Location> homes;
	static List<List<Location>> combinations;

	public static void main(String[] args) throws IOException {
		init();
		simulate();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		homes = new ArrayList<>();
		chickens = new ArrayList<>();
		combinations = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int number = Integer.parseInt(st.nextToken());
				if (number == 1) {
					homes.add(new Location(i, j));
				}

				if (number == 2) {
					chickens.add(new Location(i, j));
				}
			}
		}

		combinations = new ArrayList<>();
	}

	private static void simulate() {
		makeCombination(new ArrayList<>(), 0);
		int result = findMinChickenDistance();
		System.out.println(result);
	}

	//치킨집 콤비네이션 만들기 M의 크기에 맞게
	private static void makeCombination(List<Location> tmp, int index) {
		if (tmp.size() == M) {
			combinations.add(new ArrayList<>(tmp));
			return;
		}

		for (int i = index; i < chickens.size(); i++) {
			tmp.add(chickens.get(i));
			makeCombination(tmp, i + 1);
			tmp.remove(tmp.size() - 1);
		}
	}

	// 치킨 콤비네이션과 집과의 모든 경우의 수 계산
	private static int findMinChickenDistance() {

		int result = Integer.MAX_VALUE;
		for (List<Location> chickenComb : combinations) {
			int cal = calculateMinChicken(chickenComb);
			result = Math.min(cal, result);
		}

		return result;

	}

	//가장 가까운 치킨집과의거리 -> 도시의 치킨거리 계산
	private static int calculateMinChicken(List<Location> chickenComb) {

		int sumMinDistance = 0;
		for (Location home : homes) {
			int minDistance = Integer.MAX_VALUE;

			for (Location chicken : chickenComb) {
				int distance = Math.abs(chicken.x - home.x) + Math.abs(chicken.y - home.y);
				minDistance = Math.min(distance, minDistance);
			}

			sumMinDistance += minDistance;
		}

		return sumMinDistance;

	}
}

class Location {
	int x;
	int y;

	Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
