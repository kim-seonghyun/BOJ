import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static HashMap<String, Boolean> map = new HashMap<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			map.put(br.readLine(), true);
		}
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < m; i++) {
			String tmp = br.readLine();
			if(map.containsKey(tmp)) {
				result.add(tmp);
			}
		}
		result.sort(null);
		System.out.println(result.size());
		for (String s : result) {
			System.out.println(s);
		}
	}
}
