import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        // 달팽이가 낮에 A 미터 올라가고
        // 밤에 B미터 내려온다.

        // 단순히 차이 값만 업데이트 하면 낮에 도착했는데 다음날 도착하는 값으로 출력 할 수도있음.
        // 결국 도착은 낮에 함.

        // 그렇다면 낮의 경우를 막대 길이랑 같거나 클때까지 모두 더하고,
        // 더한 만큼 밤의 이동거리를 빼준다.
        // 달팽이의 이동거리가 막대보다 작은경우

        // 종료 조건 : 특정 점에서 낮동안 달팽이의 이동거리가 막대 길이와 같거나 큰 경우

        // 출발 한채로 시작한다면?

        // 2 -1
        // 4 -2
        // 6 -3
        // 5
        // 8 - 4


        // 2 + (2 -1)x = 5

        // A, B, V

        // A + (A - B)x = V

        // 5 + (5 - 1) x = 6

        // V - A = (A- B)x
        // V - A

        //
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        int V = sc.nextInt();
        if(A >= V){
            System.out.println(1);
            return;
        }



        int result = (int) (Math.ceil((double) (V - A) / (A- B)) + 1);
        System.out.println(result);
    }
}
