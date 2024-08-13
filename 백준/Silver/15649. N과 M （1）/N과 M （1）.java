/**
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 
 * 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
 * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
 */

import java.util.*;
import java.io.*;

public class Main {

    //수열 저장 하는 배열
    static int arr[];
    //탐색 중 
    static boolean visited[];
    
    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        arr = new int[M];
        visited = new boolean[N];
        dfs(N, M, 0);
    }
    
    public static void dfs(int N, int M, int depth) {
        // 만약 탐색 길이가 M 동일하면 출력
        if(depth == M) {
            for(int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }
        
        for(int i=0; i<N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                arr[depth] = i+1;
                dfs(N, M, depth+1);
                visited[i] = false;
            }
        }
    }

}