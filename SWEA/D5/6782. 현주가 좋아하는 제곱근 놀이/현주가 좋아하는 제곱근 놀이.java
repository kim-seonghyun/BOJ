import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. 문제 설명
 * 2 이상의 정수 N을 최소한의 N+1, √n 연산을 사용하여 2로 만들기.
 * 
 * 2. input
 * T
 * N (T개)
 * 
 * 3. output
 * N을 2로 만드는 최소한의 연산 횟수
 * 
 * 4. 해결방법
 * a. √n 연산 결과의 정수 부분 d 구하기 (1회)
 * b. (d + 1^2) - n 연산으로 N+1 연산 횟수 구하기 (n - d^2회)
 * c. a, b를 N이 2가 될때까지 반복
 * c-1. N ==4이면 연산횟수 1증가하고 return
 */
public class Solution {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            long N = Long.parseLong(br.readLine());
            long result = getOperationCount(N);
            System.out.printf("#%d %d\n", tc, result);
        }
    }

    private static long getOperationCount(long N) {
        long operationCount = 0;
        while (N > 2) {
            if (Math.sqrt(N) == Math.floor(Math.sqrt(N))) {
                operationCount++;
                N = (long) Math.sqrt(N);
                continue;
            }

            long sqrtedN = (long) Math.sqrt(N) + 1;
            long plusCount = (long) Math.pow(sqrtedN, 2) - N;

            operationCount += 1;
            operationCount += plusCount;
            N = sqrtedN;
        }
        return operationCount;
    }
}
