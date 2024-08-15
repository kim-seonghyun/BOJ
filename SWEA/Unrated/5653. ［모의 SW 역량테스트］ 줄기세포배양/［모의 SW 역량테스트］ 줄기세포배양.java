import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * @author 김성현
 * 
 * 줄기세포 배양 시뮬레이션
 * 
 * 1. 줄기세포
 * 1.1 attribute : hp, inactive, active, dead, isChanged (동시에 번식 하는 경우), x,y
 * 1.2 method : 
 * 
 */


 class StemCell{
    int hp;
    int createdTime;
    int status;
    

    public StemCell(int hp, int createdTime){
        this.hp = hp;
        this.createdTime = createdTime;
        this.status = 2;
    }

   

    
}
public class Solution {
    // 1. 줄기 세포 class
        // 1.1 attribute : hp (생명력 ), inactive, active, dead, x,y
        // 1.2 method : ?

    // 2. 규칙 : 비활성화 -> 활성화 (+번식) -> 죽음
        // 2.1 활성화 된 줄기세포는 첫 1시간 동안 상,하, 좌,우 4방향으로 동시 번식
        // 2.2 번식 된 후는 비활성 상태
        // 2.3 두개 이상의 줄기 세포가 하나의 그리드 셀에 번식할때 : 생명력 수치가 높은 줄기세포가 혼자 차지
        
    // 3. input - o
        // N , M , K
        // M개의 hp row 배열

        // N : 세로크기
        // M : 가로크기
        // K : 배양 시간

    
    
    // 4. output
        // #test_case 줄기 세포 (비활성 상태 + 활성 상태)

    // 5. 풀이 방법 -> K시간동안 2차원 배열 순회 : 300 * 300 * 300 -> 쌉가능
        // 5.1 입력 받기

        // 5.2 
    


// 3. input
        // N , M , K
        // M개의 hp row 배열

        // N : 세로크기
        // M : 가로크기
        // K : 배양 시간
    static BufferedReader br;
    static StringTokenizer st;
    static int cellCultureDishRow;
    static int cellCultureDishColumn;
    static int cultivateTime;
    static StemCell[][] cultivator;

    static int[] xDirection = {0, 0,1,-1};
    static int[] yDirection = {1, -1,0, 0};

    static final int DEACTIVATE = 2;
    static final int ACTIVATE = 3;
    static final int DEAD = 4;
    

    // 1. 줄기세포
    // 1.1 attribute : hp, inactive, active, dead, isChanged (동시에 번식 하는 경우), x,y
    // 1.2 method : 
 
    public static int getAliveStemCellCount(){
        for(int time =1; time <= cultivateTime; time++){

            for(int row=0; row< 401; row++){
                for(int col=0; col < 401; col++){
                    StemCell cell = cultivator[row][col];
                    if(Objects.isNull(cell) || cell.status == DEAD){
                        continue;
                    }
                    // 3.1 비활성 -> 활성 상태
                    if((cell.createdTime + cell.hp) == time  && cell.status == DEACTIVATE){
                       cell.status = ACTIVATE;
                        
                    // 3.2 활성 상태 -> 번식
                    }else if(((cell.createdTime +cell.hp + 1) == time) && cell.status == ACTIVATE){
                        for(int dir=0; dir<4; dir++){
                            int nextX = row + xDirection[dir];
                            int nextY = col + yDirection[dir];

                            if(nextX >= 0 && nextX < 401 && nextY >= 0 && nextY < 401){
                                StemCell otherCell = cultivator[nextX][nextY];
                                if(Objects.isNull(otherCell)){
                                    cultivator[nextX][nextY] = new StemCell(cell.hp, time);
                                    
                                }else if(otherCell.createdTime == time && otherCell.hp < cell.hp){
                                    cultivator[nextX][nextY] = new StemCell(cell.hp, time);
                                }
                                
                            }
                        }
                        
                    }if((cell.createdTime + cell.hp * 2) == time){
                        cell.status = DEAD;
                    }

                   
                }
            }
            
            
            
            
            
            
        }
        int count =0;
        for(int row=0; row< 401; row++){
            for(int col=0; col < 401; col++){
                if(Objects.isNull(cultivator[row][col])){
                    continue;
                }
                if(cultivator[row][col].status != DEAD){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int testCaseSize = Integer.parseInt(st.nextToken());
        // 값 입력 받기
        
        
       
        // 5. 풀이 방법 -> K시간동안 2차원 배열 순회 : 300 * 300 * 300 -> 쌉가능
        // 5.1 입력 받기
        
        // 5.2 배열에 StemCell들 저장
        
        // 5.3 cultivator 배열 k 번 순회하기

        // 3. 케이스
                
                    // isActivated = true
                    // isCreated = false

                
                    // isActivated == true이면
                    // 첫 1시간 동안 상,하, 좌,우 4방향으로 동시 번식
                    // 3.2.1 두개의 줄기세포가 하나의 그리드 셀에 동시 번식
                        // 둘다 isCreated == true이면
                        // 생명력이 더 높은 쪽이 번식
                    // 3.2.2 기존에 번식 된 경우
                        // 패스
                    // 3.2.3 그리드가 비어있는 경우
                        // 바로 번식

                // 3.3 활성 상태 -> 죽음
                    // isActivate = false
                    // hp만큼 살다가 죽음 (생명력 * 2 시간에에 죽음)
        for(int testCaseNumber = 1; testCaseNumber <= testCaseSize; testCaseNumber++){
            inputValue();
            System.out.printf("#%d %d\n", testCaseNumber, getAliveStemCellCount());
        }

        
        
        
        
    }

    private static void inputValue() throws IOException {
        st = new StringTokenizer(br.readLine());
        cellCultureDishRow = Integer.parseInt(st.nextToken());
        cellCultureDishColumn = Integer.parseInt(st.nextToken());
        cultivateTime = Integer.parseInt(st.nextToken());
        cultivator = new StemCell[401][401];
        for(int i=0; i<cellCultureDishRow; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<cellCultureDishColumn; j++){
                int hp = Integer.parseInt(st.nextToken());
                if(hp == 0){
                    cultivator[i + 401/2][j + 401/2] = null;
                }else{
                    cultivator[i + 401/2][j + 401/2] = new StemCell(hp, 0);
                }
                
            }
            
        }
    }
}
