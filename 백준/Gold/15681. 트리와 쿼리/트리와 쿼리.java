
import java.io.*;
import java.util.*;

public class Main {

	// 정점 U를 루트로 하는 서브트리에 속한 정점의 수 -> 연결된 정점의 수
	static int N, R, Q, dp[];
	static LinkedList<Integer>[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		list = new LinkedList[N + 1];
		dp = new int[N+1];
		for (int i = 1; i < N + 1; i++) {
			list[i] = new LinkedList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
			
		}
		
		findSub(R, -1);

		for (int i = 0; i < Q; i++) {
			int q = Integer.parseInt(br.readLine());
			System.out.println(dp[q]
					);
		}

	}

	public static int findSub(int cur, int parent) {
		dp[cur] = 1;
		
		for(int next : list[cur]) {
			if(next == parent) continue;
			dp[cur] += findSub(next, cur);
		}
		
		
		return dp[cur];
	}

}
