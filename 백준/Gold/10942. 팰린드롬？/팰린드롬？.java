
import java.io.*;
import java.util.*;

/**
 * 1개 -> 무조건 1 2개 -> 두개가 같으면 1 3개 -> 시작, 끝이 같으면 1 4개 -> 사이가 팰린드롬이면 1
 * 
 * 
 */
public class Main {

	static int N, M, arr[], dp[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N][N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			dp[i][i] = 1;
			for (int j = 0; j < i; j++) {
				if (i - j <= 2) dp[j][i] = arr[i] == arr[j] ? 1 : 0;
				else if(arr[i] == arr[j]) dp[j][i] = dp[j + 1][i - 1];
			}
		}

//		for (int i = 0; i < N; i++) {
//			dp[i][i] = 1;
//
//			for (int j = i + 1; j < N; j++) {
//				if (j - i <= 2) dp[i][j] = arr[i] == arr[j] ? 1 : 0;
//				else if(arr[i] == arr[j]) dp[i][j] = dp[i + 1][j - 1];
//			}
//		}

		StringBuilder sb = new StringBuilder();
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			sb.append(dp[a][b]).append("\n");
		}
		
		System.out.println(sb);

	}

}
