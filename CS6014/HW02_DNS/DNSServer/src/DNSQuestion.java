import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class DNSQuestion {
    String[] qName_;
    short qType_, qClass_;

    static DNSQuestion decodeQuestion(InputStream stream, DNSMessage message) throws IOException {
        // initialize return variable
        DNSQuestion question = new DNSQuestion();

        // initialize qName string array
        String[] qName = message.readDomainName(stream);

        // read next 2 bytes for qType, and next 2 for qClass
        byte byte1 = (byte) stream.read();
        byte byte2 = (byte) stream.read();
        short qType = (short) ((byte1 & 0xFF) << 8 | (byte2 & 0xFF));

        byte1 = (byte) stream.read();
        byte2 = (byte) stream.read();
        short qClass = (short) ((byte1 & 0xFF) << 8 | (byte2 & 0xFF));


        // store values in the return objects member variables
        question.qName_ = qName;
        question.qType_ = qType;
        question.qClass_ = qClass;

        return question;
    }
    void writeBytes(ByteArrayOutputStream stream, HashMap<String,Integer> domainNameLocations){

        if(domainNameLocations.containsKey(qName_.toString())){
            short offset = (short) (domainNameLocations.get(qName_.toString()) | 0xc0 );

            byte[] offsetBytes = new byte[2];
            offsetBytes[0] = (byte)((offset >> 8) & 0xff);
            offsetBytes[1] = (byte)(offset & 0xff);

            stream.writeBytes(offsetBytes);

        }else{
            for(int i = 0; i < qName_.length; i++){
                stream.write( (byte) (qName_[i].length()) );

                for(char letter: qName_[i].toCharArray()){
                    stream.write((byte) letter);
                }
            }
            stream.write((byte) 0x00);
        }


        byte[] qTypeBytes = new byte[2];
        qTypeBytes[0] = (byte)((qType_ >> 8) & 0xff);
        qTypeBytes[1] = (byte)(qType_ & 0xff);

        stream.writeBytes(qTypeBytes);


        byte[] qClassBytes = new byte[2];
        qClassBytes[0] = (byte)((qClass_ >> 8) & 0xff);
        qClassBytes[1] = (byte)(qClass_ & 0xff);

        stream.writeBytes(qClassBytes);

    }

    @Override
    public String toString() {
        return "DNSQuestion{" +
                "qName_=" + Arrays.toString(qName_) +
                ", qType_=" + qType_ +
                ", qClass_=" + qClass_ +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DNSQuestion that)) return false;
        return qType_ == that.qType_ && qClass_ == that.qClass_ && Arrays.equals(qName_, that.qName_);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(qType_, qClass_);
        result = 31 * result + Arrays.hashCode(qName_);
        return result;
    }
}
