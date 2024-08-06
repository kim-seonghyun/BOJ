import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class Car{
    int weight;
    int endTime;

    public Car(int weight, int endTime) {
        this.weight = weight;
        this.endTime = endTime;
    }
}

class Bridge{
    public Bridge(int W, int L) {
        this.W = W;
        this.L = L;
    }
    int L;
    int W;
    int currentWeight =0;

    static Queue<Car> cars = new ArrayDeque<>();
    public boolean isAvailable(int weight) {
        return cars.size() < this.W && (this.currentWeight + weight) <= L;
    }

    public void addCar(int weight, int currentTime) {
        if(isAvailable(weight)) {
            this.currentWeight += weight;
            cars.add(new Car(weight, currentTime + this.W));
        }
    }

    public void popCar() {
        this.currentWeight -= this.cars.poll().weight;

    }

    public boolean popable(int currentTime) {
//		if(this.cars.peek().endTime == currentTime) {
//			return true;
//		}
//
        if(this.cars.size() == 0 || this.cars.peek().endTime > currentTime) {
            return false;
        }
        return true;
    }
}

public class Main {

    static int N;
    static int W;
    static int L;
    static int[] cars;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        st =new StringTokenizer(br.readLine());
        cars = new int[N];
        for(int i=0; i<N; i++) {
            cars[i] = Integer.parseInt(st.nextToken());
        }
        Bridge bridge = new Bridge(W, L);

        int endCount = 0;
        int index = 0;
        int time = 0;
        while(endCount < N) {
            time++;
            if(bridge.popable(time)) {
                endCount++;
                bridge.popCar();
            }


            if(index < N && bridge.isAvailable(cars[index]) ) {
                bridge.addCar(cars[index], time);
                index += 1;
            }



        }
        System.out.println(time);
    }


}
