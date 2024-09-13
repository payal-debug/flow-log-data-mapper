import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlowLogParser {
    public List<FlowLog> parse(String filePath) throws IOException {
        List<FlowLog> flowLogs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int dstPort = Integer.parseInt(parts[6]);
                String protocol = parts[7].equals("6") ? "tcp" : "udp";

                FlowLog flowLog = new FlowLog(protocol, dstPort);
                flowLogs.add(flowLog);
            }
        }

        return flowLogs;
    }
}
