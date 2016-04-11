package UDPUtils;

import java.io.Serializable;
import java.net.InetAddress;

public class ClientData implements Serializable {
    private InetAddress ip;
    private int port;

    public ClientData(InetAddress ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
