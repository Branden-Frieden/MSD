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

        //TODO: IMPLEMENT ME
        //As soon as the network is online,
        //fill in your initial distance table and broadcast it to your neighbors

        for(Neighbor neighbor : Network.getNeighbors(this)){
            neighbor.cost = (int) Double.POSITIVE_INFINITY;
        }
        for(Neighbor neighbor : Network.getNeighbors(this)){
            Message message = new Message(this, neighbor.router, distances);
            Network.sendDistanceMessage(message);
        }

    }

    public void onDistanceMessage(Message message) throws InterruptedException {
        //update your distance table and broadcast it to your neighbors if it changed
        if(message.distances.get(message.sender) != distances.get(message.sender)){
            distances.put(message.sender, message.distances.get(message.sender));
            for(Neighbor neighbor : Network.getNeighbors(this)){
                Message outMessage = new Message(this, neighbor.router, distances);
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
