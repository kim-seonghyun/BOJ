
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {


	public static boolean isPossible(int a, int b, int c) {
		if (a == b || b == c || a == c) {
			return false;
		}
		return true;
	}

	public static boolean[] makeAllPossibleArray(boolean[] arr) {
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				for (int k = 1; k <= 9; k++) {
					if (isPossible(i, j, k)) {
						arr[i * 100 + j * 10 + k] = true;
					}
				}
			}
		}
		return arr;
	}

	public static int countStrike(int answer, int guess) {
		String a = String.valueOf(answer);
		String b = String.valueOf(guess);
		int count = 0;
		for (int i = 0; i < 3; i++) {
			if (a.charAt(i) == b.charAt(i)) {
				count++;
			}
		}
		return count;
	}
	
	public static int countBall(int answer, int guess) {
		String a = String.valueOf(answer);
		String b = String.valueOf(guess);
		int count = 0;
		for (int i = 0; i < 3; i++) {
			for(int j=0; j<3; j++) {
				if(i != j && (a.charAt(i) == b.charAt(j))) {
					count++;
					break;
				}
			}
		}
		return count;
	}

	public static boolean isAnswer(int answer, int[][] guess) {
		for (int i = 0; i < guess.length; i++) {
			int strike = countStrike(answer, guess[i][0]);
			int ball = countBall(answer, guess[i][0]);
			if(strike != guess[i][1] || ball != guess[i][2]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		final int ARR_LENGTH = 1000;
		boolean[] possible_answer = new boolean[ARR_LENGTH];

		possible_answer = makeAllPossibleArray(possible_answer);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] guess = new int[N][3];
		for (int i = 0; i < N; i++) {
			guess[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		int count = 0;
		for (int i = 111; i <= 999; i++) {
			if (possible_answer[i] && isAnswer(i, guess)) {
				count++;
			}
		}
		System.out.println(count);
	}
}
