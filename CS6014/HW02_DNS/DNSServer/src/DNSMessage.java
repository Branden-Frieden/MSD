import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DNSMessage {
    DNSHeader header_;
    DNSQuestion[] questions_;
    DNSRecord[] anRecords_;
    DNSRecord[] nsRecords_;
    DNSRecord[] arRecords_;
    static DNSMessage decodeMessage(byte[] bytes) throws IOException {
        // initialize return DNSMessage
        DNSMessage message = new DNSMessage();

        // create input stream from bytes
        InputStream inputStream = new ByteArrayInputStream(bytes);

        // decode header
        DNSHeader header = DNSHeader.decodeHeader(inputStream);

        // decode question
        short questionCount = (short) (header.qdCount_[0] << 8 + header.qdCount_[1]);
        DNSQuestion[] questions = new DNSQuestion[questionCount];

        for(int i = 0; i < questionCount; i++){
            questions[i] = DNSQuestion.decodeQuestion(inputStream, message);
        }


        // decode anRecords
        short anRecordCount = (short) (header.anCount_[0] << 8 + header.anCount_[1]);
        DNSRecord[] anRecords = new DNSRecord[anRecordCount];

        for(int i = 0; i < anRecordCount; i++){
            anRecords[i] = DNSRecord.decodeRecord(inputStream, message);
        }

        // decode nsRecords
        short nsRecordCount = (short) (header.nsCount_[0] << 8 + header.nsCount_[1]);
        DNSRecord[] nsRecords = new DNSRecord[nsRecordCount];

        for(int i = 0; i < nsRecordCount; i++){
            anRecords[i] = DNSRecord.decodeRecord(inputStream, message);
        }

        // decode arRecords
        short arRecordCount = (short) (header.arCount_[0] << 8 + header.arCount_[1]);
        DNSRecord[] arRecords = new DNSRecord[arRecordCount];

        for(int i = 0; i < arRecordCount; i++){
            arRecords[i] = DNSRecord.decodeRecord(inputStream, message);
        }


        // store in message
        message.header_ = header;

        return message;
    }
}
