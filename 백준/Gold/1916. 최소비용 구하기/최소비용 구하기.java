import java.io.*;
import java.util.*;

public class Main {
	public static List<Edge>[] edgeList;
	public static int[] distance;
	public static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cityCount = Integer.parseInt(br.readLine());
		int edgeCount = Integer.parseInt(br.readLine());

		edgeList = new List[cityCount + 1];
		visited = new boolean[cityCount + 1];
		distance = new int[cityCount + 1];

		for (int i = 0; i < cityCount + 1; i++) {
			edgeList[i] = new ArrayList<>();
			distance[i] = Integer.MAX_VALUE;
		}
		StringTokenizer st;
		for (int i = 0; i < edgeCount; i++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			edgeList[number].add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		PriorityQueue<Edge> queue = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());

		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		queue.add(new Edge(start, 0));
		distance[start] = 0;

		while (!queue.isEmpty()) {
			Edge now = queue.poll();
			if (visited[now.number]) {
				continue;
			}
			visited[now.number] = true;

			for (Edge next : edgeList[now.number]) {
				if (distance[next.number] > distance[now.number] + next.power) {
					distance[next.number] = distance[now.number] + next.power;
					queue.add(new Edge(next.number, distance[next.number]));
				}
			}
		}
		System.out.println(distance[end]);
	}
}

class Edge implements Comparable<Edge> {
	int number;
	int power;

	Edge(int number, int power) {
		this.number = number;
		this.power = power;
	}

	public int compareTo(Edge o) {

		return this.power - o.power;
	}

}