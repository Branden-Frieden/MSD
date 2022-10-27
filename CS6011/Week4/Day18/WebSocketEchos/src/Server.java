import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket servSocket = new ServerSocket(8080);
        boolean done = false;

        while (!done) {
            Socket socketToClient = servSocket.accept();
            Thread newThread = new Thread(new myRunnable( socketToClient ));
            connections_.add( newThread );
            newThread.start();
        }
    }
    static ArrayList<Thread> connections_ = new ArrayList<>();
}
