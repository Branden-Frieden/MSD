import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HTTPResponse {

    public HTTPResponse(String filename, Socket socketToClient) throws IOException {

        OutputStream outputStream = socketToClient.getOutputStream();

        PrintWriter pw = new PrintWriter(outputStream);

        try {
            // open the requested file ( filename)
            File file = new File(filename);
            String result;
            if (file.isFile()) {
                result = "200 OK";
            } else {
                result = "404 not found";
            }

            // send response header
            pw.println("HTTP/1.1 " + result);

            // send back content-type
            // determine file extension (html or css) and use it here
            pw.println("Content-Type: text/" + filename.substring(filename.lastIndexOf(".") + 1) + "; charset=UTF-8");
            pw.println("Content-Length: " + file.length());
            pw.println(); // ends the response header with blank line

            FileInputStream fileInput = new FileInputStream(file);
            Scanner fileReader = new Scanner(fileInput);

            while (fileReader.hasNextLine()) {
                pw.println(fileReader.nextLine());
            }

            // send the data from the file
            pw.close();
            socketToClient.close();
        } catch (IOException e) {
            String result = "404 not found";
            System.out.println(e.getMessage());
            pw.println("HTTP/1.1 " + result);
            pw.println(e.getMessage());
            pw.println();
            pw.close();
            socketToClient.close();
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket servSocket = new ServerSocket(8080);
        boolean done = false;

        while (!done) {

           HTTPRequest req = new HTTPRequest(servSocket);
           new HTTPResponse( req.HTTPgetFileName(), req.HTTPgetClientSocket());

        }
    }
}