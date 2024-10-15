import java.util.*;
import java.io.*;

public class Main {
	public static int a;
	public static int[] d = { -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Integer.parseInt(st.nextToken());
		long target = Integer.parseInt(st.nextToken());

		PriorityQueue<Node> queue = new PriorityQueue<>();

		queue.add(new Node(1, a));
		int result = 0;
		boolean flag = false;
		while (!queue.isEmpty()) {
			Node now = queue.poll();

			for (int i = 0; i < 2; i++) {
				long nextValue = calculate(now.value, d[i]);

				if (nextValue == target) {
					flag = true;
					result = now.count + 1;
					break;
				}

				if (nextValue < target) {
					queue.add(new Node(now.count + 1, nextValue));
				}

			}
		}

		if (flag) {
			System.out.println(result);
			return;
		}
		System.out.println(-1);

	}

	public static long calculate(long target, int type) {
		if (type == -1) {
			return target * 2;
		}
		return target * 10 + 1;
	}
}

class Node implements Comparable<Node> {
	int count;
	long value;

	Node(int count, long value) {
		this.count = count;
		this.value = value;
	}

	public int compareTo(Node o) {
		return this.count - o.count;
	}
}