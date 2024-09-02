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
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int m;
    static int[][] board;
    static int[] xDir = {1,-1,0,0};
    static int[] yDir = {0,0,1,-1};
    static int[][] time ;
    static int count = 0;


    public static void main(String[] args) throws IOException {
        inputValue();
        bfs();
        System.out.println(time[n-1][m-1]);
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
         int[] tmp = {0,0};
        queue.add(tmp);

        while(!queue.isEmpty()){

            int[] p = queue.poll();
            int x = p[0];
            int y = p[1];
            if(visited[x][y]){
                continue;
            }


            count++;

            visited[x][y] = true;

            for(int dir =0; dir<4; dir++){
                int nx = x + xDir[dir];
                int ny = y + yDir[dir];

                if(nx >=0 && nx < n && ny >=0 && ny < m && board[nx][ny] == 1 && !visited[nx][ny]){
                    int[] tmp2 = {nx,ny};
                    if(time[nx][ny] == 0){
                        time[nx][ny] = time[x][y] + 1;
                    }else{
                        time[nx][ny] = Math.min(time[x][y] + 1, time[nx][ny]);
                    }
                    queue.add(tmp2);
                }
            }

        }

    }

    private static void inputValue() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        time = new int[n][m];
        time[0][0] =1 ;
        for(int i=0; i<n; i++){
            String tmp = br.readLine();
            for(int j=0; j<m; j++){
                board[i][j] = tmp.charAt(j) - '0';
            }
        }
    }
}
