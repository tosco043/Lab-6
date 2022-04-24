package Commands;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.ParseException;

public abstract class AbstractCommand{
    public abstract void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) throws IOException, ParseException;
}

//The class the sets the execution to false all things been equal
