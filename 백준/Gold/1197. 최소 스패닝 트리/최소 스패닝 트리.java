import java.util.*;
import java.io.*;

public class Main {
	public static int[] numbers;
	static int n;
	static int m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		Edge[] edges = new Edge[m];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			Edge edge = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
			edges[i] = edge;
		}
		init();
		Arrays.sort(edges);
		int result = 0;

		for (Edge edge : edges) {
			if (union(edge.start, edge.end)) {
				result += edge.power;
			}
		}

		System.out.println(result);
	}

	public static void init() {
		numbers = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			numbers[i] = i;
		}
	}

	public static int find(int target) {
		if (target == numbers[target]) {
			return target;
		}

		return numbers[target] = find(numbers[target]);
	}

	public static boolean union(int a, int b) {
		int aFind = find(a);
		int bFind = find(b);

		if (aFind == bFind) {
			return false;
		}

		numbers[aFind] = bFind;
		return true;
	}
}

class Edge implements Comparable<Edge> {
	int start;
	int end;
	int power;

	Edge(int start, int end, int power) {
		this.start = start;
		this.end = end;
		this.power = power;
	}

	@Override
	public int compareTo(Edge o) {

		return this.power - o.power;
	}

}
