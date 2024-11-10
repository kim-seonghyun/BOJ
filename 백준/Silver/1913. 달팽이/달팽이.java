import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    static BufferedReader br;
    static int N;
    static int tagetNumber;

    static int[] xDir = {1, 0 ,-1, 0};
    static int[] yDir = {0, 1, 0 ,-1};
    
    static int currentDir = 0;

    static int[][] board;
    public static void main(String[] args) throws NumberFormatException, IOException {
        inputValue();
        
        int startNumber = N*N;
        
        int cX = -1;
        int cY = 0;
        for(int i=startNumber; i>=1; i--){
            int nx= cX + xDir[currentDir];
            int ny = cY  + yDir[currentDir];

            if(nx < 0 || nx >= N || ny < 0 || ny >=N || board[nx][ny] > 0){
                break;
            }
            cX = nx;
            cY = ny;
            board[cX][cY] = i;
            

            nx= cX + xDir[currentDir];
            ny = cY  + yDir[currentDir];

            if(nx < 0 || nx >= N || ny < 0 || ny >=N || board[nx][ny] > 0){
               currentDir =  (currentDir + 1) %4;
            }
        }
        int targetX = -1;
        int targetY = -1;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(board[i][j] == tagetNumber){
                    targetX = i + 1;
                    targetY = j + 1;
                }
                System.out.print(board[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println(targetX + " " + targetY);
    }

    private static void inputValue() throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tagetNumber = Integer.parseInt(br.readLine());

        board = new int[N][N];
        
    }
}