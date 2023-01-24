import java.io.IOException;
import java.io.InputStream;
public class DNSHeader {
    byte QR_, opcode_, AA_, TC_, RD_, RA_, Z_, rCode_;
    byte[] qdCount_, anCount_, nsCount_, arCount_, id_;
    DNSHeader(byte[] id, byte QR, byte opcode, byte AA, byte TC, byte RD, byte RA, byte Z, byte rCode, byte[] qdCount, byte[] anCount, byte[] nsCount, byte[] arCount) {
        QR_ = QR;
        opcode_ = opcode;
        AA_ = AA;
        TC_ = TC;
        RD_ = RD;
        RA_ = RA;
        Z_ = Z;
        rCode_ = rCode;

        id_ = id;
        qdCount_ = qdCount;
        anCount_ = anCount;
        nsCount_ = nsCount;
        arCount_ = arCount;
    }
    static DNSHeader decodeHeader(InputStream stream) throws IOException {

        byte mask1 = 0x01;
        byte mask2 = 0x02;
        byte mask3 = 0x04;
        byte mask8 = (byte) 0x80;

        byte[] id = stream.readNBytes(2);

        byte b1 = (byte) stream.read();
        byte QR = (byte) (b1 & mask8);
        QR >>= 7;

        byte opcode = (byte) (b1 & 0x78); // mask = 0111 1000
        opcode >>= 3;

        byte AA = (byte) (b1 & mask3);
        AA >>= 2;

        byte TC = (byte) (b1 & mask2);
        TC >>= 1;

        byte RD = (byte) (b1 & mask1);

        byte b2 = (byte) stream.read();

        byte RA = (byte) (b2 & mask8);
        RA >>= 7;

        byte Z = (byte) (b2 & 0x70); // mask = 0111 0000
        Z >>= 4;

        byte rCode = (byte) (b2 & 0x0F); // mask = 0000 1111

        byte[] qdCount = stream.readNBytes(2);
        byte[] anCount = stream.readNBytes(2);
        byte[] nsCount = stream.readNBytes(2);
        byte[] arCount = stream.readNBytes(2);

        return new DNSHeader(id, QR, opcode, AA, TC, RD, RA, Z, rCode, qdCount, anCount, nsCount, arCount);
    }
}
