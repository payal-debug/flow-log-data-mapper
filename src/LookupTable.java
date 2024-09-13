import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LookupTable {
    private Map<String, String> lookupMap;

    public LookupTable(String filePath) throws IOException {
        lookupMap = new HashMap<>();
        loadLookupTable(filePath);
    }

    private void loadLookupTable(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String key = parts[0] + "-" + parts[1].toLowerCase(); // Key is dstPort-protocol
                    lookupMap.put(key, parts[2]);
                }
            }
        }
    }

    public String getTag(int dstPort, String protocol) {
        return lookupMap.getOrDefault(dstPort + "-" + protocol.toLowerCase(), "Untagged");
    }
}
