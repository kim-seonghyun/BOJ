
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 자석 정보를 저장하는 클래스
 * attribute:	자석 번호,극 정보, 최근 회전 방향(돌릴때마다 재설정해주기.)
 * method : 시계 반대 방향 회전, 시계방향 회전
 *  
				 시계 방향 회전 : 끝 pop -> 앞 push
				반시계 방향 회전 : 앞 pop -> 끝 push
 */
class Magnatic{
	LinkedList<Integer> poles = new LinkedList<>();
	

	public Magnatic(int[] inputPoles){
		
		for(int pole: inputPoles){
			this.poles.add(pole);
		}
	}
	void rotateWise(int direction){
		if(direction == -1){
			int pole = this.poles.pollFirst();
			this.poles.addLast(pole);
		}else if(direction == 1){
			int pole = this.poles.pollLast();
			this.poles.addFirst(pole);
		}
	}
	@Override
	public String toString() {
		return "Magnatic [poles=" + poles + "]";
	}

	
	int getLeft(){
		return this.poles.get(6);
	}

	int getRight(){
		return this.poles.get(2);
	}



}

/**
 * @author 김성현
 */
public class Solution {
	// 특이한 자석

	// 1. 문제 설명 :
		// 1.1 요소: 4개의 자석, 자석은 8개의 날(N과 S)을 가짐
		// 1.2 자석의 작동(k번 회전) :
			// 1.2.1 1회 회전 : 붙어있는 날의 자성이 다른경 반대방향 회전
		// 1.3 점수 계산 : 화살표 위치 자성이 N극 -> 0점, S극 -> 1,2,4,8점


	// 2. input :
		// T
		// K ( 자석 회전 횟수 )
		// 1~4번까지 8개 날의 자성 정보 배열
		// K개 줄, 자석 회전 정보(자석 번호, 회전 방향 {1 : 시계, -1 : 반시계})
	
	// 3. output :
		// ex) #1 10
		// 3.1 모든 자석 회전 끝난 후 획득 점수 총합
	
	// 4. 해결 방안:
		// 4.1 자석 정보 입력
		// 4.2 자석 회전
			// 4.2.1 원형, 시계, 반시계 방향으로 회전해야함 : 어떤 자료구조를쓸까? ArrayDeque? 오 ㅋㅋ
				// 4.2.1.2 시계 방향 회전 : 끝 pop -> 앞 push
				// 4.2.1.2 반시계 방향 회전 : 앞 pop -> 끝 push
			// 4.2.2 맞닿은 부분이 같은 극 : 반대방향 회전
				// 4.2.2.1 주변에 같은 극이 있는 경우 : 반대방향으로 회전시키고 인접한 자석 DFS로 반복 (각 방향으로 한번씩 돌릴까,,? 종료조건은 1이나 4일때)
				// 근데 인접한건 어떻게 알수있지? 연결리스트를 써볼까?
				// Prev, next가 존재하지 않을때까지 , 현재 자석 회전방향과 반대로 회전, prev, next로 이동
				// 4.2.2.2 주변에 같은 극이 없는 경우 : pass
			
			
		// 4.3 결과 출력
			// 자석 4개의 0번 인덱스의 극값에 따라 합을 구함.
	
	static int testCaseSize;
	static int K;
	static int[][] rotateOrders;
	static BufferedReader br;
	static StringTokenizer st;
	static ArrayList<Magnatic> magnaticList; 

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		// br = new BufferedReader(new FileReader("/Users/biggie/SSAFY/workspace/input.txt"));
		testCaseSize = Integer.parseInt(br.readLine());
		for(int testCaseNumber = 1; testCaseNumber <= testCaseSize; testCaseNumber++){
			inputValue();
			rorateMagnatic();
			System.out.printf("#%d %d\n",testCaseNumber,  outputResult());
		}
		
		
	}

    private static void inputValue() throws Exception{
        K = Integer.parseInt(br.readLine());
		magnaticList = new ArrayList<>();
		rotateOrders = new int[K][2];
		for(int i=0; i<4; i++){
			st = new StringTokenizer(br.readLine());
			int[] tmp = new int[8];
			for(int j=0; j<8; j++){
				tmp[j] = Integer.parseInt(st.nextToken());
			}
			Magnatic tmpMagnatic = new Magnatic(tmp);
			
			magnaticList.add(tmpMagnatic);
		}

		for(int i=0; i<K; i++){
			st = new StringTokenizer(br.readLine());
			rotateOrders[i][0] = Integer.parseInt(st.nextToken()) -1;
			rotateOrders[i][1] = Integer.parseInt(st.nextToken());
		}
		
    }
	// 4.2 자석 회전
				// 4.2.1 원형, 시계, 반시계 방향으로 회전해야함 : 어떤 자료구조를쓸까? ArrayDeque? 오 ㅋㅋ
					// 4.2.1.2 시계 방향 회전 : 끝 pop -> 앞 push
					// 4.2.1.2 반시계 방향 회전 : 앞 pop -> 끝 push
				// 4.2.2 맞닿은 부분이 같은 극(2번 인덱스)) : 반대방향 회전
					// 4.2.2.1 주변에 같은 극이 있는 경우 : 반대방향으로 회전시키고 인접한 자석 회전 (각 방향으로 한번씩 돌릴까,,? 종료조건은 1이나 4일때)
					
					// 4.2.2.2 주변에 같은 극이 없는 경우 : pass
    private static void rorateMagnatic() {
        for(int[] orderInfo:rotateOrders){
			int selectedMagneticNum = orderInfo[0];
			int rotateDirection = orderInfo[1];
			
			
			
			rotateLeftMagnetic(selectedMagneticNum, rotateDirection);
			rotateRightMagnetic(selectedMagneticNum, rotateDirection);
			magnaticList.get(selectedMagneticNum).rotateWise(rotateDirection);
			

			
		}
    }

	private static void rotateLeftMagnetic(int selectedMagneticNum, int startDirection){
		ArrayList<Integer> changeMagnaticIndeies = new ArrayList<>();
		for(int i= selectedMagneticNum; i>0; i--){
			if(magnaticList.get(i).getLeft() != magnaticList.get(i-1).getRight()){
				changeMagnaticIndeies.add(i-1);
			}else{
				break;
			}
		}
		for(int index: changeMagnaticIndeies){
			startDirection *= -1;
			magnaticList.get(index).rotateWise(startDirection);
		}
	}

    private static void rotateRightMagnetic(int selectedMagneticNum, int startDirection) {
		ArrayList<Integer> changeMagnaticIndeies = new ArrayList<>();
        for(int i= selectedMagneticNum; i< 3; i++){
			if(magnaticList.get(i).getRight() != magnaticList.get(i+1).getLeft() ){
				changeMagnaticIndeies.add(i+1);
			}else{
				break;
			}
		}
		for(int index: changeMagnaticIndeies){
			startDirection *= -1;
			magnaticList.get(index).rotateWise(startDirection);
			
		}
		
    }

    private static int outputResult() {
		int scoreSum =0;
        for(int i=0; i<4; i++){
			if(magnaticList.get(i).poles.peek() == 1){
				scoreSum += Math.pow(2, i);
			}
		}
		return scoreSum;
    }

}
