package Commands.SpecificCommands;

import Commands.AbstractCommand;
import Utilities.Receiver;

import java.io.IOException;
import java.io.Serializable;

public class Update extends AbstractCommand implements Serializable {
    private Receiver receiver;
    private static final long serialVersionUID = 1234567L;

    public Update(){

    }

    public Update(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void aboutCommand() {
        System.out.println("update {id}                 - update new values for element has corresponding id");
    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("Client: Invalid command's format! Fail to execute UpdateCommand!");
        }
        else{
            receiver.update();
        }
    }
}
