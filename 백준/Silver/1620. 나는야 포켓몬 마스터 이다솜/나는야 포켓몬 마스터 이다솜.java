import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int m;
	
	static HashMap<Integer, String> board;
	static HashMap<String, Integer> board2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		inputValue();
	}

	private static void inputValue() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new HashMap<>();
		board2 = new HashMap<>();
		for(int i=1; i<=n; i++) {
			String tmp = br.readLine();
			board.put(i, tmp);
			board2.put(tmp , i);
		}
		
		for(int i=0; i< m; i++) {
			String tmp = br.readLine();
			if(isnumeric(tmp)) {
				sb.append(board.get(Integer.parseInt(tmp))).append("\n");
			}else {
				sb.append(board2.get(tmp)).append("\n");
			}
		}
		System.out.println(sb.toString());
		
		
	}
	
	public static boolean isnumeric(String str) {
		boolean result = true;
		for(char c:str.toCharArray()) {
			if(!Character.isDigit(c)) {
				result = false;
			}
		}
		return result;
	}
}
