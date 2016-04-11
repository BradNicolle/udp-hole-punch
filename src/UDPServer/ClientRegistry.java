package UDPServer;

import UDPUtils.ClientData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class ClientRegistry implements Runnable {
    private final HashMap<String, ClientData> registry = new HashMap<>();
    private static final int DEFAULT_TTL = 10000;
    private final int ttl;

    public ClientRegistry() {
        this(DEFAULT_TTL);
    }

    public ClientRegistry(int ttl) {
        this.ttl = ttl;
    }

    public synchronized void addClient(String name, ClientData data) {
        registry.put(name, data);
    }

    public synchronized byte[] getRegistry() {
        byte[] output = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutput oo = new ObjectOutputStream(bos)) {
            oo.writeObject(registry);
            output = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(output.length);
        return output;
    }

    public synchronized void removeClient(String name) {
        registry.remove(name);
    }

    public synchronized void clearRegistry() {
        registry.clear();
    }

    @Override
    public void run() {
        while (true) {
            try {
                clearRegistry();
                Thread.sleep(ttl);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
