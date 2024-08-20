import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 1.문제 설명
 * 
 * 정수 배열의 부분집합에서, 합이 B 보다 큰 값 중 가장 작은 값
 * 
 * 2. input
 * 
 * T
 * N(점원 수) B(기준 높이)
 * 점원들의 키 ( N개 )
 * 
 * 3. output
 * 
 * 만들 수 있는 높이가 B 이상인 탑 중에서 탑의 높이와 B의 차이가 가장 작은 것을 출력한다.
 * 
 * 4. 해결 방법
 * 
 * 1. 기존 점원으로 만들수 있는 높이 + 현재 점원의 키
 * 
 * 위 과정을 모든 점원의 키마다 반복하면 만들수 있는 높이들을 구할 수 있다.
 */
public class Solution {

    static int T;
    static int N;
    static int B;

    static int[] heights;
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int testCaseNumber = 1; testCaseNumber <= T; testCaseNumber++) {
            inputValue();
            System.out.printf("#%d %d\n", testCaseNumber, getMaxClerkHeight() - B);
        }

    }

    private static int getMaxClerkHeight() {
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < heights.length; i++) {
            int current = heights[i];

            Integer[] tmp = set.toArray(new Integer[0]);
            for (int t : tmp) {
                set.add(t + current);
            }
            set.add(current);
        }

        return set.stream().filter(a -> a >= B).sorted().findFirst().get();

    }

    private static void inputValue() throws Exception {
        // TODO Auto-generated method stub
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        heights = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
    }

}
