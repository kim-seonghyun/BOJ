import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author 김성현
 */
public class Solution {
    static int N;
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] board;
    static int maxValue = Integer.MIN_VALUE;
    static String direction;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            System.out.printf("#%d\n", tc);
            inputValue();

            switch (direction){
                case "up":
                    up();
                    break;
                case "down":
                    down();
                    break;
                case "left":
                    left();
                    break;
                case "right":
                    right();
                    break;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }


    }

    private static void inputValue() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        direction = st.nextToken();
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void dfs(int depth) {
        if (depth == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    maxValue = Math.max(board[i][j], maxValue);
                }
            }
            return;
        }
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(board[i], 0, tmp[i], 0, N);
        }
        for (int dir = 0; dir < 4; dir++) {

            switch (dir) {
                case 0:
                    up();
                    break;
                case 1:
                    down();
                    break;
                case 2:
                    left();
                    break;
                case 3:
                    right();
                    break;
            }
            dfs(depth + 1);
        }
        for (int i = 0; i < N; i++) {
            System.arraycopy(tmp[i], 0, board[i], 0, N);
        }

    }

    public static void up() {
        for (int j = 0; j < N; j++) {
            Stack<Integer> stack = new Stack<>();
            for (int i = N - 1; i >= 0; i--) {
                if (board[i][j] > 0) {
                    stack.add(board[i][j]);
                    board[i][j] = 0;
                }
            }
            int index = 0;
            while (!stack.empty()) {
                int val1 = stack.pop();
                if (!stack.empty()) {
                    int val2 = stack.pop();
                    if (val1 == val2) {
                        board[index++][j] = val1 * 2;
                    } else {
                        board[index++][j] = val1;
                        stack.add(val2);
                    }
                } else {
                    board[index++][j] = val1;
                }
            }
        }
    }

    public static void down() {
        for (int j = 0; j < N; j++) {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < N; i++) {
                if (board[i][j] > 0) {
                    stack.add(board[i][j]);
                    board[i][j] = 0;
                }
            }
            int index = N - 1;
            while (!stack.empty()) {
                int val1 = stack.pop();
                if (!stack.empty()) {
                    int val2 = stack.pop();
                    if (val1 == val2) {
                        board[index--][j] = val1 * 2;
                    } else {
                        board[index--][j] = val1;
                        stack.add(val2);
                    }
                } else {
                    board[index--][j] = val1;
                }
            }
        }
    }

    public static void left() {
        for (int i = 0; i < N; i++) {
            Stack<Integer> stack = new Stack<>();
            for (int j = N - 1; j >= 0; j--) {
                if (board[i][j] > 0) {
                    stack.add(board[i][j]);
                }
                board[i][j] = 0;
            }
            int index = 0;

            while (!stack.empty()) {
                int val1 = stack.pop();
                if (!stack.empty()) {
                    int val2 = stack.pop();
                    if (val1 == val2) {
                        board[i][index++] = val1 * 2;
                    } else {
                        board[i][index++] = val1;
                        stack.add(val2);
                    }
                } else {
                    board[i][index++] = val1;
                }
            }


        }
    }

    public static void right() {
        for (int i = 0; i < N; i++) {
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < N; j++) {
                if (board[i][j] > 0) {
                    stack.add(board[i][j]);
                }
                board[i][j] = 0;
            }
            int index = N - 1;

            while (!stack.empty()) {
                int val1 = stack.pop();
                if (!stack.empty()) {
                    int val2 = stack.pop();
                    if (val1 == val2) {
                        board[i][index--] = val1 * 2;
                    } else {
                        board[i][index--] = val1;
                        stack.add(val2);
                    }
                } else {
                    board[i][index--] = val1;
                }
            }

        }
    }
}
