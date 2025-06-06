import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 2659
// 사탕게임 문제
public class Main {
	


	public static void main(String[] args) throws NumberFormatException, IOException {
		// 2를 곱한다.
		// 1을 수의 가장 오른쪽에 추가한다.
		// 들어오는 수마다 * 2, "" + 1을 추가하여 queue에 추가하기
		// 이걸 클래스로 만들어서 count를 기록한다면?
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
	
		Queue<long[]> queue = new LinkedList<>();
		long[] arr = {A,0};
		queue.add(arr);
		while(!queue.isEmpty()) {
			long[] current = queue.poll();
			if(current[0] > B) {
				continue;
			}
			if(current[0] == B) {
				System.out.println(current[1] + 1);
				return;
			}
			long[] multiple2 = {current[0] * 2, current[1] +1};
			long[] plus1 = {Long.parseLong(String.valueOf(current[0]) + "1") ,current[1] + 1}; 
			queue.add(multiple2);
			queue.add(plus1);
		}
		
		System.out.println(-1);
	}
}