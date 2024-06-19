import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class HandleDataFile {
  static final String DATA_FILE_PATH = "data.csv";
  static final String DATA_FILE_SEPARATOR = ",";

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

  public static String[] readByIdFromCSVFile(String taskId) {
    ArrayList<String[]> taskList = readFromCSVFile();

    String[] data = new String[3];

    for (String[] row : taskList) {
      if (row[2].equals(taskId)) {
        data[0] = row[0];
        data[1] = row[1];
        data[2] = row[2];
        break;
      }
    }
    return data;
  }

  public static void saveToCSVFile(String title, String description) {
    try {
      String[] taskData = { title, description, System.currentTimeMillis() + "" };

      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(DATA_FILE_PATH, true));

      System.out.println(taskData);

      bufferedWriter.write(String.join(DATA_FILE_SEPARATOR, taskData));
      bufferedWriter.newLine();
      bufferedWriter.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static void updateTaskByIdInCSVFile(String taskId, String title, String description) {
    System.out.println("================");
    ArrayList<String[]> taskList = readFromCSVFile();

    for(String[] row : taskList){
      if(row[2].equals(taskId)){
        row[0] = title;
        row[1] = description;
        break;
      }
    }

    writeTask(taskList);
  }

  public static void writeTask(ArrayList<String[]> data) {
    try {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(DATA_FILE_PATH, false));

      for (String[] row : data) {
        bufferedWriter.write(String.join(DATA_FILE_SEPARATOR, row));
        bufferedWriter.newLine();
      }
      bufferedWriter.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static void deleteTask(String id) {
    ArrayList<String[]> updatedData = new ArrayList<>();
    ArrayList<String[]> data = readFromCSVFile();

    for (String[] row : data) {
      if (row[2].equals(id))
        continue;
      String[] rowData = { row[0], row[1], row[2] };

      updatedData.add(rowData);
    }
    writeTask(updatedData);
  }
}
