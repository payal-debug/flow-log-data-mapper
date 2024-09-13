import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Main <flowlogFilePath> <lookupTableFilePath> <outputDirectory>");
            return;
        }

        String flowLogFilePath = args[0];
        String lookupTableFilePath = args[1];
        String outputDirectory = args[2];

        try {
            // Load lookup table
            LookupTable lookupTable = new LookupTable(lookupTableFilePath);

            // Parse flow logs
            FlowLogParser parser = new FlowLogParser();
            List<FlowLog> flowLogs = parser.parse(flowLogFilePath);

            // Map tags
            TagMapper tagMapper = new TagMapper(lookupTable);
            Map<String, Integer> tagCounts = tagMapper.generateTagCounts(flowLogs);
            Map<String, Integer> portProtocolCounts = tagMapper.generatePortProtocolCounts(flowLogs);

            // Generate reports
            ReportGenerator reportGenerator = new ReportGenerator();
            reportGenerator.generateTagReport(tagCounts, outputDirectory + "/tag_report.csv");
            reportGenerator.generatePortProtocolReport(portProtocolCounts, outputDirectory + "/port_protocol_report.csv");

            System.out.println("Reports generated successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
