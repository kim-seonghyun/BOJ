import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static ArrayList<int []> board;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        br= new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=t; tc++){
            inputValue();
            int result = 0;

            for(int i=0; i<n; i++){
                for(int j=0; j< n; j++){
                    for(int k=1; k<=n +1; k++){
                        int home = 0;
                        int cost = k * k + (k-1) * (k-1);
                        for(int[] b :board){
                            int x2 = b[0];
                            int y2 = b[1];

                            int dist = getDistance(i,j, x2,y2);
                            if(dist < k){
                                home++;
                            }
                        }
                        if(home * m - cost >= 0){
                            result = Math.max(result, home );
                        }
                    }
                }
            }
            System.out.printf("#%d %d\n", tc, result);

        }
    }

    private static int getDistance(int x1, int y1, int x2, int y2 ){
        return Math.abs(x2- x1) + Math.abs(y2- y1);
    }

    private static void inputValue() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new ArrayList<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                if(Integer.parseInt(st.nextToken()) == 1){
                    int[] tmp = {i, j};
                    board.add(tmp);
                }

            }
        }

    }
}
