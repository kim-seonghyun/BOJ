import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * 1. 문제 설명
 * N*N 배열에 1~ N^2의 수가 적힘
 * 어떤방에 있다면 상하 좌우로이동가능
 * 이동하려는 방에 적힌 숫자가 현재 방에 적힌 숫자 + 1
 * 처음 어떤 수가 적힌 방에서 있어야 최대 방을 방문 하나? 
 * 
 * 2. input
 * T
 * N
 * N*N 배열에 1 ~N^2의 정수 
 * 
 * 
 * 3. output : #1 1 2
 * #테케 번호 출발 방 번호, 이동한 방의 개수 
 * 
 * 4. 해결 방법 - 최대한 간단하고 단순하게

 *  
 */
public class Solution {
	static int N;
	static int[][] arr;
	static BufferedReader br;
	static StringTokenizer st;
	static int[] xDirection = {0,0,1,-1};
	static int[] yDirection = {1,-1,0,0};
	static int maxValue = Integer.MIN_VALUE;
	static int startIndex = Integer.MAX_VALUE;
	static int[] result;
	public static void main(String[] args) throws NumberFormatException, IOException {
	// input
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			inputValue();
			
			for(int i =0; i<N; i++) {
				for(int j=0; j<N; j++) {
					dfs(1, i, j);
					result[arr[i][j]] = maxValue;
					maxValue = Integer.MIN_VALUE;
				}
			}
			
		
			int maxVal = Integer.MIN_VALUE;
			for(int i=1; i<result.length; i++) {
				maxVal = Math.max(maxVal, result[i]);
			}
			
			for(int i=1; i<result.length; i++) {
				if(result[i] == maxVal) {
					System.out.printf("#%d %d %d\n", tc, i, maxVal);
					break;
				}
			}
			
			
		}
	
	
		
	}

	private static void dfs(int distance, int i, int j) {
		if(distance >= maxValue) {
			maxValue = distance;
		}
		
		for(int dir = 0; dir<4; dir++) {
			int nx = i + xDirection[dir];
			int ny = j + yDirection[dir];
			
			if(nx >=0 && nx < N && ny >=0 && ny< N) {
				if(arr[nx][ny] == arr[i][j] + 1) {
					dfs(distance+1, nx,ny);
				}
			}
		}
		

		
		
	}

	private static void inputValue() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		result = new int[N*N +1];
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		
	}

}
