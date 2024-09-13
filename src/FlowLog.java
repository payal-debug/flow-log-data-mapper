public class FlowLog {
    private String protocol;
    private int dstPort;

    public FlowLog(String protocol, int dstPort) {
        this.protocol = protocol.toLowerCase(); // Case insensitive
        this.dstPort = dstPort;
    }

    public String getProtocol() {
        return protocol;
    }

    public int getDstPort() {
        return dstPort;
    }
}
