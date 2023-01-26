import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class DNSServer {
    InputStream inputStream;

    public static void main(String[] args) throws IOException {

        DatagramSocket socket = new DatagramSocket(6969);
        byte[] data = new byte[600];

        boolean done = false;
        while( !done ){

            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            data = packet.getData();

            DNSMessage message = DNSMessage.decodeMessage(data);



        }


    }


}
