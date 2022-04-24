package Commands.SpecificCommands;

import Commands.AbstractCommand;
import Utilities.Receiver;

import java.io.IOException;
import java.io.Serializable;


public class PrintFieldAscendingType extends AbstractCommand implements Serializable {
    private Receiver receiver;
    private static final long serialVersionUID = 1234567L;

    public PrintFieldAscendingType(){

    }

    public PrintFieldAscendingType (Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void aboutCommand() {
        System.out.println("print_field_ascending_type  - print field manufacturers in ascending order");

    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("Client: Invalid command's format! Fail to execute InfoCommand!");
        }
        else{
            receiver.printFieldAscendingType();
        }
    }
}