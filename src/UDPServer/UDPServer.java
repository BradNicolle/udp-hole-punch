package UDPServer;

import java.io.*;
import java.net.*;
import UDPUtils.UDPUtils;
import UDPUtils.ClientData;
import UDPUtils.ClientRequest;

public class UDPServer {
	private static int counter = 0;
	
	public static void main(String[] args) throws IOException {
		ClientRegistry registry = new ClientRegistry();
		DatagramSocket socket = new DatagramSocket(4445);
		new Thread(registry).start();
		while(true) {
			byte[] buf = new byte[256];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);

            ClientRequest request = UDPUtils.demarshallRequest(buf);

			registry.addClient(request.getName(), new ClientData(packet.getAddress(), packet.getPort()));
			byte[] regData = registry.getRegistry();
			DatagramPacket sendPacket = new DatagramPacket(regData, regData.length, packet.getAddress(), packet.getPort());
			UDPUtils.printPacketInfo(packet);
			socket.send(sendPacket);
		}
	}

}