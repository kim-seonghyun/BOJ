import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br;
    static int n;
    static int m;
    static int[] board;
    static int[] arr;


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            board[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(board);
        arr = new int[m];
        dfs(0, new boolean[n + 1]);
        System.out.println(sb);

    }

    private static void dfs(int depth, boolean[] visited) {
        if(depth == m){
            for(int i=0; i<m; i++){
                sb.append(board[arr[i]]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i= 0; i< board.length; i++){
            if(!visited[i]){
                visited[i] = true;
                arr[depth] = i;
                dfs(depth+1, visited);
                visited[i] = false;
            }
        }
    }
}
