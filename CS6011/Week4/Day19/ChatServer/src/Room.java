import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Room {

    private Room( String roomName ){
        roomName_ = roomName;

    }

    public synchronized static Room getRoom( String name ){
        Room room = null;

        for(int i = 0; i < rooms_.size(); i++){
            if(rooms_.get(i).roomName_.equals( name )){
                room = rooms_.get( i );
                return room;
            }
        }
        room = new Room( name );
        rooms_.add(room);
        return room;
    }

    public synchronized void addClient(String name, Socket clientSocket) throws IOException {
        clients_.add( clientSocket );

        clientNames_.add( name );
        for(int i = 0; i < storedMessages_.size(); i++){
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            out.write(storedMessages_.get(i), 0, storedMessages_.get(i).length);
            out.flush();
        }
    }
    public synchronized void removeClient(String name, Socket clientSocket){
        clientNames_.remove(name);
        clients_.remove(clientSocket);
    }
    public synchronized void sendMessages( byte[] output ) throws IOException {
        storedMessages_.add( output );
        for(int i = 0; i< clients_.size(); i++){
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(clients_.get( i ).getOutputStream()));

            out.write(output, 0, output.length);
            out.flush();
        }
    }

    static ArrayList<Room> rooms_ = new ArrayList<>();
    ArrayList<byte[]> storedMessages_ = new ArrayList<>();

    ArrayList<String> clientNames_ = new ArrayList<>();
    ArrayList<Socket> clients_ = new ArrayList<>();
    String roomName_;
}
