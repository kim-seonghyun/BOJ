import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static BufferedReader br;
    static StringTokenizer st;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr =new int[m];
        dfs(0, new boolean[n+1]);
    }

    private static void dfs(int depth, boolean[] visited) {
        if(depth == m){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<m; i++){
                sb.append(arr[i]).append(" ");
            }
            System.out.println(sb);
            return;
        }

        for(int i =1; i<=n; i++){
            if(!visited[i]){
                visited[i] = true;
                arr[depth] = i;
                dfs(depth + 1, visited);
                visited[i] = false;
            }
        }
    }
}
