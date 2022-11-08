import java.util.ArrayList;


public class Main {

    static int answer;

    public static void badSum() throws InterruptedException {
        answer = 0;
        int maxValue = 40000;
        int numThreads = 5;
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            final int finalI = i;
            Thread t = new Thread( () -> {
                int count = finalI * maxValue/ numThreads;
                while( count < Math.min((finalI+1) * maxValue/numThreads, maxValue)){
                    answer += count;
                    count++;
                }
            } );
            threads.add( t );
        }
        for(int i = 0; i < numThreads; i++){
            threads.get(i).start();
        }
        for(int i = 0; i < numThreads; i++){
            threads.get(i).join();
        }
        System.out.println("computed answer: " + answer + " correct answer: " + maxValue * (maxValue - 1) / 2);
    }
    public static void sayHello() throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        myRunnable runnable = new myRunnable();
        for (int i = 0; i < 10; i++) {
            Thread newThread = new Thread(runnable);
            threads.add( newThread );
        }
        for(int i = 0; i < threads.size(); i++){
            threads.get(i).start();
        }
        for(int i = 0; i < threads.size(); i++){
            threads.get(i).join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello");
        badSum();
    }
}