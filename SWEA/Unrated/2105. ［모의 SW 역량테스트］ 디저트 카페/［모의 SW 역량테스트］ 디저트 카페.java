import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 디저트 카페
 *
 * 1. 문제 설명
 *
 * 디저트 카페 투어, 대각선으로 , 사각형 모양으로 움직여야함.
 * 같은 종류 디저트 먹는거 싫어함.
 *
 * 2. input
 * T
 * N
 * N * N 디저트 목록.
 *
 * 3. output
 * 디저트를 가장 많이 먹을때 디저트 수, 없으면 -1
 *
 * 4. 해결 방법
 *
 * 모든 행, 열에 대해서. 대각선 방향으로 dfs 돌리기
 * 계속 가거나, 반대 방향으로 꺾거나 둘중 하나.
 * 방향은 4번 바꿈.
 * 다음 행이 기존 디저트 들에 중복이거나, 배열을 벗어날 경우 pass
 *
 *
 */
public class Solution {
    static BufferedReader br;
    static boolean[][] visited;
    static boolean[] dessert;
    static int n;
    static int[][] board;
    static int[] xDirection = { 1, 1, -1, -1 };
    static int[] yDirection = { 1, -1, -1, 1 };

    static int maxValue;

    static StringTokenizer st;

    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
//        br = new BufferedReader(new FileReader("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            maxValue = -1;
            board = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            for(int i = 0; i < n; i++){
                for(int j = 0; j< n; j++){
                    visited = new boolean[n][n];
                    dessert = new boolean[101];
                    dfs(i, j, i, j, 0, 0, 0);
                }
            }
            System.out.printf("#%d %d\n", tc ,maxValue);
        }
    }

    private static void dfs(int startX, int startY, int x, int y, int direction, int changedDir, int distance) {
        if(changedDir > 3){
            return;
        }

        if( dessert[board[x][y]] || direction < changedDir){
            return;
        }


        visited[x][y] = true;

        if(changedDir == 3 || changedDir == 2) {
            // 마지막 방향이면, 시작점 만날때까지 증가하고 max값 갱신
            if (x - 1 == startX && y + 1 == startY) {
                maxValue = Math.max(maxValue, distance + 1);
                return;
            }
        }


        for(int dir = direction; dir < xDirection.length; dir++){
            int nX = x + xDirection[dir];
            int nY = y + yDirection[dir];

            if(checkRange(nX, nY) && !dessert[board[nX][nY]]){
                dessert[board[x][y]] = true;
                dfs(startX, startY, nX, nY, dir, changedDir + ((dir == direction) ? 0: 1), distance + 1);
                dessert[board[x][y]] = false;
            }
        }

        // 방향 바꾼 횟수가 4를 넘어가면 return


        // 4방향 확인하면서 다음 노드가 유효하면 넘김.
    }
}
