package Utilities;

import Data.Product;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;

public class Receiver {
    private final DatagramSocket datagramSocket;
    private final DatagramPacket datagramPacket;


    public Receiver(DatagramSocket datagramSocket, DatagramPacket datagramPacket) {
        this.datagramSocket = datagramSocket;
        this.datagramPacket = datagramPacket;
    }


    public void info() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.info());
        Logging.log(Level.INFO, "Server executed InfoCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        //System.out.println("Server: byteStream" + Arrays.toString(b));
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }

    public void show() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.show());
        Logging.log(Level.INFO, "Server executed ShowCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);

    }

    public void clear() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.clear());
        Logging.log(Level.INFO, "Server executed ClearCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);

    }

    public void add(Object o) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.add(o));
        Logging.log(Level.INFO, "Server executed AddCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }

    public void removeByID(long id) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.removeByID(id));
        Logging.log(Level.INFO, "Server executed RemoveByIDCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }

    public void update(long id, Object o) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.update(id, o));
        Logging.log(Level.INFO, "Server executed UpdateCommand!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }


    public void removeGreater (Product o) throws IOException{
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.removeGreater(o));
        Logging.log(Level.INFO, "Server executed Remove if greater command!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);

    }

    public void removeLower (Product o) throws IOException{
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.removeLower(o));
        Logging.log(Level.INFO, "Server executed Remove if lower command!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);

    }

    public void minByOfficialAddress(String args) throws IOException {
        System.out.println("here i am");
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.minByOfficialAddress(args));
        Logging.log(Level.INFO, "Server executed minByOfficialAddress command!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);


    }

    public void countLessThanOfficialAddress (String args) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.countLessThanOfficialAddress(args));
        Logging.log(Level.INFO, "Server executed filterContainsName command!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);


    }

    public void printFieldAscendingType () throws IOException{
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeObject(CollectionManager.printFieldAscendingType());
        Logging.log(Level.INFO, "Server executed printFieldAscendingType command!");
        out.flush();
        byte[] b = byteStream.toByteArray();
        DatagramPacket answerPacket = new DatagramPacket(b, b.length, datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(answerPacket);
    }
}
