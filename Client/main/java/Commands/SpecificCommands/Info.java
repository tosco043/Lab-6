package Commands.SpecificCommands;

import Commands.AbstractCommand;
import Utilities.Receiver;

import java.io.IOException;
import java.io.Serializable;

public class Info extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private Receiver receiver;

    public Info(){}

    public Info(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void aboutCommand() {

    }

    @Override
    public void execute(String[] args) throws IOException {

        if(args.length != 1) {
            System.out.println("Client: Invalid command's format! Fail to execute Info command!");
        }
        else{
            receiver.info();
        }
    }

}
