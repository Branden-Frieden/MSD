package msd.benjones;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int sum = 0;
        for(int i = 0; i < 5; i++) {
            //Network.makeSimpleNetwork(); //use this for testing/debugging
            Network.makeProbablisticNetwork(900); //use this for the plotting part
            Network.dump();

            Network.startup();
            Network.runBellmanFord();

            //System.out.println("done building tables!");
            for (Router r : Network.getRouters()) {
                r.dumpDistanceTable();
            }
            //System.out.println("total messages: " + Network.getMessageCount());
            sum += Network.getMessageCount();
        }
        System.out.println(sum/5);
    }
}