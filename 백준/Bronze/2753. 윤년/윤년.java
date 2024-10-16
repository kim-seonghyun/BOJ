import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int year = Integer.parseInt(br.readLine());
		
		if((year % 4 ==0 && year % 100 != 0 ) || year % 400 == 0) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
	}
}
