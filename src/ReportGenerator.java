import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ReportGenerator {
    public void generateTagReport(Map<String, Integer> tagCounts, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Tag,Count\n");
            for (Map.Entry<String, Integer> entry : tagCounts.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        }
    }

    public void generatePortProtocolReport(Map<String, Integer> portProtocolCounts, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Port,Protocol,Count\n");
            for (Map.Entry<String, Integer> entry : portProtocolCounts.entrySet()) {
                String[] keyParts = entry.getKey().split("-");
                writer.write(keyParts[0] + "," + keyParts[1] + "," + entry.getValue() + "\n");
            }
        }
    }
}
