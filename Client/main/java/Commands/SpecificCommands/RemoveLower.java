package Commands.SpecificCommands;

import Commands.AbstractCommand;
import Utilities.Receiver;

import java.io.IOException;
import java.io.Serializable;

public class RemoveLower extends AbstractCommand implements Serializable {
    private Receiver receiver;
    private static final long serialVersionUID = 1234567L;

    public RemoveLower(){

    }

    public RemoveLower(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void aboutCommand(){
        System.out.println("remove_lower {element}      - removes a new item to the collection if its value is lower than the"+
                " value of the lowest item in this collection");
    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("Client: Invalid command's format! Fail to execute RemoveLower command!");
        }
        else{
            receiver.removeLower();
        }
    }
 }
