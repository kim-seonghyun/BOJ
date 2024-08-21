import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 김성현
 *         1. 문제 설명 : Nkg 의 설탕을 최소의 5,3kg 봉지를 사용하여 담기.
 * 
 *         2. input : N (설탕 무게)
 * 
 *         3. output : 최소 봉지 개수
 * 
 *         4. 해결 방법 : 가능한 봉지 개수 N/5 ~ N / 3 사이에서 찾는다.
 * 
 * 
 * 
 * 
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int result = -1;
        for (int i = n / 3; i >= n / 5; i--) {
            // 나누는 횟수가 i인 경우
            for (int j = 0; j <= i; j++) {
                // 5, 3kg 봉지의 개수를 조절하면서 가능한경우 return
                if (5 * (i - j) + 3 * j == n) {
                    result = i;
                }
            }
        }
        System.out.println(result);
    }
}