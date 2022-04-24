package Commands.SerializableCommands;

import Commands.AbstractCommand;

import java.io.Serializable;

public class SerializedCombinedCommand implements Serializable {
    private final AbstractCommand command;
    private static final long serialVersionUID = 1234567L;
    private final String arg;
    private final Object object;

    public SerializedCombinedCommand (AbstractCommand command, Object object, String arg){
        this.command = command;
        this.object = object;
        this.arg = arg;
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public Object getObject(){
        return object;
    }

    public String getArg(){
        return arg;
    }
}
