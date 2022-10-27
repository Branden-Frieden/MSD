import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;

public class HTTPResponse {

    public HTTPResponse(String filename, Socket socketToClient, HashMap<String,String> properties) throws IOException, InterruptedException, NoSuchAlgorithmException {

        OutputStream outputStream = socketToClient.getOutputStream();

        PrintWriter pw = new PrintWriter(outputStream);
        if(!properties.get( "Connection" ).contains("Upgrade")) {
            try {
                // open the requested file ( filename)
                File file = new File(filename);
                String result;
                if (file.isFile()) {
                    result = "200 OK";
                } else {
                    result = "404 not found";
                }

                // send response header
                pw.println("HTTP/1.1 " + result);

                // send back content-type
                // determine file extension (html or css) and use it here
                pw.println("Content-Type: text/" + filename.substring(filename.lastIndexOf(".") + 1) + "; charset=UTF-8");
                pw.println("Content-Length: " + file.length());
                pw.println(); // ends the response header with blank line

                FileInputStream fileInput = new FileInputStream(file);
                Scanner fileReader = new Scanner(fileInput);

                while (fileReader.hasNextLine()) {
                    pw.println(fileReader.nextLine());
                }

                // send the data from the file
                pw.close();
                socketToClient.close();
            } catch (IOException e) {
                String result = "404 not found";
                System.out.println(e.getMessage());
                pw.println("HTTP/1.1 " + result);
                pw.println(e.getMessage());
                pw.println();
                pw.close();
                socketToClient.close();
            }
        } else{
            pw.println("HTTP/1.1 101 Switching Protocols");
            pw.println("Upgrade: websocket");
            pw.println("Connection: keep-alive, Upgrade");
            String accept = properties.get("Sec-WebSocket-Key") + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
            byte[] asBytes = accept.getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update( asBytes );
            byte[] sha1 = md.digest();
            byte[] b64 = Base64.getEncoder().encode( sha1 );
            String webSocketAccept = new String( b64 );

            pw.println("Sec-WebSocket-Accept: " + webSocketAccept);
            pw.println("");
            pw.flush();

            DataInputStream webSocket = new DataInputStream( new BufferedInputStream(socketToClient.getInputStream()) );
            boolean done = false;


            while(!done){
                OutputStream out = socketToClient.getOutputStream();
                boolean fin = false;
                boolean masked = false;
                int payloadLength = 0;
                int returnMessageLength = 2;

                byte b0 = webSocket.readByte();
                if( ( b0 & 0x80 ) != 0 ){
                    fin = true;
                }
                byte opCode = (byte) (b0 & 0x0F);

                byte b1 = webSocket.readByte();
                if( ( b1 & 0x80 ) != 0){
                    masked = true;
                }

                byte guessLength = (byte) (b1 & 0x7F);

                if( guessLength == 0x7E ){
                    short extendedPayloadLength = webSocket.readShort();
                    payloadLength = extendedPayloadLength;
                    returnMessageLength += 2 + extendedPayloadLength;
                }else if( guessLength == 0x7F ){
                    long extendedPayloadLength = webSocket.readLong();
                    payloadLength = (int) extendedPayloadLength;
                    returnMessageLength += 8 + extendedPayloadLength;
                }else{
                    payloadLength = guessLength;
                }

                byte[] returnMessage = new byte[returnMessageLength];

                if(masked){


                    byte[] MASK = new byte[ 4 ];
                    for(int i = 0; i < 4; i++) {
                        MASK[i] = webSocket.readByte();
                    }
                    byte [] ENCODED = new byte[payloadLength];
                    for(int i = 0; i < payloadLength; i++){
                        ENCODED[i] = webSocket.readByte();
                    }
                    byte[] DECODED = new byte[ENCODED.length ];

                    for( int i = 0; i < ENCODED.length; i++){
                        DECODED[ i ] = (byte) (ENCODED[i] ^ MASK[i % 4]);
                    }

                    System.out.write(DECODED, 0, DECODED.length);
                    System.out.println("");
                    out.write(DECODED, 0, DECODED.length);
                   //out.flush();


                }else {
                    for(int i = 0; i < payloadLength; i++){
                        byte[] DECODED = new byte[payloadLength];
                        DECODED[i] =  webSocket.readByte();
                    }
                }

            }

        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket servSocket = new ServerSocket(8080);
        boolean done = false;

        while (!done) {
            Socket socketToClient = servSocket.accept();
            Thread newThread = new Thread(new myRunnable( socketToClient ));
            newThread.start();
        }
    }
}