import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HTTPRequest {

    String filename;

    Socket socketToClient;

    public HTTPRequest(Socket clientSocket) throws IOException {

        socketToClient = clientSocket;
        InputStream inputstream = socketToClient.getInputStream();

        Scanner sc = new Scanner(inputstream);

        //only read the 1st line once

        // break line into 3 pieces
        sc.next();
        filename = sc.next();
        if(filename.equals("/"))

        {
            filename = "index.html";
        } else
        {
            filename = filename.substring(1);
        }

        String line = sc.nextLine();
                while(!line.isEmpty())

        {

            // read header line
            line = sc.nextLine();

            //break ‘line’ into key: value pairs

            // store in hash map
        }
    }
    public String HTTPgetFileName(){
        return filename;
    }

    public Socket HTTPgetClientSocket(){
        return socketToClient;
    }
}
