import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 냅색 알고리즘
 */
class Solution{
    static int K;
    static int N;

    static int[][] arr;
    static int[][] backpack;
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br= new BufferedReader(new InputStreamReader((System.in)));
        st = new StringTokenizer(br.readLine()) ;
        int t = Integer.parseInt(st.nextToken());

        for(int tc =1; tc<=t; tc++){
            inputValue();

            for(int i=0; i<=N; i++){
                arr[i][0] = 0;
            }

            for(int i=0; i<=K; i++){
                arr[0][i] = 0;
            }

            for(int i = 1; i<=N; i++){
                for(int w = 1; w<=K; w++){
                    if(backpack[i][1] > w){
                        arr[i][w] = arr[i-1][w];
                    }else{
                        arr[i][w] = Math.max(backpack[i][0] + arr[i-1][w - backpack[i][1]], arr[i-1][w]);
                    }
                }
            }
            int result = 0;
            for(int i=0; i<=K; i++){
               result = Math.max(result, arr[N][i]);
            }
            System.out.printf("#%d %d\n", tc, result);
        }

    }

    private static void inputValue() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K =Integer.parseInt(st.nextToken());

        arr = new int[N+1 ][K + 1];
        backpack = new int[N + 1][2];

        for(int i=1; i<= N; i++){
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            backpack[i][0] = C;
            backpack[i][1] = V;
        }
    }
}