import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RainData {

    ArrayList<String> Months = new ArrayList<String>();
    ArrayList<String> setMonths = new ArrayList<>(Arrays.asList("January",
            "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December"));
    ArrayList<Integer> Years = new ArrayList<Integer>();
    ArrayList<Double> Rainfall = new ArrayList<Double>();
    PrintWriter pw = new PrintWriter( new FileOutputStream( "rainfall_data.txt") );
    String fileName;
    Scanner fileReader;
    RainData(String inputFile) throws FileNotFoundException {
        fileName = inputFile;
        fileReader = new Scanner( new FileInputStream( fileName ) );
        fileReader.next();

        for(int i = 0; i < 240; i++){
            Months.add(fileReader.next());
            Years.add(fileReader.nextInt());
            Rainfall.add(fileReader.nextDouble());
        }
    }

    public void OverallAverage() throws FileNotFoundException {
        double sum = 0;
        PrintWriter pw = new PrintWriter( new FileOutputStream( "rainfall_data.txt") );
        for(int i = 0; i < Rainfall.size(); i++){
            sum += Rainfall.get(i);
        }

        pw.printf("The overall average rainfall amount is %.2f %n %n", sum/(Rainfall.size()));
        pw.close();
    }

    public void averageByMonth() throws FileNotFoundException {
        double sum = 0;
        int data_count = 0;
        PrintWriter pw = new PrintWriter( new FileOutputStream( ("rainfall_data.txt"), true ));

        for(int i = 0; i < 12; i++){
            sum = 0;
            data_count = 0;
            for(int j = 0; j < Rainfall.size(); j++){
                if(Months.get(j).equals(setMonths.get(i))){
                    sum += Rainfall.get(j);
                    data_count++;
                }
            }
            pw.printf("The average rainfall amount for %s is %.2f inches.%n",setMonths.get(i), sum/data_count);
        }
        pw.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        RainData atlanta = new RainData("Macon.txt");
        atlanta.OverallAverage();
        atlanta.averageByMonth();
    }


}