package Utilities;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class Communicator {
    private DatagramChannel datagramChannel;
    private final SocketAddress socketAddress;
    private Selector selector;

    public Communicator(String host, int port) {
        this.socketAddress = new InetSocketAddress(host, port);
    }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    public void startCommunication() {
        try {
            selector = Selector.open();
            datagramChannel = DatagramChannel.open();
            datagramChannel.bind(null);
            datagramChannel.configureBlocking(false);
            datagramChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DatagramChannel selectWriteChannel() throws IOException {
        long startTime = System.currentTimeMillis();

        while (true) {
            if (System.currentTimeMillis() - startTime > 5000) {
                System.out.println("Cannot connect to server! Please make ensure the Server is ready");
                return null;
            }
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                if (key.isValid()) {
                    if (key.isWritable()) {
                        return (DatagramChannel) key.channel();
                    }
                }
            }
        }
    }
    public DatagramChannel selectReadChannel() throws IOException{
        long startTime = System.currentTimeMillis();

        while (true){
            if(System.currentTimeMillis() - startTime > 5000){
                System.out.println("Cannot connect to server! Please make sure the server is ready!");
                return  null;
            }
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while(keys.hasNext()){
                SelectionKey key = keys.next();
                keys.remove();
                if(key.isValid()){
                    if(key.isReadable())  return (DatagramChannel) key.channel();
                }
            }
        }
    }
    public void endCommunication() throws IOException {
        datagramChannel.close();
    }

}
