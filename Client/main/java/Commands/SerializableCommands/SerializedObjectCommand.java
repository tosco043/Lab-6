package Commands.SerializableCommands;

import Commands.AbstractCommand;

import java.io.Serializable;

public class SerializedObjectCommand implements Serializable {
    private final AbstractCommand command;
    private final Object object;
    private static final long serialVersionUID = 1234567L;

    public SerializedObjectCommand(AbstractCommand command, Object object){
        this.command = command;
        this.object = object;
    }

    public AbstractCommand getCommand() {
        return command;
    }
}
