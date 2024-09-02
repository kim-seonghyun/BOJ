import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;

    static int n;
    static int m; // 선택할수 있는 벌통 개수
    static int c; // 채취 최대 양
    static int[][] board;
    static int maxProfit;
    static int result;
    static int[] tmp;

    static int[] arr;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++){
            inputValue();
            getPosition();
            System.out.printf("#%d %d\n",tc, result );
        }

    }

    private static void getPosition(){
        arr = new int[2];
        result = Integer.MIN_VALUE;
        dfs2(0, 0);
    }

    private static void dfs2(int depth, int current){
        if(depth == 2){
            // 둘다 안겹치고, 범위 안에 들었을때
            int firstStart = arr[0];
            int fx= firstStart / n;
            int fy = firstStart % n;

            int secondStart = arr[1];
            int sx = secondStart / n;
            int sy = secondStart % n;

            if(fx == sx && fy + m -1 >= sy){
                return;
            }

            if(sy + m -1 < n && fy + m -1 < n ){

                int fProfit = getProfit(fx, fy);
                int sProfit = getProfit(sx, sy);
                result = Math.max(fProfit + sProfit, result);
            }
            return;
        }

        for(int i=current; i<n*n; i++){
            arr[depth] = i;
            dfs2(depth +1, i+ 1);
        }
    }


    private static void dfs(int depth, boolean[] visited) {
        if (depth == m) {
            int s = 0;
            int honey = 0;
            for (int i = 0; i < m; i++) {
                if (visited[i]) {
                    honey += tmp[i];
                    s += (int) Math.pow(tmp[i], 2);
                }
            }
            if (honey <= c) {
                maxProfit = Math.max(s, maxProfit);
            }
        } else {
            visited[depth] = true;
            dfs(depth + 1, visited);
            visited[depth] = false;
            dfs(depth + 1, visited);
        }
    }


    private static int getProfit(int i, int j) {
        tmp = Arrays.copyOfRange(board[i], j, j + m);

        maxProfit = Integer.MIN_VALUE;
        dfs(0, new boolean[m]);

        return maxProfit;
    }

    private static void inputValue() throws IOException {


        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }


    }
}
/*
1
4 2 13
6 1 9 7
9 8 5 8
3 4 5 3
8 2 6 7
 */