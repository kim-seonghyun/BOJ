import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int zero = 0;
	static int one = 1;
	static int[] board;
	static int[][] countBoard;
	public static int fibonacci(int n) {
		if(board[n] > 0) {
			zero += countBoard[n][0];
			one += countBoard[n][1];
			return board[n];
		}
		
		if(n == 0) {
			zero++;
			return 0;
		}else if(n == 1) {
			one++;
			return 1;
		}else {
			return fibonacci(n-1) + fibonacci(n-2);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		countBoard = new int[41][2];
		countBoard[0][0] = 1;
		countBoard[0][1] = 0;
		board = new int[41];
		for(int i=1; i<=40; i++) {
			zero = 0;
			one = 0;
			board[i] = fibonacci(i);
			countBoard[i][0] = zero;
			countBoard[i][1] = one;
		}

		for(int tc = 1; tc <= t; tc++) {
			
			int n = Integer.parseInt(br.readLine());
			fibonacci(n);
			System.out.println(countBoard[n][0] + " " + countBoard[n][1]);
			
		}
	}
}
