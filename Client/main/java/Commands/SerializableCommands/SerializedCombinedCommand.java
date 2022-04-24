package Commands.SerializableCommands;

import Commands.AbstractCommand;

import java.io.Serializable;

public class SerializedCombinedCommand implements Serializable {
    private final AbstractCommand command;
    private static final long serialVersionUID = 1234567L;
    private final String arg;
    private final Object object;

    public SerializedCombinedCommand (AbstractCommand command, String arg, Object object){
        this.command = command;
        this.arg = arg;
        this.object = object;
    }

    public AbstractCommand getCommand() {
        return command;
    }
}
