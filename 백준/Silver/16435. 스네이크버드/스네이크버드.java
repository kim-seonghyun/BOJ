import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Stream.of(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = input[0];
		int L = input[1];
		
		int[] fruits = Stream.of(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		Arrays.sort(fruits);
		
		for(int i=0; i<fruits.length; i++) {
			if(L < fruits[i]) {
				
				break;
			}
			L++;
		}
		System.out.println(L);
	}

}
