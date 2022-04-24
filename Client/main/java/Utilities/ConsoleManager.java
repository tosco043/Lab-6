package Utilities;

import Commands.SpecificCommands.*;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleManager {
    public void interactive(String host, String p) throws IOException{
        Communicator communicator = null;
        try{
            communicator = new Communicator(host, Integer.parseInt(p));
            communicator.startCommunication();
        } catch (NumberFormatException e){
            System.out.println("Client: Error! Port is invalid");
            System.exit(0);
        }


        Sender sender = new Sender(communicator.getSocketAddress());
        Invoker invoker = new Invoker();
        Receiver receiver = new Receiver(invoker, sender, communicator);

        invoker.register("add", new Add(receiver));
        invoker.register("remove_greater", new RemoveGreater(receiver));
        invoker.register("remove_lower", new RemoveLower(receiver));
        invoker.register("clear", new Clear(receiver));
        invoker.register("min_by_official_address", new MinByOfficialAddress(receiver));
        invoker.register("exit", new Exit(receiver));
        invoker.register("count_less_than_official_address", new CountLessThanOfficialAddress(receiver));
        invoker.register("help", new Help(receiver));
        invoker.register("info", new Info(receiver));
        invoker.register("print_field_ascendingType", new PrintFieldAscendingType(receiver));
        invoker.register("remove_by_id", new RemoveById(receiver));
        invoker.register("show", new Show(receiver));
        invoker.register("update", new Update(receiver));
        invoker.register("execute_script", new ExecuteScript(receiver));


        Scanner userInput = new Scanner(System.in);
        while(true){
            if(!userInput.hasNextLine()){
                communicator.endCommunication();
                System.exit(0);
            }
            String[] userCommand = userInput.nextLine().trim().split(" ");
            invoker.executeCommand(userCommand);
            System.out.println("-----------------------------------------");
        }
    }
}
