
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Student{
    int studentNum;
    ArrayList<Integer> likeStudents = new ArrayList<>();

    public Student(int studentNum, int likeStudent1, int likeStudent2,int likeStudent3, int likeStudent4) {
        this.studentNum= studentNum;
        likeStudents.add(likeStudent1);
        likeStudents.add(likeStudent2);
        likeStudents.add(likeStudent3);
        likeStudents.add(likeStudent4);
    }
}
// 특정 배열에 좋아하는 학생이 있을때마다 count 하여 새로운 객체 ((행,열, 인접한 좋아하는 학생 수, 비어있는 칸 수)를 생성하여 queue에 넣고 정렬한다. ?
class SeatInfo implements Comparable<SeatInfo>{
    int row;
    int col;
    int likeStudentCount;
    int emptySeatCount;

    public SeatInfo(int row,
                    int col,
                    int likeStudentCount,
                    int emptySeatCount) {
        this.row = row;
        this.col = col;
        this.likeStudentCount = likeStudentCount;
        this.emptySeatCount = emptySeatCount;
    }



    @Override
    public String toString() {
        return "SeatInfo [row=" + row + ", col=" + col + ", likeStudentCount=" + likeStudentCount + ", emptySeatCount="
                + emptySeatCount + "]";
    }



    @Override
    public int compareTo(SeatInfo o) {
        if(this.likeStudentCount > o.likeStudentCount) {
            return -1;
        }else if(this.likeStudentCount == o.likeStudentCount) {
            if(this.emptySeatCount > o.emptySeatCount) {
                return -1;
            }else if(this.emptySeatCount == o.emptySeatCount) {
                if(this.row < o.row) {
                    return -1;
                }else if(this.row == o.row) {
                    if(this.col < o.col) {
                        return -1;
                    }else if (this.col == o.col){
                        return 0;
                    }
                }
            }
        }




        return 1;
    }
}

/**
 * @author 김성현
 */
public class Main {
    // 자리 채우는 규칙
    // 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
    // 2. 1을 만족하는 칸이 여러개 이면, 인접한 칸중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
    // 3. 2를 만족하는 칸도 여러개인 경우에는 행의 번호가 가장 작은칸으로, 그러한칸도 여러개 이면 열의 번호가 가장 작은 칸으로 자리를 정한다.

    // 특정 학생이 앉을수 있는 자리수를 좁히는 방식으로?
    // 근데 턴수, 학생에 따라 앉을 자리가 변한다.
    // 각 턴마다 앉을수 있는 자리는 변한다.
    // 각 턴마다 특정 학생이 앉을 자리를 고려해야한다.
    // 앉을 자리는 인접한 칸을 고려한다.
    // 인접한 칸에는
    // 1. 특정학생이 좋아하는 학생 번호 목록
    // 2. 비어있는 칸이 있다.
    // 어떤 알고리즘으로 접근해야 되냐,,
    // 1번 규칙은 턴마다 가능한 경우가 바뀐다.
    // 학생수는 최대 400명
    // 400 * 20 * 20 = 1600?
    // 이거 학생 자리 한명 정하고
    // 자리마다 정렬 돌려도 될듯?

    // 풀이 순서
    // 0. 각 자리마다 정보를 저장하는 class 생성하기
    // 1. 각 자리마다 인접한 학생번호, 비어있는 칸, 행, 열 계산하여 저장하기.
    // 2. 각 자리마다 인접한 학생 번호 목록에 좋아하는 학생의 번호
    // 3.

    // 근데 매번 바뀌는데 클래스 저장할 필요가 없다

    // 4번 학생이 자리를 선택하는 경우 -> 400
    // 2차원 배열을 순회 하면서 4방탐색을 한다. -> 20 * 20
    // 특정 배열에 2,5,1,7 이 있을때마다 count 센 후, 새로운 객체 (행,열, 인접한 좋아하는 학생 수, 비어있는 칸 수)를 생성하여 queue에 넣고 정렬한다. ?
    // 정렬 후 적절한 값에 4번 학생을 대입한다.
    //
    //

    static int classSize;
    static BufferedReader br;
    static StringTokenizer st;
    static ArrayList<Student> students;
    static int[][] seatTable;
    static boolean[][] visited;
    static int[] xDirection = {0 ,0 ,1, -1};
    static int[] yDirection = {1, -1, 0 ,0};
    static SeatInfo[][] seatResult;
    public static void main(String[] args) throws IOException {

        // 입력 받기
        inputValue();



        // 모든 학생을 순회 하면서
        for(Student student : students) {

            ArrayList<SeatInfo> seats = new ArrayList<>();
            for(int row = 0; row < classSize; row++) {
                for(int col =0; col < classSize; col++) {
                    if(seatTable[row][col] > 0) {
                        continue;
                    }

                    // 특정 학생이 자리를 선택하는경우
                    int likeStudentCount = 0;
                    int emptyStudentCount = 0;
                    // 2차원 배열을 순회하면서 4방탐색을 한다
                    for(int direction=0; direction< xDirection.length; direction++) {
                        int nextX = row + xDirection[direction];
                        int nextY = col + yDirection[direction];

                        // 특정 배열에 좋아하는 학생이 있을때마다 count 하여 새로운 객체 ((행,열, 인접한 좋아하는 학생 수, 비어있는 칸 수)를 생성하여 queue에 넣고 정렬한다. ?
                        if(0 <= nextX && nextX < classSize && 0 <=nextY && nextY < classSize) {

                            if(seatTable[nextX][nextY] == 0) {
                                emptyStudentCount++;
                                continue;
                            }

                            if(student.likeStudents.contains(seatTable[nextX][nextY])) {
                                likeStudentCount++;
                                continue;
                            }
                        }
                    }
                    seats.add(new SeatInfo(row, col, likeStudentCount, emptyStudentCount));

                }
            }
            // 적절한 배열 위치에 특정 학생을 대입한다.
            Collections.sort(seats);
            SeatInfo currentSeat = seats.get(0);
            seatTable[currentSeat.row][currentSeat.col] = student.studentNum;


        }
        int likeScore = 0;


        // 각 테이블에서 우호도 계산하기.
        // 학생 순회하면서
        // 테이블에서 특정학생의 번호 찾기.
        // 찾으면 사방 탐색해서 우호도 계산
        // 반복

        for(Student student : students){
            int stdNum = student.studentNum;;

            for(int i=0; i<classSize; i++){
                for(int j =0; j<classSize; j++){
                    int likeCount = 0;
                    if(seatTable[i][j] == stdNum){

                        for(int direction=0; direction< xDirection.length; direction++) {
                            int nextX = i + xDirection[direction];
                            int nextY = j + yDirection[direction];
                            if(0 <= nextX && nextX < classSize && 0 <=nextY && nextY < classSize) {
                                if(student.likeStudents.contains(seatTable[nextX][nextY])) {
                                    likeCount++;

                                }
                            }
                        }

                    }
                    if(likeCount == 1){
                        likeScore += 1;
                    } else if (likeCount == 2) {
                        likeScore += 10;
                    }else if(likeCount == 3){
                        likeScore += 100;
                    } else if (likeCount == 4) {
                        likeScore += 1000;
                    }

                }
                }

        }

        System.out.println(likeScore);


    }
    private static void inputValue() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        classSize = Integer.parseInt(st.nextToken());
        students = new ArrayList<>();
        seatResult = new SeatInfo[classSize][classSize];
        visited = new boolean[classSize][classSize];
        seatTable = new int[classSize][classSize];
        for(int i=0; i< classSize * classSize; i++) {
            st = new StringTokenizer(br.readLine());
            students.add(new Student(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }
    }



}


