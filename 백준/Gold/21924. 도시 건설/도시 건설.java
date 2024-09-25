import java.io.*;
import java.util.*;

public class Main {
	static int nodeCount;
	static int edgeCount;
	static Edge[] edgeArray;
	static int[] parents;
	static long total;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		nodeCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());

		edgeArray = new Edge[edgeCount];
		total = 0;
		for (int i = 0; i < edgeCount; i++) {
			st = new StringTokenizer(br.readLine());
			edgeArray[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Long.parseLong(st.nextToken()));

			total += edgeArray[i].weight;
		}

		Arrays.sort(edgeArray);

		init();

		long sum = 0;
		int count = 0;
		for (Edge edge : edgeArray) {
			if (union(edge.start, edge.end)) {
				sum += edge.weight;
				count++;

				if (count == nodeCount - 1) {
					System.out.println(total - sum);
					return;
				}
			}
		}

		System.out.println(-1);

	}

	public static void init() {
		parents = new int[nodeCount + 1];
		for (int i = 1; i < nodeCount + 1; i++) {
			parents[i] = i;
		}
	}

	public static int find(int a) {
		if (parents[a] == a) {
			return a;
		}

		return parents[a] = find(parents[a]);
	}

	public static boolean union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);

		if (aParent == bParent) {
			return false;
		}

		parents[bParent] = aParent;
		return true;
	}

}

class Edge implements Comparable<Edge> {
	int start;
	int end;
	long weight;

	Edge(int start, int end, long weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return Long.compare(this.weight, o.weight);
	}
}