package Commands.SpecificCommands;

import Commands.AbstractCommand;
import Utilities.Receiver;

import java.io.IOException;
import java.io.Serializable;

public class Add extends AbstractCommand implements Serializable {
    private Receiver receiver;
    private static final long serialVersionUID = 1234567L;


    public Add(Receiver receiver){
        this.receiver = receiver;
    }

    public Add() {

    }

    @Override
    public void aboutCommand() {
        System.out.println("add {element}               - add new element into the collection");
    }

    @Override
    public void execute(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("Client: Invalid command's format! Fail to execute Add command!");
        }
        else{
            receiver.add();
        }
    }
}
