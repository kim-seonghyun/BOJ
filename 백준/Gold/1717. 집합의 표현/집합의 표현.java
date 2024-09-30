import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static int m;
	static Edge[] edges;
	static int[] numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		init();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());

			if (number == 0) {
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			} else {
				if (isUnion(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}

	}

	public static void init() {
		numbers = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			numbers[i] = i;
		}
	}

	public static int find(int target) {
		if (numbers[target] == target) {
			return target;
		}

		return numbers[target] = find(numbers[target]);
	}

	public static boolean isUnion(int a, int b) {
		return find(a) == find(b);
	}

	public static boolean union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);

		if (aParent == bParent) {
			return false;
		}

		numbers[aParent] = bParent;

		return true;
	}

}

class Edge {
	int a;
	int b;

	public Edge(int a, int b) {
		this.a = a;
		this.b = b;
	}

}