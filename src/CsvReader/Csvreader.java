package CsvReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Csvreader {

    private final File csvFile;
    private static ArrayList<ArrayList<String>> parsedFile;
    int maxNumberOfScores = 5;

    public Csvreader()
    {
        csvFile = new File("PacManLeaderBoard.csv");
        try {
//            boolean outcome = csvFile.createNewFile();
//            if (!outcome){ throw new IOException(); }

            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;
            parsedFile = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                parsedFile.add(new ArrayList<>(Arrays.asList(line.split(","))));
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
    public ArrayList<ArrayList<String>> getParsedFile() { return parsedFile; }
}

