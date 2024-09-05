import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author 김성현
 *         1. 문제 설명 : 가장 긴 등산로 만들기
 *         가장 높은 봉우리에서 시작
 *         한 곳을 정해서 K깊이 만큼 지형을 깎는다.
 *         <p>
 *         2. input
 *         T
 *         N K
 *         N *N의 지도 정보
 *         <p>
 *         3. output
 *         가장 긴 등산로 길이
 *         <p>
 *         4. 해결 방법
 *         <p>
 *         가장 높은 숫자에서 dfs 시작, 더 낮은 숫자일 경우 depth 추가,
 *         더 큰 숫자이고 산을 깎은적 없으면 현재 좌표보다 하나 깎는다.
 *         visited = true
 *         산을 깎았는데 다 깎았으면 그냥 종료.
 *         깎은거 복구, visited 해제
 */
public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int k;
    static int[][] board;
    static int result;
    static int[] xDir = { 1, -1, 0, 0 };
    static int[] yDir = { 0, 0, -1, 1 };
    static ArrayList<int[]> top;
    static int changedValue;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            inputValue();
            for (int[] tmp : top) {
                dfs(tmp[0], tmp[1], 1, false, new boolean[n][n]);
            }
            System.out.printf("#%d %d\n", tc, result);
        }
    }

    private static void dfs(int x, int y, int depth, boolean isChanged, boolean[][] visited) {
        result = Math.max(result, depth);

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + xDir[dir];
            int ny = y + yDir[dir];

            // 다음 등산로 높이가 더 낮은경우
            // 그냥 기

            // 다음 등산로 높이가 같거나 더 높은경우
            // 산을 깎을수 있다.
            // 이전에 산을 깎은경우
            // 이전에 산을 깎지 않은경우
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                if (board[x][y] <= board[nx][ny]) {
                    if (!isChanged) {
                        if (board[nx][ny] - k < board[x][y]) {
                            changedValue = board[nx][ny];
                            board[nx][ny] = board[x][y] - 1;
                            visited[x][y] = true;
                            dfs(nx, ny, depth + 1, true, visited);
                            visited[x][y] = false;
                            board[nx][ny] = changedValue;
                        }
                    }
                } else {
                    visited[x][y] = true;
                    dfs(nx, ny, depth + 1, isChanged, visited);
                    visited[x][y] = false;
                }
            }
        }

    }

    private static void inputValue() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        result = 0;
        board = new int[n][n];

        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                maxValue = Math.max(board[i][j], maxValue);
            }
        }
        top = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == maxValue) {
                    int[] tmp = { i, j };
                    top.add(tmp);
                }
            }
        }

    }
}