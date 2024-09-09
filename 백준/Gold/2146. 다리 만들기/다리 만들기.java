import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김성현
 */
public class Main {
    static int N;
    static int M;
    static BufferedReader br;
    static StringTokenizer st;

    static int[][] board;
    static boolean[][] visited;
    static int result = Integer.MAX_VALUE;
    static int[] xDir = { 0, 0, 1, -1 };
    static int[] yDir = { 1, -1, 0, 0 };

    public static void main(String[] args) throws NumberFormatException, IOException {
        inputValue();
        numbering();

        makeBridge();

        System.out.println(result);

    }

    private static void makeBridge() {

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] > 0) {
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = i + xDir[dir];
                        int ny = j + yDir[dir];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N || board[nx][ny] > 0) {
                            continue;
                        }

                        bfs(nx, ny, board[i][j]);
                    }
                }

            }
        }
    }

    private static void bfs(int sx, int sy, int start) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] tmpVisited = new boolean[N][N];
        int[] startP = { sx, sy };
        queue.add(startP);
        int length = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            length++;
            for (int s = 0; s < size; s++) {
                int[] p = queue.poll();
                int x = p[0];
                int y = p[1];
                if (tmpVisited[x][y]) {
                    continue;
                }
                tmpVisited[x][y] = true;

                for (int dir = 0; dir < 4; dir++) {
                    int nx = x + xDir[dir];
                    int ny = y + yDir[dir];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N || tmpVisited[nx][ny] || board[nx][ny] == start) {
                        continue;
                    }

                    if (board[nx][ny] != start && board[nx][ny] > 0) {
                        if (length != 0) {
                            result = Math.min(length, result);
                        }
                        return;
                    }

                    if (board[nx][ny] == 0) {
                        int[] tmp = { nx, ny };
                        queue.add(tmp);
                    }
                }
            }

        }
    }

    private static void numbering() {
        visited = new boolean[N][N];
        int count = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || board[i][j] == 0) {
                    continue;
                }
                dfs(i, j, count++);
            }
        }
    }

    private static void dfs(int x, int y, int number) {
        visited[x][y] = true;
        board[x][y] = number;
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + xDir[dir];
            int ny = y + yDir[dir];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || board[nx][ny] == 0) {
                continue;
            }
            dfs(nx, ny, number);
        }

    }

    private static void inputValue() throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }
}
