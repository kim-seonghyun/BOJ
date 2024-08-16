// 1~N 까지의 부분집합 중에서 
// 재료 a,b가 동시에 들어있지 않은 버거 개수 출력

// input :
// T
// N M
// a b
// c d

// N : 재료의 개수
// M : 어울리지 않는 재료쌍의 개수

// a b : 각각 재료 쌍

// output :
// 버거의 가짓수.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int M;
	static int[][] pair;
	static BufferedReader br;
	static StringTokenizer st;
	static int count = 0;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int testCaseSize = Integer.parseInt(st.nextToken());
		for(int testCaseNumber=1; testCaseNumber <= testCaseSize; testCaseNumber++ ) {
			inputValue();
			getBurgerCount(0, new boolean[N]);
			System.out.printf("#%d %d\n", testCaseNumber, count);
			count=0;
		}
		
	}
	private static void getBurgerCount(int cnt, boolean[] isVisited) {
		// TODO Auto-generated method stub
		if(cnt == isVisited.length) {
					// 여기서 1하고 2가 동시에 들어가있으면 취소
					// 여기서 2하고 3이 동시에 들어가 있으면 취소
					for(int j =0; j<pair.length; j++) {
						if(isVisited[pair[j][0] -1] && isVisited[pair[j][1] -1]) {
							return;
						}
					}
				count++;
				return;
			
		}else {
			if(!isVisited[cnt]) {
				isVisited[cnt] = true;
				getBurgerCount(cnt + 1, isVisited);
				isVisited[cnt] = false;
				getBurgerCount(cnt + 1, isVisited);		
			}
		}
	}
	private static void inputValue() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		pair = new int[M][2];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			pair[i][0] = Integer.parseInt(st.nextToken());
			pair[i][1] = Integer.parseInt(st.nextToken());
		}
	}

}
