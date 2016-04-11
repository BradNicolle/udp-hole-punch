package UDPUtils;

import java.net.*;

public class UDPUtils {
	public static void printPacketInfo(DatagramPacket packet) {
		String address = packet.getAddress().getHostAddress();
		int port = packet.getPort();
		System.out.println("Packet received from " + address + " on port " + port);
	}

    public static byte[] marshallRequest(ClientRequest request) {
        byte[] ret = new byte[request.getName().length()+1];
        ret[0] = (byte)request.getType().ordinal();
        byte[] name = request.getName().getBytes();
        for (int i = 0; i < name.length; i++) {
            ret[i+1] = name[i];
        }
        return ret;
    }

	public static ClientRequest demarshallRequest(byte[] data) {
        return new ClientRequest(new String(data, 1, data.length-1), ClientRequest.RequestType.values()[data[0]]);
    }
}