package msd.benjones;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        for(int i = 10; i < 1000; i+= 5) {
            int sum = 0;
            for (int j = 0; j < 20; j++) {
                //Network.makeSimpleNetwork(); //use this for testing/debugging
                Network.makeProbablisticNetwork(i); //use this for the plotting part
                Network.dump();

                Network.startup();
                Network.runBellmanFord();

                //System.out.println("done building tables!");
                for (Router r : Network.getRouters()) {
                    r.dumpDistanceTable();
                }
                sum = sum + Network.getMessageCount();
            }
            int avg = sum/5;
            System.out.println(i + " " + avg);
        }
    }
}