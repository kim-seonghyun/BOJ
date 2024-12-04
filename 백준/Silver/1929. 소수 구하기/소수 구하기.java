import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int M;

    static boolean[] board;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new boolean[M + 1];
        for(int i= 2; i<=M; i++){
            board[i] = true;
        }


        for(int i=2; i<=M; i++){
            if(!board[i]) continue;
            for(int j=2; i*j<=M; j++){
                board[i * j] = false;
            }
        }

        for(int i=N; i<=M; i++){
            if(board[i]){
                sb.append(i).append("\n");
            }
        }
    System.out.print(sb.toString());
  }
}