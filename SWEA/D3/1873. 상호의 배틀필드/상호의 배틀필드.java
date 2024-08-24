import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 문제 설명 :
 * .	평지(전차가 들어갈 수 있다.)
 * *	벽돌로 만들어진 벽
 * #	강철로 만들어진 벽
 * -	물(전차는 들어갈 수 없다.)
 * ^	위쪽을 바라보는 전차(아래는 평지이다.)
 * v	아래쪽을 바라보는 전차(아래는 평지이다.)
 * <	왼쪽을 바라보는 전차(아래는 평지이다.)
 * >	오른쪽을 바라보는 전차(아래는 평지이다.)
 *
 * 입력 종류 ( 전차의 이동)
 * 방향 바꾸고 평지라면 한칸 이동
 * U, D, L, R, S
 *
 * 포탄 발사
 * 벽돌(파괴), 강철(파괴 x)에 충돌 또는 맵밖 까지 직진
 *
 *  2. input
 *  T
 *  H W
 * H * W 배열 문자열
 * N
 * N 배열 입력종류 나타내는 문자열
 *
 *
 * 3. output #1 모든 입력 처리 후의 맵
 *
 * 4. 해결 방법 :
 * 4.1. 전차 이동 ( 배열 범위 내, 방향 전환후 다음 인덱스가 평지일때)
 * 4.1.1. 전차 방향 전환
 * 4.2. 포탄 발사
 * 4.2.1 한줄로 쭉
 * 4.2.2 벽 만나면 멈츰
 *  4.2.2.1 벽돌 -> 부숨
 *  4.2.2.2 강철 -> 못부숨
 *
 */
public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static int H;
    static int W;
    static int N;
    static char[][] board;
    static char[] inputs;
    static int tankX;
    static int tankY;

    public static void main(String[] args) throws IOException {
//        br = new BufferedReader(new FileReader("input.txt"));
        br= new BufferedReader(new InputStreamReader(System.in));
        int t= Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=t; tc++){
            System.out.printf("#%d ", tc);
            inputValue();
            for(char input: inputs){
                changeDirection(input);
            }
            for(int i=0; i<H; i++){
                for(int j=0; j<W; j++){
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
        }
    }

    private static void changeDirection(char input) {
        switch (input){
            case 'U':
                board[tankX][tankY] = '^';
                move(tankX -1, tankY );
                break;
            case 'D':
                board[tankX][tankY] = 'v';
                move(tankX + 1, tankY);
                break;
            case 'L':
                board[tankX][tankY] = '<';
                move(tankX, tankY -1);
                break;
            case 'R':
                board[tankX][tankY] = '>';
                move(tankX, tankY +1);
                break;
            case 'S':
                shoot();
        }
    }

    private static void move(int nx, int ny){
        if(nx >=0 && nx < H && ny >=0 && ny < W && board[nx][ny] == '.'){
            board[nx][ny] = board[tankX][tankY] ;
            board[tankX][tankY] = '.';
            tankX = nx;
            tankY = ny;
        }
    }

    private static void shoot(){
        int currentDirection = board[tankX][tankY];
        switch (currentDirection){
            case '^':
                for(int i=tankX-1; i>=0; i--){
                    if(board[i][tankY] == '#'){
                        break;
                    }
                    if(board[i][tankY] == '*'){
                        board[i][tankY] = '.';
                        break;
                    }
                }
                break;
            case 'v':
                for(int i=tankX+1; i<H; i++){
                    if(board[i][tankY] == '#'){
                        break;
                    }
                    if(board[i][tankY] == '*'){
                        board[i][tankY] = '.';
                        break;
                    }
                }
                break;
            case '>':
                for(int i=tankY+1; i<W; i++){
                    if(board[tankX][i] == '#'){
                        break;
                    }
                    if(board[tankX][i] == '*'){
                        board[tankX][i] = '.';
                        break;
                    }
                }
                break;
            case '<':
                for(int i=tankY-1; i>=0; i--){
                    if(board[tankX][i] == '#'){
                        break;
                    }
                    if(board[tankX][i] == '*'){
                        board[tankX][i] = '.';
                        break;
                    }
                }
                break;
        }
    }



    private static void inputValue() throws IOException {
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        board = new char[H][W];
        for(int i=0; i<H; i++){
            String tmp = br.readLine();
            for(int j = 0; j<W; j++){
                board[i][j] = tmp.charAt(j);
                if(board[i][j] == '>' || board[i][j] == '<' || board[i][j] == '^' || board[i][j] == 'v'){
                    tankX = i;
                    tankY = j;
                }
            }
        }
        N = Integer.parseInt(br.readLine());
        String tmp = br.readLine();
        inputs = new char[N];
        for(int i=0; i< N; i++){
            inputs[i] = tmp.charAt(i);
        }
    }
}
