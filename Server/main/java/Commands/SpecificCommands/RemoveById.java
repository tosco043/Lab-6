package Commands.SpecificCommands;

import Commands.AbstractCommand;
import Utilities.Logging;
import Utilities.Receiver;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;

public class RemoveById extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = 1234567L;

    @Override
    public void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) throws IOException {
        Receiver receiver = new Receiver(datagramSocket, datagramPacket);
        long id =  Long.parseLong((String) o);
        Logging.log(Level.INFO, "Server is executing RemoveByID command....");
        receiver.removeByID(id);
    }
}
