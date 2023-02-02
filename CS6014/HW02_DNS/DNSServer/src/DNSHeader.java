import java.io.IOException;
import java.io.InputStream;
public class DNSHeader {
    byte QR_, opcode_, AA_, TC_, RD_, RA_, Z_, rCode_;
    short qdCount_, anCount_, nsCount_, arCount_, id_;
    static DNSHeader decodeHeader(InputStream stream) throws IOException {
        DNSHeader header = new DNSHeader();

        byte mask1 = 0x01;
        byte mask2 = 0x02;
        byte mask3 = 0x04;
        byte mask8 = (byte) 0x80;

        byte byte1 = (byte) stream.read();
        byte byte2 = (byte) stream.read();
        short id = (short) ((byte1 & 0xFF) << 8 | (byte2 & 0xFF));

        byte b1 = (byte) stream.read();
        byte QR = (byte) ((b1 & mask8));
        QR >>= 7;
        QR = (byte) (QR & mask1);

        byte opcode = (byte) (b1 & 0x78); // mask = 0111 1000
        opcode >>= 3;

        byte AA = (byte) (b1 & mask3);
        AA >>= 2;

        byte TC = (byte) (b1 & mask2);
        TC >>= 1;

        byte RD = (byte) (b1 & mask1);

        byte b2 = (byte) stream.read();

        byte RA = (byte) ((b2 & mask8) >> 7);
        RA = (byte) (RA & mask1);

        byte Z = (byte) (b2 & 0x70); // mask = 0111 0000
        Z >>= 4;

        byte rCode = (byte) (b2 & 0x0F); // mask = 0000 1111

        byte1 = (byte) stream.read();
        byte2 = (byte) stream.read();
        short qdCount = (short) ((byte1 & 0xFF) << 8 | (byte2 & 0xFF));
        byte1 = (byte) stream.read();
        byte2 = (byte) stream.read();
        short anCount = (short) ((byte1 & 0xFF) << 8 | (byte2 & 0xFF));
        byte1 = (byte) stream.read();
        byte2 = (byte) stream.read();
        short nsCount = (short) ((byte1 & 0xFF) << 8 | (byte2 & 0xFF));
        byte1 = (byte) stream.read();
        byte2 = (byte) stream.read();
        short arCount = (short) ((byte1 & 0xFF) << 8 | (byte2 & 0xFF));

        header.id_ = id;
        header.QR_ = QR;
        header.opcode_ = opcode;
        header.AA_ = AA;
        header.TC_ = TC;
        header.RD_ = RD;
        header.RA_ = RA;
        header.Z_ = Z;
        header.rCode_ = rCode;
        header.qdCount_ = qdCount;
        header.anCount_ = anCount;
        header.nsCount_ = nsCount;
        header.arCount_ = arCount;

        return header;
    }

    static DNSHeader buildHeaderForResponse(DNSMessage request, DNSMessage response){
        DNSHeader header = new DNSHeader();

        header.id_ = request.header_.id_;
        header.QR_ = 0x01;
        header.opcode_ = request.header_.opcode_;
        header.AA_ = 0x00;
        header.TC_ = request.header_.TC_;
        header.RD_ = 0x01;
        header.RA_ = 0x01;
        header.Z_ = request.header_.Z_;
        header.rCode_ = request.header_.rCode_;
        header.qdCount_ = (short) response.questions_.length;
        header.anCount_ = (short) response.anRecords_.length;
        header.nsCount_ = (short) response.nsRecords_.length;
        header.arCount_ = (short) response.arRecords_.length;

        return header;
    }

    byte[] toBytes(){
        byte[] output = new byte[12];

        output[0] = (byte) (id_ >> 8);
        output[1] = (byte) (id_ & 0x00ff);
        output[2] = (byte) ((0x80) | ((opcode_ << 6) & 0x78) | ((AA_ << 2) & 0x04) | ((TC_ << 1) & 0x03) | RD_);

        output[3] = (byte) (((RA_ << 7) & 0x80) | ((Z_ << 6) & 0x70) | rCode_);

        output[4] = (byte) (qdCount_ >> 8);
        output[5] = (byte) (qdCount_ & 0x00ff);

        output[6] = (byte) (anCount_ >> 8);
        output[7] = (byte) (anCount_ & 0x00ff);

        output[8] = (byte) (nsCount_ >> 8);
        output[9] = (byte) (nsCount_ & 0x00ff);

        output[10] = (byte) (arCount_ >> 8);
        output[11] = (byte) (arCount_ & 0x00ff);

        return output;
    }
}
