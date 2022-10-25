public class myRunnable implements Runnable {
    @Override
    public void run() {
        int  count = 0;
        while(count < 100){
            System.out.print( "Hello from this thread: "
            + Thread.currentThread().getId() + ": " + count++ + " ");
            if(count % 10 == 0){
                System.out.printf("\n");
            }
        }
    }
}
