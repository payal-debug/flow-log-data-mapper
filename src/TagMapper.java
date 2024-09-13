import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagMapper {
    private LookupTable lookupTable;

    public TagMapper(LookupTable lookupTable) {
        this.lookupTable = lookupTable;
    }

    public Map<String, Integer> generateTagCounts(List<FlowLog> flowLogs) {
        Map<String, Integer> tagCounts = new HashMap<>();

        for (FlowLog flowLog : flowLogs) {
            String tag = lookupTable.getTag(flowLog.getDstPort(), flowLog.getProtocol());
            tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);
        }

        return tagCounts;
    }

    public Map<String, Integer> generatePortProtocolCounts(List<FlowLog> flowLogs) {
        Map<String, Integer> portProtocolCounts = new HashMap<>();

        for (FlowLog flowLog : flowLogs) {
            String key = flowLog.getDstPort() + "-" + flowLog.getProtocol();
            portProtocolCounts.put(key, portProtocolCounts.getOrDefault(key, 0) + 1);
        }

        return portProtocolCounts;
    }
}
