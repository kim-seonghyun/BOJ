
// 9개의 숫자중에서 7개의 숫자 합이 100인 경우 찾기.
// 9C7의 숫자중 합이 100인 경우 출력하기

// input : 9개의 수

// output : 합이 100인 7개의 수

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] hatNumbers = new int[9];
	static BufferedReader br;
	static StringTokenizer st;
	static boolean[] visited = new boolean[9];
	static int[] numArr = new int[7];
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		inputValue();
		findHat(0, 0);
	}

	private static void findHat(int depth, int start) {
		// TODO Auto-generated method stub
		if(depth == 7) {
			int sum = 0;
			for(int i=0; i<7; i++) {
				sum += numArr[i];
			}
			if(sum == 100) {
				for(int i=0; i<7; i++) {
					System.out.println(numArr[i]);
				}
			}
			return;
		}
		
		for(int i=start; i<9; i++) {
			if(!visited[i]) {
				visited[i] = true;
				numArr[depth] = hatNumbers[i];
				findHat(depth + 1, i);
				visited[i] = false;
			}
		}
		
	}

	private static void inputValue() throws IOException {
		// TODO Auto-generated
		br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<hatNumbers.length; i++) {
			st = new StringTokenizer(br.readLine());
			hatNumbers[i] = Integer.parseInt(st.nextToken());
		}
	}

}
