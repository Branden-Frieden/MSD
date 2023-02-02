import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class DNSMessage {
    byte[] data_;
    DNSHeader header_;
    DNSQuestion[] questions_;
    DNSRecord[] anRecords_;
    DNSRecord[] nsRecords_;
    DNSRecord[] arRecords_;
    static DNSMessage decodeMessage(byte[] bytes) throws IOException {

        // initialize return DNSMessage
        DNSMessage message = new DNSMessage();

        message.data_ = bytes;
        // create input stream from bytes
        InputStream inputStream = new ByteArrayInputStream(bytes);

        // decode header
        DNSHeader header = DNSHeader.decodeHeader(inputStream);

        // decode question
        DNSQuestion[] questions = new DNSQuestion[header.qdCount_];
        for(int i = 0; i < header.qdCount_; i++){
            questions[i] = DNSQuestion.decodeQuestion(inputStream, message);
        }


        // decode anRecords
        DNSRecord[] anRecords = new DNSRecord[header.anCount_];
        for(int i = 0; i < header.anCount_; i++){
            anRecords[i] = DNSRecord.decodeRecord(inputStream, message);
        }

        // decode nsRecords
        DNSRecord[] nsRecords = new DNSRecord[header.nsCount_];
        for(int i = 0; i < header.nsCount_; i++){
            nsRecords[i] = DNSRecord.decodeRecord(inputStream, message);
        }

        // decode arRecords
        DNSRecord[] arRecords = new DNSRecord[header.arCount_];
        for(int i = 0; i < header.arCount_; i++){
            arRecords[i] = DNSRecord.decodeRecord(inputStream, message);
        }


        // store in message

        message.header_ = header;
        message.questions_ = questions;
        message.anRecords_ = anRecords;
        message.nsRecords_ = nsRecords;
        message.arRecords_ = arRecords;

        return message;
    }



    String[] readDomainName(InputStream stream) throws IOException {

        String[] domainNameUnsqueezed = new String[127];
        for(int i = 0; i < 127; i++){
            domainNameUnsqueezed[i] = "";
        }
        int numOfDomainLayers = 0;
        int numOfBytesToRead;

        boolean first = true;
        do{
            numOfDomainLayers++;
            numOfBytesToRead = stream.read();

            if(first) {
                if ((numOfBytesToRead & 0xc0) == 0xc0) {
                    byte b1 = (byte) stream.read();
                    int firstByte = ((numOfBytesToRead & 0x3f) << 8 | b1);
                    return readDomainName(firstByte);

                }
            }

            first = false;

            for(int i = 0; i < numOfBytesToRead; i++){
                domainNameUnsqueezed[numOfDomainLayers - 1] += (char) stream.read();
            }


        } while (numOfBytesToRead != 0);

        String [] domainName = new String[numOfDomainLayers - 1];
        for(int i = 0; i < numOfDomainLayers - 1; i++){
            domainName[i] = domainNameUnsqueezed[i];
        }
        return domainName;
    }

    String[] readDomainName(int firstByte) throws IOException {

        String[] domainNameUnsqueezed = new String[127];
        for(int i = 0; i < 127; i++){
            domainNameUnsqueezed[i] = "";
        }
        int currentByte = firstByte;

        int numOfDomainLayers = 0;
        int numOfBytesToRead;

        do{
            numOfDomainLayers++;
            numOfBytesToRead = data_[currentByte];
            currentByte++;
            int endByte = currentByte + numOfBytesToRead;
            for(; currentByte < endByte; currentByte++){
                domainNameUnsqueezed[numOfDomainLayers - 1] += (char) data_[currentByte];
            }


        } while (numOfBytesToRead != 0);

        String [] domainName = new String[numOfDomainLayers -1];
        for(int i = 0; i < numOfDomainLayers -1; i++){
            domainName[i] = domainNameUnsqueezed[i];
        }
        return domainName;
    }

    static DNSMessage buildResponse(DNSMessage request, DNSRecord[] answers){
        DNSMessage response = new DNSMessage();

        response.anRecords_ = answers;
        response.arRecords_ = request.arRecords_;
        response.nsRecords_ = request.nsRecords_;
        response.questions_ = request.questions_;
        response.header_ = DNSHeader.buildHeaderForResponse(request, response);

        return response;
    }

    byte[] toBytes(){

        HashMap<String,Integer> domainNameLocations = new HashMap<>();
        byte[] header = header_.toBytes();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        for(DNSQuestion question: questions_){
            question.writeBytes(outputStream, domainNameLocations);
        }

        for(DNSRecord record: arRecords_){
            record.writeBytes(outputStream, domainNameLocations);
        }

        byte[] data = outputStream.toByteArray();

        byte[] output = new byte[ header.length + data.length];

        for (int i = 0; i < output.length; ++i)
        {
            output[i] = i < header.length ? header[i] : data[i - header.length];
        }

        return output;
    }

}
