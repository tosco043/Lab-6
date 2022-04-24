package Commands.SerializableCommands;

import Commands.AbstractCommand;
import java.io.Serializable;

public class SerializedArgumentCommand implements Serializable {
    private final AbstractCommand command;
    private String arg;

    private static final long serialVersionUID = 1234567L;

    public SerializedArgumentCommand(AbstractCommand command, String arg){
        this.command = command;
        this.arg = arg;
    }

    public AbstractCommand getCommand(){
        return command;
    }
}
