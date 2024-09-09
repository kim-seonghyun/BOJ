import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;

    static BufferedReader br;
    static StringTokenizer st;
    static int result = Integer.MAX_VALUE;
    static ArrayList<int[]> position = new ArrayList<>();
    static int[] papers = { 5, 5, 5, 5, 5 };

    public static void main(String[] args) throws Exception {
        inputValue();
        dfs(0, 0);
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static void dfs(int depth, int count) {
        if (depth == position.size()) {
            if (checkFill()) {
                result = Math.min(result, count);
            }
            return;
        }

        if (count >= result) {
            return;
        }
        int x = position.get(depth)[0];
        int y = position.get(depth)[1];
        if (board[x][y] == 0) {
            dfs(depth + 1, count);
        } else {
            for (int i = 4; i >= 0; i--) {
                if (papers[i] == 0 || !canFill(x, y, i)) {
                    continue;
                }
                papers[i]--;
                fill(x, y, i, 0);
                dfs(depth + 1, count + 1);
                fill(x, y, i, 1);
                papers[i]++;
            }
        }

    }

    private static void fill(int x, int y, int range, int number) {
        for (int i = x; i <= x + range; i++) {
            for (int j = y; j <= y + range; j++) {
                board[i][j] = number;
            }
        }
    }

    private static boolean canFill(int x, int y, int range) {
        for (int i = x; i <= x + range; i++) {
            for (int j = y; j <= y + range; j++) {
                if (i < 0 || i >= 10 || j < 0 || j >= 10 || board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkFill() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void inputValue() throws IOException {
        board = new int[10][10];

        br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    int[] tmp = { i, j };
                    position.add(tmp);
                }
            }
        }
    }
}