import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class HandleDataFile {
  static final String DATA_FILE_PATH = "data.csv";
  static final String DATA_FILE_SEPARATOR = ", ";

  public static ArrayList<String[]> readFromCSVFile() {
    ArrayList<String[]> data = new ArrayList<>();

    try {
      BufferedReader bufferedReader = new BufferedReader(new FileReader(DATA_FILE_PATH));
      while (true) {
        String line = bufferedReader.readLine();
        if (line == null)
          break;

        data.add(line.split(DATA_FILE_SEPARATOR));
      }
      bufferedReader.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return data;
  }

  public static void saveToCSVFile(String title, String description) {
    try {
      String[] taskData = { title, description, System.currentTimeMillis() + "" };

      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(DATA_FILE_PATH, true));
      bufferedWriter.write(String.join(DATA_FILE_SEPARATOR, taskData));
      bufferedWriter.newLine();
      bufferedWriter.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
