package Commands.SpecificCommands;

import Commands.AbstractCommand;
import Commands.SerializableCommands.SerializedCombinedCommand;
import Utilities.Logging;
import Utilities.Receiver;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;

public class Update extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = 1234567L;

    @Override
    public void execute(Object o, DatagramSocket datagramSocket, DatagramPacket datagramPacket) throws IOException {
        SerializedCombinedCommand serializedCombinedCommand = (SerializedCombinedCommand) o;
        String arg = serializedCombinedCommand.getArg();
        Object obj = serializedCombinedCommand.getObject();
        Receiver receiver = new Receiver(datagramSocket, datagramPacket);
        long id = Long.parseLong(arg);
        Logging.log(Level.INFO, "Server is executing Update command....");
        receiver.update(id, obj);
    }
}
