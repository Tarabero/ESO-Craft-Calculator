package util;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public static void readFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner fileScanner = new Scanner(file);
    }

}
