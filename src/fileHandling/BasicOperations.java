package fileHandling;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class BasicOperations {

    static final Charset encoding = Charset.forName("Cp1257");

    static boolean checkFileStatus(String filePath){
        File fileToCheck = new File(filePath);

        if (!fileToCheck.exists()) System.out.println("File not found");
        else if (!fileToCheck.canRead()) System.out.println("File not readable");
        else return true;
        return false;
    }

    static String[] readTextFile(String filePath){
        System.out.println("Reading file from " + filePath);
        if (checkFileStatus(filePath)) {
            try {
                List<String> linesRead = readAllLines(Path.of(filePath), encoding);
                if (linesRead.size() < 1) System.out.println("File empty or incorrect encoding");
                else {
                    System.out.println("Number of lines read: " + linesRead.size() + " in encoding: " + encoding);
                    return linesRead.toArray(new String[0]);
                }
            } catch (IOException e) {
                System.out.println("An unexpected error occurred while reading file");
                e.printStackTrace();
            }
        }
        return null;
    }

    static final String valueSeparator = " : ";

    public static ArrayList<String[]> prepareValueList(String filePath){
        String[] readLines = readTextFile(filePath);
        if (readLines != null) {
            ArrayList<String[]> valueList = new ArrayList<>();

            System.out.println("ListSplitter - number of lines: " + readLines.length);
            for (int i=0; i<readLines.length; i++){
                String[] splitString = readLines[i].split(valueSeparator);
                valueList.add(splitString);
            }

            return valueList;
        }
        return null;
    }

    public static void writeToFile(String filePath, ArrayList<String[]> writableData){
        try {
            File settingsFile = new File(filePath);
            if (settingsFile.delete()) System.out.println("Previous file deleted");
            if (settingsFile.createNewFile()) System.out.println("New file created");

            List<String> writableLines = new ArrayList<>();

            for(int i=0; i<writableData.size(); i++) {
                String writableLine = "";

                for(int j=0; j<writableData.get(i).length; j++){
                    if (j>0)
                        writableLine = writableLine + valueSeparator;

                    writableLine = writableLine + writableData.get(i)[j];
                }

                writableLines.add(writableLine);
            }

            Files.write(Path.of(filePath), writableLines, encoding);

        } catch (IOException e) {
            System.out.println("An unexpected error occurred while creating at " + filePath);
            e.printStackTrace();
        }
    }

    static Image readSingleImage(String filePath){
        System.out.println("Loading image from " + filePath);
        if(checkFileStatus(filePath)) {
            File imageFile = new File(filePath);
            try {
                return ImageIO.read(imageFile);
            } catch (IOException e) {
                System.out.println("An unexpected error occurred while loading image");
                e.printStackTrace();
            }
        }
        return null;
    }

}
