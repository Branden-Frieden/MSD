import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        ServerSocket ss = new ServerSocket( 8080 );

        System.out.println("waiting for client to connect");
        Socket client = ss.accept();

        InputStream input = client.getInputStream();

        Scanner scanner = new Scanner(input);

        String line = scanner.nextLine();
        System.out.println("just got: " + line);

        System.out.println("Server runnning in directory: " + System.getProperty( "user.dir"));
        System.exit(1);

        HashMap<String, String> headers_ = new HashMap<>();


        line = scanner.nextLine();
        while( !line.equals("")){
            System.out.println("got line: " + line);
            // "key: value
            String [] pieces = line.split(": ");

            headers_.put(pieces[0],pieces[1]);
            line = scanner.nextLine();
        }

        System.out.println("done parsing headers");

        if( headers_.containsKey("Sec-WebSocket-Key")){
            handshake(client, headers_.get("Sec-WebSocket-Key"));
        }
        else{
            System.out.println("bad connection");
            System.exit(1);
        }

        boolean x = false;
        while( !x ){}
        System.exit(1);

        //////////////////////////
        // start the game ...


        sendMessage( "Welcome to Adventure 2022", client);

        boolean done = false;
        while( !done ){
            String msg = currentRoom.print();
            sendMessage( msg,client);

            String command = handleCommand( client );

        }
    }

    private static String handleCommand(Socket client) throws IOException {

        DataInputStream input = new DataInputStream( client.getInputStream());

        byte[] data = input.readNBytes(2);

        //parse handle first byte...
        //fin and opcode

        //parse handle second byte...
        //mask length

        boolean isMasked = ((data[1] & 0x80) != 0);
        int       length = data[1] & 0x7F;

        byte[] mask = new byte[4];
        if( isMasked ){
            //read the mask
            mask = input.readNBytes(4);
        }

        byte[] payload = input.readNBytes( length );
        byte[] decoded = payload;
        // decode (unmask it)
        for(int i =0; i < payload.length; i++){
            decoded[i] = (byte) (payload[i] ^ mask[ i % 4]);
        }

        String command = new String( decoded );
        System.out.println( "recieved: '" + command + "'");

        return command;
    }

    private static void sendMessage(String ascii_msg, Socket client) throws IOException {

        DataOutputStream output = new DataOutputStream( client.getOutputStream());

        // send the ascii msg as a websocket msg
        //1st byte is: fin res res res opcode
        // 1000 0001
        output.writeByte( 0x81 );
        //2nd byte is: mask bit length of msg
        //0 _ _ _ _ _ _ _
        long length = ascii_msg.length();

        if( length > 65000 ){
            // 2nd byte, assume msg length < 65k
            output.writeByte( 127 );
            output.writeLong(length);

        }
        else if( length > 125 ){
            // 2nd byte, assume msg length < 65k
            output.writeByte( 126 );
            output.writeShort((int) length);

        }
        else {
            output.writeByte((int) length);
        }
        output.writeBytes( ascii_msg );

    }

    private static void handshake(Socket client, String key) throws IOException, NoSuchAlgorithmException {
        OutputStream output = client.getOutputStream();
        PrintWriter writer = new PrintWriter(output);

        key += "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";

        MessageDigest md = MessageDigest.getInstance( "SHA-1" );
        byte[] data = key.getBytes();
        String result = Base64.getEncoder().encodeToString(md.digest( data ));

        writer.print( "HTTP/1.1 101 Switching Protocols\r\n");
        writer.print( "Upgrade: websocket\r\n" );
        writer.print( "Connection: Upgrade\r\n");
        writer.print("Sec-WebSocket-Accept: " + result + "\r\n");
        writer.print("\r\n");
        writer.flush();

        System.out.println("done sending handshake");

    }
}