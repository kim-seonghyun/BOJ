import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

/**
 * 
 * 
 * @author 김성현
 *         1. 문제 설명
 *         전체 치킨집중 M개의 치킨집을 선택했을때 치킨 거리의 최소합 구하기.
 * 
 *         2. input
 *         N M
 *         N* N의 도시 정보 (0 : 빈칸 , 1: 집, 2: 치킨집)
 * 
 *         3. output
 *         M개의 치킨집을 선택했을때 1과 2의 거리합의 최소.
 * 
 *         4. 해결방법
 *         전체 치킨집 좌표 저장
 *         가정집 좌표 저장
 *         M개의 치킨집 고르는 조합 구하기
 *         1과 2의 거리합을 구하고 최소값 갱신
 *         결과 출력
 * 
 *
 */
public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static ArrayList<int[]> cBoard = new ArrayList<>();
    static ArrayList<int[]> hBoard = new ArrayList<>();
    static int minChicken;
    static int[] board;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int tmp = parseInt(st.nextToken());
                if (tmp == 1) {
                    int[] tmpP = { i, j };
                    hBoard.add(tmpP);
                } else if (tmp == 2) {
                    int[] tmpP = { i, j };
                    cBoard.add(tmpP);
                }
            }
        }
        board = new int[m];
        minChicken = Integer.MAX_VALUE;
        combination(0, 0);
        System.out.println(minChicken);
    }

    private static void combination(int depth, int currentNumber) {
        if (depth == m) {
            int total = 0;
            for (int[] h : hBoard) {
                int cDistance = Integer.MAX_VALUE;

                int hx = h[0];
                int hy = h[1];
                for (int i = 0; i < board.length; i++) {
                    int cx = cBoard.get(board[i])[0];
                    int cy = cBoard.get(board[i])[1];
                    int current = Math.abs(cx - hx) + Math.abs(cy - hy);
                    cDistance = Math.min(cDistance, current);
                }
                total += cDistance;
            }
            minChicken = Math.min(minChicken, total);
            return;
        }

        for (int i = currentNumber; i < cBoard.size(); i++) {
            board[depth] = i;
            combination(depth + 1, i + 1);
        }

    }
}
