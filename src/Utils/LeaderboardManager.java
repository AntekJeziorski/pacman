package Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class LeaderboardManager {
    public static ArrayList<ArrayList<String>> getScoreRecords() {
        File csvFile = new File("PacManLeaderBoard.csv");
        ArrayList<ArrayList<String>> scoreRecords = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<String> record = new ArrayList<>(Arrays.asList(line.split(",")));
                scoreRecords.add(record);
            }
        } catch (IOException e) { e.printStackTrace();}

        return scoreRecords;
    }

    public static int getBestScore() {
        ArrayList<ArrayList<String>> readScore = getScoreRecords();
        if (readScore.isEmpty()) { return 0; }
        return Integer.parseInt(readScore.get(0).get(1));
    }


    public static void saveScoreRecord(String nickname, long score) {
        File csvFile = new File("PacManLeaderBoard.csv");
        ArrayList<ArrayList<String>> readScore = getScoreRecords();

        Iterator<ArrayList<String>> iterator = readScore.iterator();
        while (iterator.hasNext()) {
            ArrayList<String> record = iterator.next();
            String existingNickname = record.get(0);
            int existingScore = Integer.parseInt(record.get(1));
            if (existingNickname.equals(nickname) && existingScore == score) { iterator.remove(); }
        }

        ArrayList<String> newScoreRecord = new ArrayList<>(Arrays.asList(nickname, Long.toString(score)));
        readScore.add(newScoreRecord);
        readScore.sort((a, b) -> Integer.parseInt(b.get(1)) - Integer.parseInt(a.get(1)));

        if (readScore.size() > 5) { readScore.subList(5, readScore.size()).clear(); }

        try (PrintWriter writer = new PrintWriter(csvFile)) {
            for (ArrayList<String> record : readScore) {
                writer.println(String.join(",", record));
            }
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

}

