package UDPClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

import UDPUtils.UDPUtils;
import UDPUtils.ClientData;
import UDPUtils.ClientRequest;

public class UDPClient {

	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			System.err.println("Insufficient arguments");
			return;
		}

        String name = args[0];
		InetAddress address = InetAddress.getByName(args[1]);
		int port = Integer.parseInt(args[2]);

		DatagramSocket socket = new DatagramSocket();
		byte[] buf = UDPUtils.marshallRequest(new ClientRequest(name, ClientRequest.RequestType.WAITING));
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);

		while (true) {
			socket.send(packet);
			byte[] recBuf = new byte[2048];
			DatagramPacket rec = new DatagramPacket(recBuf, recBuf.length);
			socket.receive(rec);
			UDPUtils.printPacketInfo(rec);
			printMap(demarshallReg(recBuf));
			Thread.sleep(1000);
		}
	}

	public static HashMap<String, ClientData> demarshallReg(byte[] data) {
		HashMap<String, ClientData> ret = null;
		try (ByteArrayInputStream bis = new ByteArrayInputStream(data); ObjectInput in = new ObjectInputStream(bis)) {
			ret = (HashMap<String, ClientData>) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static void printMap(HashMap<String, ClientData> map) {
		for (String name : map.keySet()) {
            ClientData clientData = map.get(name);
			System.out.println(name + " : " + clientData.getIp().getHostAddress() + ":" + clientData.getPort());
		}
	}

}