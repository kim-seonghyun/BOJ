import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 1. 문제 설명
 * 사용자가 이동할때 BC의 범위에 들어가면 P/범위 내 사용자수 만큼 충전됨.
 * 거리 (| X1 - X2 |+ |Y1 - YB| )
 *
 * 2. input
 * T
 * M(총 이동시간) A(BC의 개수)
 * int[] M A의 이동 정보 (0 , 1, 2, 3, 4)
 * int[] M B의 이동 정보
 * BC[A][4] BC의 정보 X Y C(충전 범위) P(처리량)
 *
 *
 * 3. output : #1 1200
 * 모든 사용자 충전량 합의 최대 값.
 *
 * 4. 해결 방법
 * 4.1. 시간 순으로 진행
 * 4.2. 특정 시간에 최대 충전량 구하기
 * 4.2.1 2명 모두 다른 BC에만 접근할수 있을때
 *  4.2.1.1 각각 최대값을 더한다.
 * 4.2.2 2명 모두 같은 BC에만 접근할 수 있을때
 *  4.2.2.1 같은 BC중 최대 BC / 2로 더한다.
 * 4.2.3 2명 모두 같거나 다른 BC에 접근할 수 있을때
 *  4.2.3.1 2명 각각 BC에 접근하는 경우를 따로 구해서 최대값을 구한다.
 * 4.2.4 2명 모두 BC에 접근할 수 없을때. : 패스
 *
 * 4.3 결과 출력
 *
 * 충전범위가 겹치는 경우가 문제임.,
 * 정확히 어떤걸 모르겠지?
 * 여러대의 충전범위가 겹칠떄?
 * 그럼 A가 선택할수있는 것중 하나, B가 선택할수있는것중 하나 구해서 곱하면 해결할 수 있나?
 * 그 합의 최대값을 저장하면 되잖아.
 * 그러니까 같은걸할지 아니면 각자 다른걸 고를지
 *
 *
 * A, B는 매 시간 다르게 움직여서, A, B가 만나는 BC 배열은 따로 관리할 필요가 있다.
 * 특정 상황에 무조건 최선의 충전량이다 를 알수 없으므로
 * A, B의 BC 배열에서 A,B가 특정 값을 선택하는 조합을 모두 구해야 한다.
 * 아이디어 생각해냈으면 검증하고 넘어가야 생각을 더 쉽게 할 수 있을거 같다.
 * A가 접근 가능한 BC 배열 구하기 연산 10번
 * B가 접근 가능한 BC 배열 구하기 연산 10번
 * 2중 for문 돌면서 최대값 찾기. 10 * 10 이지 한데?
 *
 */

class BatteryCharger{
    int x;
    int y;
    int c;
    int p;

    public BatteryCharger(int x, int y, int c, int p){
        this.x = x;
        this.y = y;
        this.c = c;
        this.p = p;
    }
}

class Person{
    int x;
    int y;

    public Person(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static int M;
    static int A;
    static int[] moveA;
    static int[] moveB;
    static BatteryCharger[] bc;
    static Person personA;
    static Person personB;

    static int[] xDirection = {0, 0,1, 0, -1};
    static int[] yDirection = {0,-1,0, 1, 0};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
//        br = new BufferedReader(new FileReader("input.txt"));
        int t = Integer.parseInt(br.readLine());

        for(int tc= 1; tc<=t; tc++){
            inputValue();
            System.out.printf("#%d %d\n", tc, getChargeAmount());
        }


    }

    private static ArrayList<BatteryCharger> getBC(int x, int y){
        ArrayList<BatteryCharger> tmp = new ArrayList<>();
        for(BatteryCharger b:bc){
            int distance = Math.abs(x - b.x) + Math.abs(y - b.y);
            if(b.c >= distance){
                tmp.add(b);
            }
        }
        return tmp;
    }

    private static int getChargeAmount() {
        // move 만큼 움직이기
        int totalChargeAmount = 0;
        for(int i=0; i<M; i++){
            // class를 사용해서 데이터 정보를 읽기 쉽게 하자.

            int aDir = moveA[i];
            int bDir = moveB[i];

            personA.x += xDirection[aDir];
            personA.y += yDirection[aDir];

            personB.x += xDirection[bDir];
            personB.y += yDirection[bDir];



            ArrayList<BatteryCharger> accessA = getBC(personA.x, personA.y);
            ArrayList<BatteryCharger> accessB = getBC(personB.x, personB.y);
            int maxBC = 0;

            if(accessB.isEmpty() && accessA.isEmpty()){
                continue;
            }

            if(!accessA.isEmpty() && accessB.isEmpty()){
                for(BatteryCharger bc1: accessA){
                    maxBC = Math.max(maxBC ,bc1.p);
                }
                totalChargeAmount+= maxBC;
                continue;
            }

            if(accessA.isEmpty()){
                for(BatteryCharger bc2: accessB){
                    maxBC = Math.max(maxBC ,bc2.p);
                }
                totalChargeAmount+= maxBC;
                continue;
            }


            for(BatteryCharger bc1: accessA){
                for(BatteryCharger bc2: accessB){
                    int currentPower;
                    if(bc1.x == bc2.x && bc1.y == bc2.y){
                        currentPower = bc1.p;
                    }else{
                        currentPower = bc1.p + bc2.p;
                    }
                    maxBC = Math.max(maxBC, currentPower);
                }
            }
            totalChargeAmount += maxBC;

        }
        return totalChargeAmount;
        // A, B의 접근 가능한 배열 구하기

        // 2중 for문으로 특정 시간 최대 충전량 구하기.
    }

    private static void inputValue() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()) + 1;
        A = Integer.parseInt(st.nextToken());
        moveA = new int[M];
        moveB = new int[M];
        bc = new BatteryCharger[A];
        st = new StringTokenizer(br.readLine());
        moveA[0] = 0;
        for(int i=1; i<M; i++){
            moveA[i] =  Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        moveB[0] = 0;
        for(int i=1; i<M; i++){
            moveB[i] = Integer.parseInt(st.nextToken());
        }

        personA = new Person(0,0);
        personB = new Person(9,9);
        for(int i=0; i<A; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) -1;
            int y = Integer.parseInt(st.nextToken()) -1;
            int c = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            BatteryCharger b = new BatteryCharger(x,y,c,p);
            bc[i]= b;
        }
    }
}
