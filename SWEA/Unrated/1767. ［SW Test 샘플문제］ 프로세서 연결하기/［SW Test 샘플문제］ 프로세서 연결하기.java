import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author 김성현
 * 1. 문제 설명
 * 전선의 최소 길이 찾기.
 * 전선은 직선으로만 섦치 가능
 * 교차 불가능
 * 전원이 연결되지 않는 Core가 존재할 수 있다.
 * <p>
 * 2. input
 * T
 * N
 * N * N 프로세서 상태( 0 : 빈칸 , 1: 프로세서)
 * <p>
 * 3. output
 * <p>
 * 최소 전선 길이합. (최대한 많은 core 연결, 전선 길이 합이 최소가 되게)
 * <p>
 * 4. 해결 방법
 * 사용 가능한 프로세서들을 dfs로 돌린다.
 * 그릴수있으면 줄 쭉 긋기
 * dfs 돌리기.
 * <p>
 * depth 가 프로세서 만큼 커지고, 현재 연결한 코어 개수가 기존 코어개수 보다 많으면 갱신, 최대 코어 개수이면 최소 간선만 기록
 * <p>
 * 다음 depth의 dfs 돌리기.
 */
public class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int[][] board;
    static ArrayList<int[]> processors;
    static int coreCount;
    static int minLineLength;
    static int[] xDir = {1, -1, 0, 0};
    static int[] yDir = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            inputValue();
            coreCount = 0;
            minLineLength = Integer.MAX_VALUE;
            dfs(0, 0, 0);
            System.out.printf("#%d %d\n",tc, minLineLength);
        }
    }

    private static boolean isAvailable(int x, int y, int dir) {
        int nx = x + xDir[dir];
        int ny = y + yDir[dir];

        while (nx >= 0 && nx < n && ny >= 0 && ny < n) {
            if (board[nx][ny] != 0) {
                return false;
            }
            nx += xDir[dir];
            ny += yDir[dir];
        }
        return true;
    }

    private static void dfs(int depth, int currentConnection, int line) {
        if (depth == processors.size()) {
            if (currentConnection > coreCount) {
                coreCount = currentConnection;
                minLineLength = line;
            } else if (currentConnection == coreCount) {
                minLineLength = Math.min(minLineLength, line);
            }
            return;
        }

        int[] tmp = processors.get(depth);
        int x = tmp[0];
        int y = tmp[1];

        for (int dir = 0; dir < 4; dir++) {
            if (isAvailable(x, y, dir)) {
                int currentLine = drawLine(x, y, dir, 2);
                dfs(depth + 1, currentConnection + 1, line + currentLine);
                drawLine(x, y, dir, 0);
            }
        }
        dfs(depth + 1, currentConnection, line); // depth 만증가시켜서 시작점을 다르게 한다.
    }

    private static int drawLine(int x, int y, int dir, int number) {
        int count = 0;
        int nx = x + xDir[dir];
        int ny = y + yDir[dir];

        while (nx >= 0 && nx < n && ny >= 0 && ny < n) {
            count++;
            board[nx][ny] = number;
            nx += xDir[dir];
            ny += yDir[dir];
        }

        return count;
    }

    private static void inputValue() throws IOException {
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        processors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1 && i > 0 && i < n - 1 && j > 0 && j < n - 1) {
                    int[] tmp = {i, j};
                    processors.add(tmp);
                }
            }
        }

    }
}
