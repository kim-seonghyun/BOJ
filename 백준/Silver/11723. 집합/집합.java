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
	static HashSet<Integer> set;

	public static void main(String[] args) throws NumberFormatException, IOException {
		List<Integer> all = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			all.add(i);
		}
		
		set = new HashSet<Integer>();
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "add":
				set.add(Integer.parseInt(st.nextToken()));
				break;
			case "check":
				if(set.contains(Integer.parseInt(st.nextToken()))) {
					sb.append("1\n");
				}else {
					sb.append("0\n");
				}
				
				break;

			case "remove":
				set.remove(Integer.parseInt(st.nextToken()));
				break;
			case "toggle":
				int num = Integer.parseInt(st.nextToken());
				if (!set.contains(num)) {
					set.add(num);
				} else {
					set.remove(num);
				}
				break;
			case "all":
				set.clear();
				set.addAll(all);
				break;
			case "empty":
				set.clear();
				break;
			}
		}
		System.out.println(sb.toString());
	}
}
