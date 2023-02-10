package com.techelevator.filereader;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This would be a GREAT place to have a public method that could take a formatted String and log it out to a file.
 */
public class LogFileWriter {
 private static File logFile = new File("Log.txt");

    public LogFileWriter() {
        try(PrintWriter test = new PrintWriter(logFile)){
            test.println(generateTimeStamp() + " Audit Log Created");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String generateTimeStamp(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        return dateFormat.format(date);
    }

    public static void appendLogFile(String transaction){
        try(FileWriter fileWriter = new FileWriter(logFile,true);
            BufferedWriter writer = new BufferedWriter(fileWriter)){
            writer.newLine();
            writer.write(generateTimeStamp() + " " + transaction);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
    }




}
}
