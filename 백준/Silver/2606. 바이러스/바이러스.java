import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int lines;
    static int[][] board;
    static boolean[] visited;

    public static void dfs(int start){
        if(visited[start]){
            return;
        }
        visited[start] = true;

        for(int i=1; i<=n; i++){
            if(start != i && board[start][i] == 1 && !visited[i]){
                dfs(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in ));
        n = Integer.parseInt(br.readLine());

        lines = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        board = new int[n+ 1][n + 1];
        for(int i =0 ;i <lines; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a][b] = 1;
            board[b][a] =1;
        }

        dfs(1);
        int count = 0;
        for(int i=2; i<=n; i++){
            if(visited[i]){
                count++;
            }
        }
        System.out.println(count);
    }
}