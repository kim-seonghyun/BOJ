import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2659
// 십자카드 문제
public class Main {
	private static final int[] direct_x = { 0, 1, 0, -1 };
	private static final int[] direct_y = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		String[][] arr = new String[N][N];

		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().split("");
		}
		int maxCandy = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				// 4방향 exchange
				for (int dir = 0; dir < direct_x.length; dir++) {
					int new_x = i + direct_x[dir];
					int new_y = j + direct_y[dir];
					if (0 <= new_x && new_x < N && 0 <= new_y && new_y < N) {
						String tmp = arr[i][j];
						arr[i][j] = arr[new_x][new_y];
						arr[new_x][new_y] = tmp;
					} else {
						continue;
					}
					maxCandy = Math.max(maxCandy, getMaxCandy(arr));
					String tmp = arr[i][j];
					arr[i][j] = arr[new_x][new_y];
					arr[new_x][new_y] = tmp;
				}
				// 사탕의 최대 개수 구하기

				// 원래대로.
			}
		}
		System.out.println(maxCandy);
	}

	private static int getMaxCandy(String[][] arr) {
		// TODO Auto-generated method stub
		int max = 0;
		// 행만 확인
		for(int i=0; i< arr.length; i++) {
			int count = 1;
			for(int j=1; j< arr.length; j++) {
				if( arr[i][j-1].equals(arr[i][j])) {
					count++;
				}else {
					max = Math.max(max, count);
					count = 1;
				}
			}
			max = Math.max(max, count);
		}
		
		
		for(int i=0; i< arr.length; i++) {
			int count = 1;
			for(int j=1; j< arr.length; j++) {
				if( arr[j-1][i].equals(arr[j][i])) {
					count++;
				}else {
					max = Math.max(max, count);
					count = 1;
				}
			}
			max = Math.max(max, count);
		}
		// 열만 확인 
		return max;
	}
}