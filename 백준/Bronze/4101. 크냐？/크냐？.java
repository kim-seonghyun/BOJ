import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String[] numbers = br.readLine().split(" ");
			int A = Integer.parseInt(numbers[0]);
			int B = Integer.parseInt(numbers[1]);
			if (A == 0 && B == 0) {
				break;
			}
			if (A > B) {
				System.out.println("Yes");
			}else {
				System.out.println("No");
			}
		}
		
		
	}

}
