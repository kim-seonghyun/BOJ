

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Stream;

// 2659
// 십자카드 문제
public class Main {
	private static final int[] direct_x = { 0, 0, 1, 1 };
	private static final int[] direct_y = { 0, 1, 0, 1 };


	public static void main(String[] args) throws IOException {
		// 정사각형 -> 크기가 2, 3, 4, 5, ... 로 계속 증가함.

		// 2차원 배열 순회 (i + 정사각형 크기, j+ 정사각형 크기 < n,m

		// 꼭짓점의 수가 모두 같은 가장 큰 정사각형.

		// 꼭짓점에 위치한 수를 측정하는 배열 필요.
		// 꼭짓점 넘어가면 멈춰야돼.

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int[][] arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			arr[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
	
		int max_size = Integer.MIN_VALUE;
		for (int size = 1; size <= Math.max(N, M); size++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 꼭지점 4개가 같은지 확인하기.
					// 꼭지점이 같으면 사이즈 기록
					max_size = Math.max(max_size, getMaxSize(size, i, j, arr, N, M));
				}
			}
		}
		System.out.println(max_size);

	}

	private static int getMaxSize(int size, int i, int j, int[][] arr, int N, int M) {

		Set<Integer> set = new HashSet<Integer>();
		// TODO Auto-generated method stub
		for (int way = 0; way < direct_x.length; way++) {
			int new_x = i + direct_x[way] * size;
			int new_y = j + direct_y[way] * size;
			if (0 <= new_x && new_x < N && 0 <= new_y && new_y < M) {
				set.add(arr[new_x][new_y]);
			} else {
				return 1;
			}

		}
		if (set.size() == 1) {
			return (size + 1) * (size + 1);
		} else {
			return 1;
		}
	}

}
