import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        ServerSocket servSocket = new ServerSocket(8080);
        boolean done = false;
        while (!done) {

            Socket socketToClient = servSocket.accept();

            InputStream inputstream = socketToClient.getInputStream();

            Scanner sc = new Scanner(inputstream);

            //only read the 1st line once

            // break line into 3 pieces
            sc.next();
            String filename = sc.next();
            if (filename.equals("/")) {
                filename = "index.html";
            }
            else{
                filename = filename.substring(1);
            }

            String line = sc.nextLine();
            while (!line.isEmpty()) {

                // read header line
                line = sc.nextLine();

                //break ‘line’ into key: value pairs

                // store in hash map


            }

            // reading of request header is done…

            // open the requested file ( filename)
            File file = new File(filename);
            String result;
            if (file.isFile()) {
                result = "200 OK";
            } else {
                result = "404 not found";
            }

            OutputStream outputStream = socketToClient.getOutputStream();
            PrintWriter pw = new PrintWriter(outputStream);


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
        }
    }
}