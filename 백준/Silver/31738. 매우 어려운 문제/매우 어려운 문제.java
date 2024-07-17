

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		long N = input[0];
		long M = input[1];

		long sum = 1;

		if (N >= M) {
			System.out.println(0);
			return;
		}
		for (int i = 1; i <= N; i++) {
			sum = (sum * i) % M;
		}
		System.out.println(sum);
	}
}
