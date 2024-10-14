import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int[] board;
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int value = 0;
		for(int i=0; i<5; i++) {
			value += Math.pow(Integer.parseInt(st.nextToken()), 2) ;
		}
		System.out.println(value % 10);
		
	}
}
