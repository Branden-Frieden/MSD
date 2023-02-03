import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.*;
import java.net.InetAddress;

public class DNSServer {
    public static void main(String[] args) throws IOException {
        DNSCache cache = new DNSCache();
        DatagramSocket socket = new DatagramSocket(8053);
        byte[] data = new byte[512];
        byte[] returnData = new byte[512];

        boolean done = false;
        while( !done ){

            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            data = packet.getData();

            DNSMessage clientMessage = DNSMessage.decodeMessage(data);
            DNSMessage response;
            if(cache.query(clientMessage.questions_[0])){
                response = DNSMessage.buildResponse(clientMessage, new DNSRecord[]{cache.getValue(clientMessage.questions_[0])});
            }
            else {
                DatagramPacket packetToGoogle = new DatagramPacket(data, data.length, InetAddress.getByName("8.8.8.8"), 53);
                socket.send(packetToGoogle);


                DatagramPacket returnPacket = new DatagramPacket(returnData, returnData.length);
                socket.receive(returnPacket);
                returnData = returnPacket.getData();

                response = DNSMessage.decodeMessage(returnData);
                if(response.anRecords_.length >= 1){
                    cache.store(clientMessage.questions_[0], response.anRecords_[0]);
                }

                DatagramPacket sendToClientPacket = new DatagramPacket(returnData, returnData.length, packet.getAddress(), packet.getPort());
                socket.send( sendToClientPacket );
                continue;
            }

            byte[] DataToSendToClient = response.toBytes();

            DatagramPacket sendToClientPacket = new DatagramPacket(DataToSendToClient, DataToSendToClient.length, packet.getAddress(), packet.getPort());
            socket.send( sendToClientPacket );
        }
    }
}
