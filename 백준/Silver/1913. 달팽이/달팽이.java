
import java.io.BufferedReader;
import java.io.InputStreamReader;



public class Main {
    static BufferedReader br;

    static int n;
    static int target;

    static int[][] board;

    static int[] dx = {1,0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int currentDirection = 0;
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        target = Integer.parseInt(br.readLine());

        board = new int[n][n];
        
        int cx = -1;
        int cy = 0;

        int number = n*n;

        while(true){
            if(number == 0){
                break;
            }
            int nx = cx + dx[currentDirection];
            int ny = cy + dy[currentDirection];

            if(nx >=0 && nx < n && ny >=0 && ny < n && board[nx][ny] == 0){
                cx = nx;
                cy = ny;
                board[cx][cy] = number;
                number--;
            }else{
                currentDirection = (currentDirection + 1) % 4;
            }
        }

        int tx = -1;
        int ty= -1;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == target){
                    tx = i + 1;
                    ty = j + 1;
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(tx + " " + ty);
    }
}