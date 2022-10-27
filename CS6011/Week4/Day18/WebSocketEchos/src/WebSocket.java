import java.io.*;

public class WebSocket {

    public WebSocket(InputStream in, OutputStream out) throws IOException {


        DataInputStream webSocket = new DataInputStream(new BufferedInputStream(in));
        boolean done = false;


        while(!done) {

            //DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socketToClient.getOutputStream()));
            boolean fin = false;
            boolean masked = false;
            int payloadLength = 0;
            int returnMessageLength = 2;

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
                byte[] DECODED = new byte[ENCODED.length];

                for (int i = 0; i < ENCODED.length; i++) {
                    DECODED[i] = (byte) (ENCODED[i] ^ MASK[i % 4]);
                }

                byte[] output = new byte[(DECODED.length + 2)];
                output[0] = b0;
                output[1] = (byte) (b1 & 0x7F);
                for (int i = 0; i < DECODED.length; i++) {
                    output[i + 2] = DECODED[i];
                }

                System.out.write(output, 0, output.length);
                System.out.println("");
                out.write(output, 0, output.length);

            } else {
                byte[] DECODED = new byte[payloadLength];
                for (int i = 0; i < payloadLength; i++) {
                    DECODED[i] = webSocket.readByte();
                }
                byte[] output = new byte[(DECODED.length + 2)];
                output[0] = b0;
                output[1] = (byte) (b1 & 0x7F);

                for (int i = 0; i < DECODED.length; i++) {
                    output[i + 2] = DECODED[i];
                }

                System.out.write(output, 0, output.length);
                System.out.println("");
                out.write(output, 0, output.length);
            }
        }
    }
}
