package Commands.SerializableCommands;

import Commands.AbstractCommand;

import java.io.Serializable;

public class SerializedSimplyCommand implements Serializable {
    private  final AbstractCommand command;
    private final long serialVersionUID = 1234567L;


    public SerializedSimplyCommand(AbstractCommand command) {
        this.command = command;
    }

    public AbstractCommand getCommand(){
        return command;
    }
}
