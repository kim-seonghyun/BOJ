import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1. 문제 설명:
 * 오름차순 조합
 */
public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int M;
    static int board[];
    static int tmp[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        inputValue();
        Arrays.sort(board);
        dfs(0, 0);
        System.out.println(sb);
    }

    private static void dfs(int depth, int currentValue) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(tmp[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            tmp[depth] = board[i];
            dfs(depth + 1, i);
        }
    }

    private static void inputValue() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N];
        tmp = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }
    }

}