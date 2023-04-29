import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVReader {
    public static String filePath;

    public static List<String[]> readCSVFile() throws IOException {
        List<String[]> readFromFile = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            if (!filePath.endsWith(".csv")){
                throw new IOException("You can read only csv files");
            }
            String line = br.readLine();
            while (line != null) {

                String[] values = line.split(",");
                readFromFile.add(values);
                line = br.readLine();
            }
        }

        return readFromFile;
    }

    public static void getUniqueEmployeesFromCSV(List<Employee> employeeList) {

        List<String[]> fileInfo;
        Map<String, Employee> employeeStringMap = new LinkedHashMap<>();

        try {
            fileInfo = CSVReader.readCSVFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String[] e : fileInfo) {
            String employeeId = e[0].trim();
            String projectId = e[1].trim();
            String startDate = e[2].trim();
            String endDate = e[3].trim();
            Employee employeeTemp = new Employee(employeeId);
            Project projectTemp = new Project(projectId, startDate, endDate);

            employeeTemp.addProject(projectTemp);


            if (!employeeStringMap.containsKey(employeeId)) {
                employeeStringMap.put(employeeId, employeeTemp);

            } else {
                Employee employeeOld = employeeStringMap.get(employeeId);
                employeeOld.addProject(projectTemp);
                employeeStringMap.put(employeeId, employeeOld);
            }


        }
        for (Map.Entry<String, Employee> entry : employeeStringMap.entrySet()) {
            employeeList.add(entry.getValue());
        }
    }
}

