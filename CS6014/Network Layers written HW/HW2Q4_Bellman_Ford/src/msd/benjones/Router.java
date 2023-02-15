package msd.benjones;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Router {

    private HashMap<Router, Integer> distances;
    private String name;
    public Router(String name) {
        this.distances = new HashMap<>();
        this.name = name;
    }

    public void onInit() throws InterruptedException {
        //As soon as the network is online,
        //fill in your initial distance table and broadcast it to your neighbors

       for(Neighbor neighbor : Network.getNeighbors(this)){
           distances.put(neighbor.router, neighbor.cost);
        }
        for(Router r: distances.keySet()){
            Network.sendDistanceMessage(new Message(this, r, distances));
        }
    }

    public void onDistanceMessage(Message message) throws InterruptedException {
        //update your distance table and broadcast it to your neighbors if it changed
        if(message.distances.get(this) != distances.get(message.sender)){
            distances.put(message.sender, message.distances.get(this));
            for(Router r: distances.keySet()){
                Message outMessage = new Message(this, r, distances);
                Network.sendDistanceMessage(outMessage);
            }
        }
    }


    public void dumpDistanceTable() {
        System.out.println("router: " + this);
        for(Router r : distances.keySet()){
            System.out.println("\t" + r + "\t" + distances.get(r));
        }
    }

    @Override
    public String toString(){
        return "Router: " + name;
    }
}
