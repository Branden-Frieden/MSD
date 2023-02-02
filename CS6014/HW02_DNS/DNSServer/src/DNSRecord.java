import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.HashMap;

public class DNSRecord {
    String[] name_;
    short type_, class_, rdLength_;
    int TTL_;
    byte[] rData_;

    long creationDate_ = Instant.now().getEpochSecond();

    public static DNSRecord decodeRecord(InputStream inputStream, DNSMessage message) throws IOException {
        DNSRecord record = new DNSRecord();

        String[] name  = message.readDomainName(inputStream);

        byte byte1 = (byte) inputStream.read();
        byte byte2 = (byte) inputStream.read();
        short type = (short) ((byte1 & 0xFF) << 8 | (byte2 & 0xFF));

        byte1 = (byte) inputStream.read();
        byte2 = (byte) inputStream.read();
        short rClass = (short) ((byte1 & 0xFF) << 8 | (byte2 & 0xFF));

        byte1 = (byte) inputStream.read();
        byte2 = (byte) inputStream.read();

        byte byte3 = (byte) inputStream.read();
        byte byte4 = (byte) inputStream.read();
        int TTL = (int) ((byte1 & 0xFF) << 24 | (byte2 & 0xFF) << 16 | (byte3 & 0xFF) << 8 | (byte4 & 0xFF));

        byte1 = (byte) inputStream.read();
        byte2 = (byte) inputStream.read();
        short rdLength = (short) ((byte1 & 0xFF) << 8 | (byte2 & 0xFF));

        byte[] rData = inputStream.readNBytes(rdLength);

        record.name_ = name;
        record.type_ = type;
        record.class_ = rClass;
        record.TTL_ = TTL;
        record.rdLength_ = rdLength;
        record.rData_ = rData;

        return record;
    }

    boolean isExpired(){
        return (creationDate_ + TTL_ < Instant.now().getEpochSecond());
    }

    void writeBytes(ByteArrayOutputStream stream, HashMap<String, Integer> domainNameLocations){
        if(domainNameLocations.containsKey(name_.toString())){
            short offset = (short) (domainNameLocations.get(name_.toString()) | 0xc0 );

            byte[] offsetBytes = new byte[2];
            offsetBytes[0] = (byte)((offset >> 8) & 0xff);
            offsetBytes[1] = (byte)(offset & 0xff);
            stream.writeBytes(offsetBytes);

        }else{
            for(int i = 0; i < name_.length; i++){
                stream.write( (byte) (name_[i].length()) );

                for(char letter: name_[i].toCharArray()){
                    stream.write((byte) letter);
                }
            }
            stream.write((byte) 0x00);
        }


        byte[] typeBytes = new byte[2];
        typeBytes[0] = (byte)((type_ >> 8) & 0xff);
        typeBytes[1] = (byte)(type_ & 0xff);
        stream.writeBytes(typeBytes);


        byte[] qClassBytes = new byte[2];
        qClassBytes[0] = (byte)((class_ >> 8) & 0xff);
        qClassBytes[1] = (byte)(class_ & 0xff);
        stream.writeBytes(qClassBytes);

        byte[] ttlBytes = new byte[4];
        ttlBytes[0] = (byte)((TTL_ >> 24) & 0xff );
        ttlBytes[1] = (byte)((TTL_ >> 16) & 0xff);
        ttlBytes[2] = (byte)((TTL_ >> 8)  & 0xff);
        ttlBytes[3] = (byte)((TTL_) & 0xff);
        stream.writeBytes(ttlBytes);

        byte[] rdLengthBytes = new byte[2];
        rdLengthBytes[0] = (byte)((rdLength_ >> 8) & 0xff);
        rdLengthBytes[1] = (byte)(rdLength_ & 0xff);
        stream.writeBytes(rdLengthBytes);

        stream.writeBytes(rData_);
    }

}
