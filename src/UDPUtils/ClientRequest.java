package UDPUtils;

public class ClientRequest {
    private String name;
    public enum RequestType {WAITING, CONNECT};
    private RequestType type;

    public ClientRequest(String name, ClientRequest.RequestType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientRequest.RequestType getType() {
        return type;
    }

    public void setType(ClientRequest.RequestType type) {
        this.type = type;
    }
}
