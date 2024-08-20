import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/**
 * @author kimseonghyun
 * 1. 문제 설명 : 정사각형 마름모 형태의 농작물 수익 계산.
 *	1.1 배열 특징 : N * N, 농장 크기는 항상 홀수, 정사각형 마름모 형태로만 가능
 *
 * 2. input :
 * 	T
 * 	N
 * 	N * N 개의 농작물 수익 배열
 * 
 * 3. output : 
 * #t 농작물의 가치
 * 
 * 4. 해결방법 : 
 * 주어진 N의 N/2 행은 N개를 읽고
 * N/2 +- 1행은 N-2개를 읽는다.
 * 각 행에서 1개의 값을 읽을때 까지 행의 값을 1씩 더하고 빼면서 값을 읽는다. 
 * 
 * 5. 걸린시간 : 40분
 * 
 * 6. 난이도 : 중
 * 
 * 7. 어려운 점 : 마름모 꼴로 배열을 읽는 아이디어 떠올리기가 어려웠다. 
 *  이런 배열 문제 구현은 머리속으로 생각하지말고 작은 케이스 부터 하나씩 쓰면서 규칙을 찾으면 더 빠르게 구현할수 있을것 같다.
 */
public class Solution {
	
	static int N;
	static BufferedReader br;
	static StringTokenizer st;
	static int[][] farm;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int T= Integer.parseInt(br.readLine());
		for(int testCaseNumber=1; testCaseNumber<=T; testCaseNumber++) {
			inputValue();
			System.out.printf("#%d %d\n", testCaseNumber, calculateIncome());
		}
		
	}
	
	// 마름모 꼴의 합 계산
	// 
	//  for문 : 읽을 수 count (1 ~N) , 행은 0~N/2까지
	//  (N- 읽을 수 개수) /2 부터 읽을 수 count 만큼 더하기
	private static int calculateIncome() {
		int sum = 0;
		for(int count = 1, row=0; count<=N || row <N/2; count+=2, row++) {
			for(int i = 0; i<count; i++) {
				sum += farm[row][(N - count) / 2 +i];
				// N/2 번째 행은 1번만 연산 하게 함.
				if(count != N) {
					sum += farm[N-row -1][(N - count) / 2 +i];
				}
			}
		}
		return sum;
	}

	private static void inputValue() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		farm = new int[N][N];
		for(int i=0; i<N; i++) {
			String tmp = br.readLine();
			for(int j=0; j<N; j++) {
				farm[i][j] = tmp.charAt(j) -'0';
			}
		}
		
		
		
		
	}

}
