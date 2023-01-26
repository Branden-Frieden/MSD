import java.io.IOException;
import java.io.InputStream;

public class DNSQuestion {

    byte[] qName_;
    byte[] qType_;
    byte[] qClass_;

    static DNSQuestion decodeQuestion(InputStream stream, DNSMessage message) throws IOException {
        DNSQuestion question = new DNSQuestion();

        byte[] qName = new byte[256];
        int i = 0;

        byte numOfBytesToRead = (byte) stream.read();
        qName[i] = (byte) numOfBytesToRead;
        i++;
        
        while( numOfBytesToRead != 0x00){
            int startI = i;
            for(; i <= numOfBytesToRead + startI; i++){
                qName[i] = (byte) stream.read();
            }
            numOfBytesToRead = (byte) stream.read();
            qName[i] = numOfBytesToRead;
            i++;
        }

        byte[] qType = stream.readNBytes(2);
        byte[] qClass = stream.readNBytes(2);

        question.qName_ = qName;
        question.qType_ = qType;
        question.qClass_ = qClass;

        return question;
    }
}
