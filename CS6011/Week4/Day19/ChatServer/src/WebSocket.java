import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class WebSocket {

    public WebSocket(Socket socketToClient) throws IOException {


        DataInputStream webSocket = new DataInputStream(new BufferedInputStream(socketToClient.getInputStream()));
        boolean done = false;
        Room room = null;
        byte[] DECODED;
        String fullName = "";
        int fullNameSpaces = 0;

        while (!done) {

            boolean fin = false;
            boolean masked = false;
            int payloadLength = 0;
            int returnMessageLength = 2;
            String outputString = "";
            byte[] output = null;

            byte b0 = webSocket.readByte();
            if ((b0 & 0x80) != 0) {
                fin = true;
            }
            byte opCode = (byte) (b0 & 0x0F);

            byte b1 = webSocket.readByte();
            if ((b1 & 0x80) != 0) {
                masked = true;
            }

            byte guessLength = (byte) (b1 & 0x7F);

            if (guessLength == 0x7E) {
                short extendedPayloadLength = webSocket.readShort();
                payloadLength = extendedPayloadLength;
                returnMessageLength += 2 + extendedPayloadLength;
            } else if (guessLength == 0x7F) {
                long extendedPayloadLength = webSocket.readLong();
                payloadLength = (int) extendedPayloadLength;
                returnMessageLength += 8 + extendedPayloadLength;
            } else {
                payloadLength = guessLength;
            }

            byte[] returnMessage = new byte[returnMessageLength];


            if (masked) {


                byte[] MASK = new byte[4];
                for (int i = 0; i < 4; i++) {
                    MASK[i] = webSocket.readByte();
                }
                byte[] ENCODED = new byte[payloadLength];
                for (int i = 0; i < payloadLength; i++) {
                    ENCODED[i] = webSocket.readByte();
                }
                DECODED = new byte[ENCODED.length];

                for (int i = 0; i < ENCODED.length; i++) {
                    DECODED[i] = (byte) (ENCODED[i] ^ MASK[i % 4]);
                }


            } else {
                DECODED = new byte[payloadLength];
                for (int i = 0; i < payloadLength; i++) {
                    DECODED[i] = webSocket.readByte();
                }
            }




            String s = new String(DECODED, StandardCharsets.UTF_8);
            String[] arr = s.split(" ");

            if (arr[0].equals("join")) {
                room = Room.getRoom(arr[arr.length - 1]);
                if(arr.length > 3) {
                    for (int i = 1; i < arr.length - 1; i++) {
                        fullName += arr[i] + " ";
                        fullNameSpaces++;
                    }
                }else{
                    fullName = arr[1];
                }
                room.addClient(fullName, socketToClient);
                outputString = "{\"type\" :\"join\", \"user\" :\"" + fullName + "\",\"room\" :\"" + room.roomName_
                        + "\"}";

            }
            else if( arr[0].equals("leave") ){
                outputString = "{\"type\" :\"leave\", \"user\" :\"" + fullName + "\",\"room\" :\"" + room.roomName_
                        + "\"}";
                room.removeClient( arr[1], socketToClient);
                done = true;
            }
            else {
                for (int i = 0; i < room.clientNames_.size(); i++) {
                    if (arr[0].equals(room.clientNames_.get(i))) {
                        outputString = "{\"type\" :\"message\", \"user\" :\"" + fullName + "\",\"room\" :\"" + room.roomName_
                                + "\", \"message\" :\"";
                                for(int j = fullNameSpaces + 1 ; j < arr.length; j++){
                                    outputString += " " + arr[j];
                                }
                                outputString += "\"}";
                    }
                }
            }

                int startLocation = 2;
                if (outputString.length() > Short.MAX_VALUE) {
                    output = new byte[2 + outputString.length() + 8];
                    output[1] = 0x7F;
                    long payloadSize = Long.valueOf(outputString.length());
                    for (int i = 9; i > 1; i--) {
                        output[i] = (byte) payloadSize;
                        payloadSize >>= 8;
                    }
                    startLocation = 10;
                } else if (outputString.length() > 124) {
                    output = new byte[2 + outputString.length() + 2];
                    output[1] = 0x7E;
                    int payloadSize = outputString.length();
                    for (int i = 3; i > 1; i--) {
                        output[i] = (byte) payloadSize;
                        payloadSize >>= 8;
                    }
                    startLocation = 4;
                } else {
                    output = new byte[2 + outputString.length()];
                    output[1] = (byte) (outputString.length());
                }
                output[0] = b0;
                byte[] temp = outputString.getBytes();
                for (int i = 0; i < outputString.length(); i++) {
                    output[i + startLocation] = temp[i];
                }

            System.out.write(output, 0, output.length);
            System.out.println("");

            room.sendMessages( output );
        }
    }
}
