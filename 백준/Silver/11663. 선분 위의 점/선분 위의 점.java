import java.util.*;
import java.io.*;

public class Main {
	static int[] points;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int pointCount = Integer.parseInt(st.nextToken());
		int sunCount = Integer.parseInt(st.nextToken());
		points = new int[pointCount];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < pointCount; i++) {
			points[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(points);
		for (int i = 0; i < sunCount; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			int aIndex = binarySearch(a, 0);
			int bIndex = binarySearch(b, 1);

			System.out.println(bIndex - aIndex);
		}

	}

	public static int binarySearch(int target, int type) {
		int left = 0;
		int right = points.length - 1;

		if (type == 0) {
			while (left <= right) {
				int mid = (left + right) / 2;

				if (points[mid] >= target) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			return left;
		} else {
			while (left <= right) {
				int mid = (left + right) / 2;

				if (points[mid] <= target) {
					left = mid + 1;

				} else {
					right = mid - 1;
				}
			}
			return right + 1;
		}
	}

}