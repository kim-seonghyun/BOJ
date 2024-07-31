import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int[] direction_x = { 0, 0, 1, -1 };
	static final int[] direction_y = { -1, 1, 0, 0 };

	static int[][] arr;

	static int M;
	static int N;

	static int totalTomatoCount = 0;
	static int currentTomatoCount = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] != -1) {
					totalTomatoCount++;
				}
				if (arr[i][j] == 1) {
					currentTomatoCount++;
				}
			}
		}

//		if (currentTomatoCount == totalTomatoCount) {
//			System.out.println(0);
//			return;
//		}
		ArrayList<int[]> adultTomatos = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1) {
					int[] tmp = { i, j };
					adultTomatos.add(tmp);
				}
			}
		}
		Queue<int[]> tomatos = new LinkedList<>();
		adultTomatos.stream().forEach(tomato -> tomatos.add(tomato));
		while (!tomatos.isEmpty()) {
			int[] position = tomatos.poll();
			int x = position[0];
			int y = position[1];

			for (int dirIndex = 0; dirIndex < direction_x.length; dirIndex++) {
				int next_x = x + direction_x[dirIndex];
				int next_y = y + direction_y[dirIndex];

				if (0 <= next_x && next_x < N && 0 <= next_y && next_y < M && arr[next_x][next_y] != -1) {
					int[] nextPosition = { next_x, next_y };

					if (arr[next_x][next_y] > arr[x][y] + 1) {
						arr[next_x][next_y] = arr[x][y] + 1;
						tomatos.add(nextPosition);
						continue;
					}

					if (arr[next_x][next_y] == 0) {
						arr[next_x][next_y] = arr[x][y] + 1;
						tomatos.add(nextPosition);
						continue;
					}

				}
			}

		}

		int maxValue = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) {
					System.out.println(-1);
					return;
				}

				maxValue = Math.max(maxValue, arr[i][j]);
			}
		}
		System.out.println(maxValue - 1);
	}

}
